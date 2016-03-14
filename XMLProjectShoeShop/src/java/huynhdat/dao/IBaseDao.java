/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Daniel
 */
public interface IBaseDao<T> {
    public int insert(Map<String, Object> col) throws SQLException;
    public List<T> getAll() throws SQLException;
    public T getById(int id) throws SQLException;
}
