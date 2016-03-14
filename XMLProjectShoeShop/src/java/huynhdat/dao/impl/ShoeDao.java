/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dao.impl;

import huynhdat.dto.ListImage;
import huynhdat.dto.ShoeDto;
import huynhdat.entities.TblShoe;
import huynhdat.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Daniel
 */
public class ShoeDao extends BaseDao<TblShoe> {

    public ShoeDao(Class<TblShoe> clazz) {
        super(clazz);
    }

    public List<TblShoe> getAllByBoundary(int start, int limit) throws SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblShoe> shoes;
        if (con != null) {
            String sql = "SELECT * FROM ("
                    + "  SELECT *, ROW_NUMBER() OVER (ORDER BY boughtNum) as row FROM TblShoe ) a"
                    + "  WHERE row > " + start + " and row <= " + limit;
            try {
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                shoes = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String thumbPath = rs.getString("thumbPath");
                    float price = rs.getFloat("price");
                    int cateId = rs.getInt("subCategoryId");
                    TblShoe shoe = new TblShoe(id, name, thumbPath, price, cateId);
                    shoes.add(shoe);
                }

                return shoes;
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
    public List<TblShoe> getAll() throws SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblShoe> shoes;
        if (con != null) {
            String sql = "SELECT * FROM TblShoe ORDER BY boughtNum DESC;";
            try {
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                shoes = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String thumbPath = rs.getString("thumbPath");
                    float price = rs.getFloat("price");
                    int cateId = rs.getInt("subCategoryId");
                    TblShoe shoe = new TblShoe(id, name, thumbPath, price, cateId);
                    shoes.add(shoe);
                }

                return shoes;
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
    public TblShoe getById(int id) throws SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblShoe> shoes;
        if (con != null) {
            String sql = "SELECT * FROM TblShoe WHERE id = ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    String thumbPath = rs.getString("thumbPath");
                    String imagesPath = rs.getString("imagesPath");
                    String guarantee = rs.getString("guarantee");
                    int status = rs.getInt("status");
                    String shoeCode = rs.getString("shoeCode");
                    int boughtNum = rs.getInt("boughtNum");
                    TblShoe dto = new TblShoe(name, price, thumbPath, imagesPath, guarantee, status, shoeCode, status);
                    dto.setBoughtNum(boughtNum);
                    return dto;
                }
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

    public boolean updateShoe(int id, int boughtNum) throws SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "UPDATE TblShoe SET boughtNum=? WHERE id=?;";
            try {
                stm = con.prepareStatement(sql);
                stm.setInt(1, boughtNum);
                stm.setInt(2, id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } finally {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            }

        }
        return false;
    }

}
