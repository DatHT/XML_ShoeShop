/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.service.impl;

import huynhdat.dao.impl.BaseDao;
import huynhdat.dao.impl.ShoeDao;
import huynhdat.entities.TblShoe;
import huynhdat.service.IShoeService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class ShoeServiceImpl implements IShoeService{
    private static final Logger log = Logger.getLogger(ShoeServiceImpl.class.getName());
    
    private ShoeDao shoeDao;

    public ShoeServiceImpl() {
        shoeDao = new ShoeDao(TblShoe.class);
    }
    
    
    
    @Override
    public List<TblShoe> getAllByboundary(int start, int limit) {
        try {
            return shoeDao.getAllByBoundary(start, limit);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Get all shoe by boundary error: " + ex.getMessage());
        }
        
        return null;
    }

    @Override
    public TblShoe getById(int id) {
        try {
            return shoeDao.getById(id);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Get shoe by id error: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateShoe(int id, int boughtNum) {
        try {
            return shoeDao.updateShoe(id, boughtNum);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Update shoe have problem: " +  ex.getMessage());
        }
        return false;
    }

    @Override
    public List<TblShoe> getAll() {
        try {
            return shoeDao.getAll();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Get all shoe by boundary error: " + ex.getMessage());
        }
        
        return null;
    }
    
}
