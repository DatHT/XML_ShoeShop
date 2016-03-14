/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.service.impl;

import huynhdat.dao.impl.OrderDao;
import huynhdat.entities.order.TblOrder;
import huynhdat.service.IBaseService;
import java.sql.Date;
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
public class OrderServiceImpl implements IBaseService<TblOrder> {

    private static final Logger log = Logger.getLogger(OrderServiceImpl.class.getName());

    private OrderDao orderDao;

    public OrderServiceImpl() {
        orderDao = new OrderDao(TblOrder.class);
    }

    @Override
    public List<TblOrder> getAll() {
        return null;
    }

    @Override
    public TblOrder getById(int id) {
        return null;
    }

    @Override
    public int insert(TblOrder obj) {
        try {
            Map<String, Object> col = new HashMap<>();
            col.put("orderTime", toSqldate(obj.getOrderTime()));
            col.put("total", obj.getTotal());
            col.put("status", obj.getStatus());
            if (obj.getCusId() != null) {
                col.put("cusId", obj.getCusId());
            } else {
                col.put("cusName", obj.getCusName());
                col.put("cusEmail", obj.getCusEmail().toString());
                col.put("cusPhone", obj.getCusPhone());
                col.put("cusAddress", obj.getCusAddress());
                
            }
            if (obj.getNote().length() != 0) {
                col.put("note", obj.getNote());
            }

            return orderDao.insert(col);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Insert into Tbl Order fail: " + ex.getMessage());
        }
        return -1;
    }
    
    private Date toSqldate(java.util.Date date) {
        return new Date(date.getTime());
    }

}
