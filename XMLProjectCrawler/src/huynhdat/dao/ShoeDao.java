/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dao;

import huynhdat.dto.ShoeDto;
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
public class ShoeDao implements Serializable{
    private static final Logger log = Logger.getLogger(ShoeDao.class.getName());
    public boolean addShoe(ShoeDto dto) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "INSERT INTO TblShoe (name,price,thumbPath,description,imagesPath,guarantee,material"
                    + ",color,status,shoeCode,subCategoryId,boughtNum) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setFloat(2, dto.getPrice());
                stm.setString(3, dto.getThumbPath());
                stm.setString(4, dto.getDescription());
                stm.setString(5, dto.getImagesPath());
                stm.setString(6, dto.getGuarantee());
                stm.setString(7, dto.getMaterial());
                stm.setString(8, dto.getColor());
                stm.setInt(9, dto.getStatus());
                stm.setString(10, dto.getShoeCode());
                stm.setInt(11, dto.getSubCategoryId());
                stm.setInt(12, dto.getBoughtNum());
                int row = stm.executeUpdate();
                if (row > 0) {
                    System.out.println("INFO: Insert Shoe Success | Shoe: " + dto.getName());
                    return true;
                }
            } catch (SQLException ex) {
                    log.log(Level.SEVERE, "CRAWLER-Insert into Shoe fail: " + ex.getMessage());
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
    
    public int getShoeId(String name) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (con != null) {
            String sql = "SELECT id FROM TblShoe WHERE name like ?;";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id");
                }
                
            } catch (SQLException ex) {
                log.log(Level.SEVERE, "CRAWLER-Get shoe id fail: " + ex.getMessage());
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
