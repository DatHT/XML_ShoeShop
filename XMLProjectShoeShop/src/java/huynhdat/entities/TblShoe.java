/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Daniel
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shoe", propOrder = {
    "id",
    "name",
    "price",
    "thumbPath",
    "subCategoryId"
})
public class TblShoe implements Serializable {

    @XmlElement(required = true)
    private int id;
    
    @XmlElement(required = true)
    private String name;
    
    @XmlElement(required = true)
    private float price;
    
    @XmlElement(required = true)
    private String thumbPath;
    
    @XmlTransient
    private String description;
    @XmlTransient
    private String imagesPath;
    @XmlTransient
    private String guarantee;
    @XmlTransient
    private String material;
    @XmlTransient
    private String color;
    @XmlTransient
    private int status;
    @XmlTransient
    private String shoeCode;
    @XmlElement(required = true)
    private int subCategoryId;
    @XmlTransient
    private int boughtNum;

    public TblShoe() {
    }
    
    public TblShoe(int id, String name, String thumbPath, float price, int subCateId) {
        this.id = id;
        this.name = name;
        this.thumbPath = thumbPath;
        this.price = price;
        this.subCategoryId = subCateId;
    }

    public TblShoe(String name, float price, String thumbPath, String description, String imagesPath, String guarantee, String material, String color, int status, String shoeCode, int subCategoryId) {
        this.name = name;
        this.price = price;
        this.thumbPath = thumbPath;
        this.description = description;
        this.imagesPath = imagesPath;
        this.guarantee = guarantee;
        this.material = material;
        this.color = color;
        this.status = status;
        this.shoeCode = shoeCode;
        this.subCategoryId = subCategoryId;
    }

    public TblShoe(String name, float price, String thumbPath, String imagesPath,
            String guarantee, int status, String shoeCode, int subCategoryId) {
        this.name = name;
        this.price = price;
        this.thumbPath = thumbPath;
        this.imagesPath = imagesPath;
        this.guarantee = guarantee;
        this.status = status;
        this.shoeCode = shoeCode;
        this.subCategoryId = subCategoryId;
    }

    public TblShoe(int id, String name, float price, String thumbPath, String description, String imagesPath, String guarantee, String material, String color, int status, String shoeCode, int subCategoryId, int boughtNum) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.thumbPath = thumbPath;
        this.description = description;
        this.imagesPath = imagesPath;
        this.guarantee = guarantee;
        this.material = material;
        this.color = color;
        this.status = status;
        this.shoeCode = shoeCode;
        this.subCategoryId = subCategoryId;
        this.boughtNum = boughtNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoughtNum() {
        return boughtNum;
    }

    public void setBoughtNum(int boughtNum) {
        this.boughtNum = boughtNum;
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

    /**
     * @return the thumbPath
     */
    public String getThumbPath() {
        return thumbPath;
    }

    /**
     * @param thumbPath the thumbPath to set
     */
    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the imagesPath
     */
    public String getImagesPath() {
        return imagesPath;
    }

    /**
     * @param imagesPath the imagesPath to set
     */
    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    /**
     * @return the guarantee
     */
    public String getGuarantee() {
        return guarantee;
    }

    /**
     * @param guarantee the guarantee to set
     */
    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the shoeCode
     */
    public String getShoeCode() {
        return shoeCode;
    }

    /**
     * @param shoeCode the shoeCode to set
     */
    public void setShoeCode(String shoeCode) {
        this.shoeCode = shoeCode;
    }

    /**
     * @return the subCategoryId
     */
    public int getSubCategoryId() {
        return subCategoryId;
    }

    /**
     * @param subCategoryId the subCategoryId to set
     */
    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

}
