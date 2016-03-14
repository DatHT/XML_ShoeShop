/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dto;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Daniel
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "images", propOrder = {
    "item"
})
public class ListImage implements Serializable{
    @XmlElement(required = true)
    private List<String> item;

    public List<String> getImage() {
        return item;
    }

    public void setImage(List<String> image) {
        this.item = image;
    }
    
    
    
}
