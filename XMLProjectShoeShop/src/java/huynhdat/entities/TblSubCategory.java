/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.entities;

import java.io.Serializable;

/**
 *
 * @author Daniel
 */
public class TblSubCategory implements Serializable{
    private int id;
    private String name;
    private int categoryId;

    public TblSubCategory() {
    }

    public TblSubCategory(String name, int categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public TblSubCategory(int id, String name, int categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    
}
