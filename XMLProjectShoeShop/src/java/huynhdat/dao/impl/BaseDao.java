/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dao.impl;

import huynhdat.dao.IBaseDao;
import huynhdat.utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public abstract class BaseDao<T> implements IBaseDao<T> {

    private static final Logger log = Logger.getLogger(BaseDao.class.getName());
    private Class<T> clazz;

    public BaseDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public int insert(Map<String, Object> col) throws SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        //Statement stm = null;
        ResultSet rs = null;
        String colums = "";
        String values = "";
        for (String key : col.keySet()) {
            colums += key + ",";
            //values += col.get(key) + ",";
            values += "?,";
        }
        colums = colums.substring(0, colums.length() - 1);
        values = values.substring(0, values.length() - 1);
        if (con != null) {
            try {
                String sql = "INSERT INTO " + clazz.getSimpleName() + " (" + colums + ")" + " VALUES " + "(" + values + ")";
                System.out.println("----INSERT SQL-------: " + sql);
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int count = 1;
                for (String key : col.keySet()) {
                    setData(col.get(key), stm, count++);
                }
                int row = stm.executeUpdate();

                if (row > 0) {
                    rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
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
        return -1;
    }

    private void setData(Object data, PreparedStatement stm, int position) throws SQLException {
        if (data instanceof String) {
            stm.setString(position, (String) data);
        } else if (data instanceof Integer) {
            stm.setInt(position, (int) data);
        } else if (data instanceof Float) {
            stm.setFloat(position, (float) data);
        } else if (data instanceof Date) {
            stm.setDate(position, (Date) data);
        }

    }

}
