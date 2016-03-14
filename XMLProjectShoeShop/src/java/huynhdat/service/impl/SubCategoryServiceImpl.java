/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.service.impl;

import huynhdat.dao.impl.SubCategoryDao;
import huynhdat.entities.TblSubCategory;
import huynhdat.service.IBaseService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class SubCategoryServiceImpl implements IBaseService<TblSubCategory>{
    private static final Logger log = Logger.getLogger(SubCategoryServiceImpl.class.getName());
    
    private SubCategoryDao subCategoryDao;

    public SubCategoryServiceImpl() {
        subCategoryDao = new SubCategoryDao(TblSubCategory.class);
    }
    
    

    @Override
    public List<TblSubCategory> getAll() {
        try {
            return subCategoryDao.getAll();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Get All sub category error: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public TblSubCategory getById(int id) {
        return null;
    }

    @Override
    public int insert(TblSubCategory obj) {
        return -1;
    }
}
