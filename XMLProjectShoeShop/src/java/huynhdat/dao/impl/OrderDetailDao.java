/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dao.impl;

import huynhdat.entities.orderdetail.TblOrderDetail;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class OrderDetailDao extends BaseDao<TblOrderDetail>{

    public OrderDetailDao(Class<TblOrderDetail> clazz) {
        super(clazz);
    }

    @Override
    public List<TblOrderDetail> getAll() throws SQLException {
        return null;
    }

    @Override
    public TblOrderDetail getById(int id) throws SQLException {
        return null;
    }
    
}
