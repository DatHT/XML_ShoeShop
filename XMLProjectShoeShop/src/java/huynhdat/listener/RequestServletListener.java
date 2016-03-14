/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.listener;

import huynhdat.dao.IBaseDao;
import huynhdat.entities.Shoes;
import huynhdat.entities.TblCategory;
import huynhdat.entities.TblShoe;
import huynhdat.entities.TblSubCategory;
import huynhdat.service.IBaseService;
import huynhdat.service.IShoeService;
import huynhdat.service.impl.CategoryServiceImpl;
import huynhdat.service.impl.ShoeServiceImpl;
import huynhdat.service.impl.SubCategoryServiceImpl;
import huynhdat.utils.XMLUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.xml.bind.JAXBException;

/**
 * Web application lifecycle listener.
 *
 * @author Daniel
 */
public class RequestServletListener implements ServletRequestListener {

    private static final Logger log = Logger.getLogger(RequestServletListener.class.getName());

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        IShoeService shoeService = new ShoeServiceImpl();
        IBaseService<TblCategory> cateService = new CategoryServiceImpl();
        IBaseService<TblSubCategory> subCateService = new SubCategoryServiceImpl();

        Shoes shoes = new Shoes();
        //List<TblShoe> listShoe = shoeService.getAllByboundary(0, 380);
        List<TblShoe> listShoe = shoeService.getAll();
        shoes.setShoe(listShoe);

        try {
            //shoe
            String xml = XMLUtils.marshallToString(shoes);
            sre.getServletRequest().setAttribute("SHOES", xml);
            sre.getServletRequest().setAttribute("NUMOFRESULT", shoes.getShoe().size());
            //category
            List<TblCategory> categories = cateService.getAll();
            List<TblSubCategory> subCategories = subCateService.getAll();
            sre.getServletRequest().setAttribute("LISTCATE", categories);
            sre.getServletRequest().setAttribute("LISTSUBCATE", subCategories);
        } catch (JAXBException ex) {
            log.log(Level.SEVERE, "Marshal to string error: " + ex.getMessage());
        }

        sre.getServletRequest().setAttribute("searchText", sre.getServletRequest().getParameter("searchText"));
        sre.getServletRequest().setAttribute("shoeId", sre.getServletRequest().getParameter("shoeId"));
        
    }
}
