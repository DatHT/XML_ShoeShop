/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.service.impl;

import huynhdat.dao.impl.CategoryDao;
import huynhdat.entities.TblCategory;
import huynhdat.service.IBaseService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class CategoryServiceImpl implements IBaseService<TblCategory>{
    private static final Logger log = Logger.getLogger(CategoryServiceImpl.class.getName());
    
    private CategoryDao categoryDao;

    public CategoryServiceImpl() {
        categoryDao = new CategoryDao(TblCategory.class);
    }
    
    

    @Override
    public List<TblCategory> getAll() {
        try {
            return categoryDao.getAll();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Get all category error: ", ex.getMessage());
        }
        return null;
        
        
    }

    @Override
    public TblCategory getById(int id) {
        return null;
    }

    @Override
    public int insert(TblCategory obj) {
        return -1;
    }
}
