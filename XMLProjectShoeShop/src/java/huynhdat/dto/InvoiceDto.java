/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.dto;

import huynhdat.entities.orderdetail.TblOrderDetail;
import java.util.Date;
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
    "cusName",
    "cusEmail",
    "cusPhone",
    "cusAddress",
    "note",
    "total",
    "orderDetail",
    "time"

})
@XmlRootElement(name = "Invoce")
public class InvoiceDto {

    @XmlElement(required = true)
    private String cusName;

    @XmlElement(required = true)
    private String cusEmail;

    @XmlElement(required = true)
    private String cusPhone;

    @XmlElement(required = true)
    private String cusAddress;

    @XmlElement(required = true)
    private String note;

    @XmlElement(required = true)
    private float total;

    @XmlElement(required = true)
    private List<OrderDetail> orderDetail;
    
    @XmlElement(required = true)
    private Date time;

    public InvoiceDto() {
    }

    public InvoiceDto(String cusName, String cusEmail, String cusPhone, String cusAddress, String note, float total, List<OrderDetail> orderDetail) {
        this.cusName = cusName;
        this.cusEmail = cusEmail;
        this.cusPhone = cusPhone;
        this.cusAddress = cusAddress;
        this.note = note;
        this.total = total;
        this.orderDetail = orderDetail;
    }
    
    

    /**
     * @return the cusName
     */
    public String getCusName() {
        return cusName;
    }

    /**
     * @param cusName the cusName to set
     */
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    /**
     * @return the cusEmail
     */
    public String getCusEmail() {
        return cusEmail;
    }

    /**
     * @param cusEmail the cusEmail to set
     */
    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    /**
     * @return the cusPhone
     */
    public String getCusPhone() {
        return cusPhone;
    }

    /**
     * @param cusPhone the cusPhone to set
     */
    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    /**
     * @return the cusAddress
     */
    public String getCusAddress() {
        return cusAddress;
    }

    /**
     * @param cusAddress the cusAddress to set
     */
    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }


    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
    

}
