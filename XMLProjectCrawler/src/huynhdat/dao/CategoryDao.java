/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dao;

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
public class CategoryDao implements Serializable{
    private static final Logger log = Logger.getLogger(CategoryDao.class.getName());
    public int getCategoryId(String name) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (con != null) {
            String sql = "SELECT id FROM TblCategory WHERE name like ?;";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id");
                }
                
            } catch (SQLException ex) {
                log.log(Level.SEVERE, "CRAWLER-Get category fail: " + ex.getMessage());
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
