/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dao;

import huynhdat.dto.SubCategoryDto;
import huynhdat.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class SubCategoryDao implements Serializable {
    private static final Logger log = Logger.getLogger(SubCategoryDao.class.getName());
    
    public boolean addSubCategory(SubCategoryDto dto) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "INSERT INTO TblSubCategory (name,categoryId) VALUES (?,?);";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setInt(2, dto.getCategoryId());
                int row = stm.executeUpdate();
                if (row > 0) {
                    System.out.println("INFO: Insert SubCategory Success | CategoryName: " + dto.getName());
                    return true;
                }
            } catch (SQLException ex) {
                log.log(Level.SEVERE, "CRAWLER-Insert into SubCategory fail: " + ex.getMessage());
            } finally {
                try {
                    if (stm != null) {
                        stm.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    log.log(Level.SEVERE, "CRAWLER-Error close connection fail: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

        }
        return false;
    }
    
    public int getSubCategoryId(String name) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (con != null) {
            String sql = "SELECT id FROM TblSubCategory WHERE name like ?;";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id");
                }
                
            } catch (SQLException ex) {
                log.log(Level.SEVERE, "CRAWLER-Get subcategory fail: " + ex.getMessage());
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stm != null) {
                        stm.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    log.log(Level.SEVERE, "CRAWLER-Error close connection fail: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

        }
        
        return -1;
    }
}
