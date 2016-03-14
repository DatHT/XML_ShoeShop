/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dao.impl;

import huynhdat.entities.TblCategory;
import huynhdat.entities.TblShoe;
import huynhdat.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class CategoryDao extends BaseDao<TblCategory>{

    public CategoryDao(Class<TblCategory> clazz) {
        super(clazz);
    }

    @Override
    public List<TblCategory> getAll() throws SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblCategory> categories;
        if (con != null) {
            String sql = "SELECT * FROM TblCategory";
            try {
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                categories = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    TblCategory category = new TblCategory(id, name);
                    categories.add(category);
                }

                return categories;
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        }
        return null;
    }

    @Override
    public TblCategory getById(int id) throws SQLException {
        return null;
    }
    
}
