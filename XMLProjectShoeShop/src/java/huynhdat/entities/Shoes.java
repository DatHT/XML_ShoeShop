/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Daniel
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "shoe"
})
@XmlRootElement(name = "shoes")
public class Shoes implements Serializable{
    
    @XmlElement(required = true)
    private List<TblShoe> shoe;
    
    public List<TblShoe> getShoe() {
        return shoe;
    }

    public void setShoe(List<TblShoe> shoe) {
        this.shoe = shoe;
    }
    
}
