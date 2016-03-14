/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dto;

import huynhdat.entities.TblShoe;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Daniel
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name",
    "price",
    "thumbPath",
    "images",
    "guarantee",
    "status",
    "shoeCode",
    "subCategoryId"
})
@XmlRootElement(name = "shoe")
public class ShoeDto implements Serializable {

    @XmlElement(required = true)
    private int id;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private float price;

    @XmlElement(required = true)
    private String thumbPath;

    @XmlElement(required = true)
    private ListImage images;

    @XmlElement(required = true)
    private String guarantee;

    @XmlElement(required = true)
    private int status;

    @XmlElement(required = true)
    private String shoeCode;

    @XmlElement(required = true)
    private int subCategoryId;

    public ShoeDto() {
    }

    public ShoeDto(int id, String name, float price, String thumbPath, ListImage images, String guarantee, int status, String shoeCode, int subCategoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.thumbPath = thumbPath;
        this.images = images;
        this.guarantee = guarantee;
        this.status = status;
        this.shoeCode = shoeCode;
        this.subCategoryId = subCategoryId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
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
     * @return the imagesPath
     */
    public ListImage getImages() {
        return images;
    }

    /**
     * @param imagesPath the imagesPath to set
     */
    public void setImages(ListImage images) {
        this.images = images;
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

    public static ShoeDto toShoeDto(TblShoe s) {
        ListImage list = parseToList(s.getImagesPath());
        ShoeDto dto = new ShoeDto(s.getId(), s.getName(), s.getPrice(), s.getThumbPath(), 
                list, s.getGuarantee(), s.getStatus(), s.getShoeCode(), s.getSubCategoryId());
        return dto;
    }
    
    private static ListImage parseToList(String src) {
        ListImage images = new ListImage();
        String[] result = src.split(";");
        List<String> listImage = Arrays.asList(result);
        images.setImage(listImage);
        return images;
    }

}
