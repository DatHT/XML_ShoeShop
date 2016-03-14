/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Daniel
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderDetail", propOrder = {
    "shoeName",
    "size",
    "quantity",
    "price"
})
public class OrderDetail {
    
    @XmlElement(required = true)
    private String shoeName;
    
    @XmlElement(required = true)
    private int size;
    
    @XmlElement(required = true)
    private int quantity;
    
    @XmlElement(required = true)
    private float price;

    public OrderDetail() {
    }

    public OrderDetail(String shoeName, int size, int quantity, float price) {
        this.shoeName = shoeName;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the shoeName
     */
    public String getShoeName() {
        return shoeName;
    }

    /**
     * @param shoeName the shoeName to set
     */
    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
}
