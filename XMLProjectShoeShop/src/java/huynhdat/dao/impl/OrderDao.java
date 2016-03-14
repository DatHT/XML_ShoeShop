/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dao.impl;

import huynhdat.entities.order.TblOrder;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class OrderDao extends BaseDao<TblOrder>{

    public OrderDao(Class<TblOrder> clazz) {
        super(clazz);
    }

    @Override
    public List<TblOrder> getAll() throws SQLException {
        return null;
    }

    @Override
    public TblOrder getById(int id) throws SQLException {
        return null;
    }

    
}
