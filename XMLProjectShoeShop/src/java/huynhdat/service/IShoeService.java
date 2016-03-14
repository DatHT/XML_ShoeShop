/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.service;

import huynhdat.entities.TblShoe;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface IShoeService {
    public List<TblShoe> getAllByboundary(int start, int limit);
    public TblShoe getById(int id);
    public boolean updateShoe(int id, int boughtNum);
    public List<TblShoe> getAll();
}
