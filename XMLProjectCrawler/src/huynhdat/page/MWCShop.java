/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.page;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import huynhdat.dao.CategoryDao;
import huynhdat.dao.ShoeDao;
import huynhdat.dao.SubCategoryDao;
import huynhdat.dto.ShoeDto;
import huynhdat.dto.SubCategoryDto;
import huynhdat.utils.ImageDownloader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.NamedNodeMap;

/**
 *
 * @author Daniel
 */
public class MWCShop {

    private List<String> linkcategory;
    private List<String> subCategoryNames;
    private WebClient webClient;
    private HtmlPage page;
    private String webUrl;

    public MWCShop(String webUrl) throws IOException {
        this.webUrl = webUrl;
        webClient = new WebClient(BrowserVersion.FIREFOX_17);
        page = webClient.getPage(webUrl);
        linkcategory = new ArrayList<>();
        subCategoryNames = new ArrayList<>();
    }

    private void parseByCategory(String category, String xpath) {
        int categoryId;
        CategoryDao categoryDao = new CategoryDao();
        SubCategoryDao subDao = new SubCategoryDao();
        List<HtmlElement> boyCategories = (List<HtmlElement>) page.getByXPath(xpath);
        categoryId = categoryDao.getCategoryId(category);
        for (HtmlElement e : boyCategories) {
            String name = e.getTextContent().trim();
            subCategoryNames.add(name);
            if (subDao.getSubCategoryId(name) == -1) {
                SubCategoryDto dto = new SubCategoryDto(name, categoryId);
                if (subDao.addSubCategory(dto)) {
                    System.out.println("Insert Success");
                } else {
                    System.out.println("Insert Fail");
                }
                System.out.println("NAme Cate: " + name);
            }
            //get Link category
            String link = e.getAttribute("href");
            if (!link.contains("http")) {
                link = webUrl + link;
            }
            linkcategory.add(link);
            System.out.println("Link:" + link);
        }
    }

    public void parseSubCategory() {
        //giay nu
        //parseByCategory("GIÀY NỮ", "//*[@id='menu-top']/ul/li[3]/ul/li/a");
        parseByCategory("GIÀY NỮ", MWCShopInfo.SUB_CATE_GIRL_XPATH);
        //giay nam
        //parseByCategory("GIÀY NAM", "//*[@id='menu-top']/ul/li[4]/ul/li/a");
        parseByCategory("GIÀY NAM", MWCShopInfo.SUB_CATE_BOY_XPATH);

    }

    public void parseShoe() throws IOException {
        for (int x = 0; x < linkcategory.size(); x++) {
            System.out.println("In coming category: " + subCategoryNames.get(x));
            String currentUrl = linkcategory.get(x);
            System.out.println("CURRENT URL: " + currentUrl);
            SubCategoryDao subCateDao = new SubCategoryDao();
            int subCateId = subCateDao.getSubCategoryId(subCategoryNames.get(x));
            if (subCateId != -1) {
                System.out.println("Sub-Id: " + subCateId);
                WebClient shoeClient = new WebClient(BrowserVersion.FIREFOX_17);
                page = shoeClient.getPage(currentUrl);
                List<HtmlElement> numOfPage = (List<HtmlElement>) page.getByXPath(MWCShopInfo.NUM_OF_PAGE_XPATH);
                for (int i = 1; i < numOfPage.size() - 1; i++) {
                    System.out.println("==========Page: " + i + "================");
                    if (i >= 2) {
                        WebClient nextClient = new WebClient(BrowserVersion.FIREFOX_17);
                        page = nextClient.getPage(MWCShopInfo.WEB_MWC_PAGE + numOfPage.get(i).getAttribute("href"));
                    }
                    List<HtmlElement> thumbsLinks = (List<HtmlElement>) page.getByXPath(MWCShopInfo.THUMB_PATH_XPATH);
                    for (HtmlElement el : thumbsLinks) {
                        ShoeDao shoeDao = new ShoeDao();
                        String hrefDetail = el.getAttribute("href");
                        //set name for images
                        String imageName = hrefDetail.substring(hrefDetail.lastIndexOf("/") + 1);
                        System.out.println("Image name: " + imageName);
                        DomNode img = el.getFirstChild();
                        NamedNodeMap attributes = img.getAttributes();
                        String dtoTitle = attributes.getNamedItem("title").getNodeValue();
                        System.out.println("Title: " + dtoTitle);
                        String imagePath = MWCShopInfo.IMAGE_SERVER_LOCATION + "/" + imageName;
                        ImageDownloader.saveImage(attributes.getNamedItem("src").getNodeValue(),
                                imagePath + ".jpg");
                        String dtoImagePath = "images/" + imageName + ".jpg";
                        WebClient detailClient = new WebClient(BrowserVersion.FIREFOX_17);
                        HtmlPage detailPage = detailClient.getPage(MWCShopInfo.WEB_MWC_PAGE + hrefDetail);
                        List<HtmlElement> elPrice = (List<HtmlElement>) detailPage.getByXPath(MWCShopInfo.PRODUCT_PRICE_XPATH);
                        List<HtmlElement> elCode = (List<HtmlElement>) detailPage.getByXPath(MWCShopInfo.PRODUCT_CODE_XPATH);
                        List<HtmlElement> elGuarantee = (List<HtmlElement>) detailPage.getByXPath(MWCShopInfo.PRODUCT_GUARANTEE_XPATH);
                        List<HtmlElement> listImages = (List<HtmlElement>) detailPage.getByXPath(MWCShopInfo.PRODUCT_LIST_IMAGE_XPATH);
                        int posPrice = elPrice.get(0).getTextContent().lastIndexOf(" ");
                        String sPrice = elPrice.get(0).getTextContent().substring(0, posPrice).replace(",", "");
                        float dtoPrice = Float.parseFloat(sPrice);
                        int posCode = elCode.get(0).getTextContent().lastIndexOf(" ");
                        String dtoCode = elCode.get(0).getTextContent().substring(posCode + 1);
                        String dtoListImage = "";
                        for (int j = 0; j < listImages.size(); j++) {
                            HtmlElement listImage = listImages.get(j);
                            String dImage = "images/" + imageName + "-" + j + ".jpg";
                            ImageDownloader.saveImage(listImage.getAttribute("src"), imagePath + "-" + j + ".jpg");
                            dtoListImage = dtoListImage + dImage + ";";
                        }
                        ShoeDto shoeDto = new ShoeDto(dtoTitle, dtoPrice, dtoImagePath, dtoListImage, "Keo Vĩnh Viễn", 1, dtoCode, subCateId);
                        if (shoeDao.getShoeId(dtoTitle) == -1) {
                            if (shoeDao.addShoe(shoeDto)) {
                                System.out.println("Add Done");
                            }else System.out.println("Add fail");
                        }
                        System.out.println("----------------done----------------------");
                    }
                }
            }

        }
    }
}
