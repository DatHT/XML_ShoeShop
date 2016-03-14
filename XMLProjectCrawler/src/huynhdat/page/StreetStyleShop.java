/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.page;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import huynhdat.dao.CategoryDao;
import huynhdat.dao.SubCategoryDao;
import huynhdat.dto.SubCategoryDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class StreetStyleShop {

    private List<String> linkcategory;
    private List<String> subCategoryNames;
    private WebClient webClient;
    private HtmlPage page;
    private String webUrl;

    public StreetStyleShop(String webUrl) throws IOException {
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
            if (subDao.getSubCategoryId(name) == -1) {
                SubCategoryDto dto = new SubCategoryDto(name, categoryId);
                if (subDao.addSubCategory(dto)) {
                    System.out.println("Insert Success");
                }else {
                    System.out.println("Insert Fail");
                }
                subCategoryNames.add(name);
            }
            //get Link category
            String link = e.getAttribute("href");
            if (!link.contains("http")) {
                link = webUrl + link;
            }
            linkcategory.add(link);
            
        }
    }
    
    public void parseSubCategory() {
        //giay nu
        parseByCategory("GIÀY NỮ", "//*[@id='menu-top']/ul/li[3]/ul/li/a");
        //giay nam
        parseByCategory("GIÀY NAM", "//*[@id='menu-top']/ul/li[2]/ul/li/a");
        
    }
}
