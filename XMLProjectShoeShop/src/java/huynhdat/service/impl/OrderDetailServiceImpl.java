/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.service.impl;

import huynhdat.dao.impl.OrderDetailDao;
import huynhdat.entities.orderdetail.TblOrderDetail;
import huynhdat.service.IBaseService;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class OrderDetailServiceImpl implements IBaseService<TblOrderDetail>{
    private static final Logger log = Logger.getLogger(OrderDetailServiceImpl.class.getName());
    
    private OrderDetailDao orderDetailDao;

    public OrderDetailServiceImpl() {
        orderDetailDao = new OrderDetailDao(TblOrderDetail.class);
    }

    @Override
    public List<TblOrderDetail> getAll() {
        return null;
    }

    @Override
    public TblOrderDetail getById(int id) {
        return null;
    }

    @Override
    public int insert(TblOrderDetail obj) {
        try {
            Map<String, Object> col = new HashMap<>();
            col.put("orderId", obj.getOrderId());
            col.put("shoeId", obj.getShoeId());
            col.put("size", obj.getSize());
            col.put("quantity", obj.getQuantity());
            col.put("price", obj.getPrice());
            
            return orderDetailDao.insert(col);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Insert into Tbl OrderDetail fail: " + ex.getMessage());
        }
        return -1;
    }
    
    
}
