
package huynhdat.entities.order;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the huynhdat.entities.order package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TblOrder_QNAME = new QName("order", "tblOrder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: huynhdat.entities.order
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TblOrder }
     * 
     */
    public TblOrder createTblOrder() {
        return new TblOrder();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TblOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "order", name = "tblOrder")
    public JAXBElement<TblOrder> createTblOrder(TblOrder value) {
        return new JAXBElement<TblOrder>(_TblOrder_QNAME, TblOrder.class, null, value);
    }

}
