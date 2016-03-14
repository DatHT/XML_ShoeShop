
package huynhdat.entities.order;

import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TblOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TblOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="orderTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="cusId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cusName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cusEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cusPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cusAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TblOrder", propOrder = {
    "id",
    "orderTime",
    "total",
    "status",
    "cusId",
    "cusName",
    "cusEmail",
    "cusPhone",
    "cusAddress",
    "note"
})
@XmlRootElement(name = "tblOrder")
public class TblOrder {

    @XmlSchemaType(name = "positiveInteger")
    protected Integer id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date orderTime;
    protected float total;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected Integer status;
    protected String cusId;
    protected String cusName;
    protected String cusEmail;
    protected String cusPhone;
    protected String cusAddress;
    protected String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    
    

    

    /**
     * Gets the value of the total property.
     * 
     */
    public float getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(float value) {
        this.total = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    

    

    /**
     * Gets the value of the cusId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCusId() {
        return cusId;
    }

    /**
     * Sets the value of the cusId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCusId(String value) {
        this.cusId = value;
    }

    /**
     * Gets the value of the cusName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCusName() {
        return cusName;
    }

    /**
     * Sets the value of the cusName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCusName(String value) {
        this.cusName = value;
    }

    /**
     * Gets the value of the cusEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCusEmail() {
        return cusEmail;
    }

    /**
     * Sets the value of the cusEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCusEmail(String value) {
        this.cusEmail = value;
    }

    /**
     * Gets the value of the cusPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCusPhone() {
        return cusPhone;
    }

    /**
     * Sets the value of the cusPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCusPhone(String value) {
        this.cusPhone = value;
    }

    /**
     * Gets the value of the cusAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCusAddress() {
        return cusAddress;
    }

    /**
     * Sets the value of the cusAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCusAddress(String value) {
        this.cusAddress = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

}
