/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dao.impl;

import huynhdat.entities.TblCategory;
import huynhdat.entities.TblSubCategory;
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
public class SubCategoryDao extends BaseDao<TblSubCategory>{

    public SubCategoryDao(Class<TblSubCategory> clazz) {
        super(clazz);
    }

    @Override
    public List<TblSubCategory> getAll() throws SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblSubCategory> subCategories;
        if (con != null) {
            String sql = "SELECT * FROM TblSubCategory";
            try {
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                subCategories = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int cateId = rs.getInt("categoryId");
                    TblSubCategory subCategory = new TblSubCategory(id, name, cateId);
                    subCategories.add(subCategory);
                }

                return subCategories;
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
    public TblSubCategory getById(int id) throws SQLException {
        return null;
    }
    
}
