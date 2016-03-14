/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprojectcrawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import huynhdat.page.MWCShop;
import huynhdat.page.MWCShopInfo;
import huynhdat.page.StreetStyleShop;
import huynhdat.utils.ImageDownloader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.NamedNodeMap;

/**
 *
 * @author Daniel
 */
public class XMLProjectCrawler {

    public static void main(String[] args) {
        try {
            LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
            MWCShop mWCShop = new MWCShop("http://mwcshop.com");
            mWCShop.parseSubCategory();
            mWCShop.parseShoe();
        } catch (IOException ex) {
            Logger.getLogger(XMLProjectCrawler.class.getName()).log(Level.SEVERE, "Error: IO-reading webpage", ex.getMessage());
        }
    }

}
