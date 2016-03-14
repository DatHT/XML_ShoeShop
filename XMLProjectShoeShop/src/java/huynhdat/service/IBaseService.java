/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.service;

import java.util.List;

/**
 *
 * @author Daniel
 */
public interface IBaseService<T> {
    public List<T> getAll();
    public T getById(int id);
    public int insert(T obj);
}
