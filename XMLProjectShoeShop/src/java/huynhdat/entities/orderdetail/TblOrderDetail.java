
package huynhdat.entities.orderdetail;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TblOrderDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TblOrderDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="shoeId" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TblOrderDetail", propOrder = {
    "id",
    "orderId",
    "shoeId",
    "size",
    "quantity",
    "price"
})
public class TblOrderDetail {

    @XmlSchemaType(name = "positiveInteger")
    private Integer id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer orderId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer shoeId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer size;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer quantity;
    private float price;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the shoeId
     */
    public Integer getShoeId() {
        return shoeId;
    }

    /**
     * @param shoeId the shoeId to set
     */
    public void setShoeId(Integer shoeId) {
        this.shoeId = shoeId;
    }

    /**
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
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
