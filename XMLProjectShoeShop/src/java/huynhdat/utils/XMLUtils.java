/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.utils;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Daniel
 */
public class XMLUtils implements Serializable {

    public static Document parseFileToDOM(String xmlFilePath) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFilePath);
        return doc;
    }

    public static XPath createXpathObj() {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        return xpath;
    }

    public static void transformDOMSrcToFile(Node node, String xmlFilePath)
            throws TransformerConfigurationException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer trans = factory.newTransformer();
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        Source source = new DOMSource(node);
        File f = new File(xmlFilePath);
        Result result = new StreamResult(f);
        trans.transform(source, result);
    }

    public static Element createElement(Document doc, String elementname, String elementValue, Map<String, String> attributes) {
        if (doc != null) {
            Element result = doc.createElement(elementname);
            if (elementValue != null) {
                result.setTextContent(elementValue);
            }
            if (attributes != null) {
                if (!attributes.isEmpty()) {
                    for (Map.Entry<String, String> entry : attributes.entrySet()) {
                        result.setAttribute(entry.getKey(), entry.getValue());
                    }
                }
            }
            return result;
        }

        return null;
    }

    public static boolean generateJavaObject(String output, String packageFile, String xsdPath) {
        try {
            SchemaCompiler sc = XJC.createSchemaCompiler();
            sc.setErrorListener(new ErrorListener() {
                @Override
                public void error(SAXParseException saxpe) {
                    System.out.println("error " + saxpe.getMessage());

                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    System.out.println("fatal " + saxpe.getMessage());
                }

                @Override
                public void warning(SAXParseException saxpe) {
                    System.out.println("Warning " + saxpe.getMessage());
                }

                @Override
                public void info(SAXParseException saxpe) {
                    System.out.println("info ");
                }
            });
            sc.forcePackageName(packageFile);
            File schema = new File(xsdPath);
            InputSource is = new InputSource(schema.toURI().toString());
            sc.parseSchema(is);
            S2JJAXBModel model = sc.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(output));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static <T> T unmarshalling(String path, Class<T> clazz) {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            File f = new File(path);
            T item = (T) u.unmarshal(f);
            return item;
        } catch (JAXBException ex) {
            Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static <T> T unmarshallingXMLString(String xmlString, Class<T> clazz) {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            T item = (T) u.unmarshal(new StringReader(xmlString));
            return item;
        } catch (JAXBException ex) {
            Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    public static <T> boolean marshalling(String path, T obj) {
        try {

            JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
            Marshaller mar = ctx.createMarshaller();
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(obj, new File(path));
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void parseFileToSax(String xmlFilePath, DefaultHandler handler)
            throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser sax = factory.newSAXParser();

        sax.parse(xmlFilePath, handler);
    }

    public static XMLStreamReader parseFileToStAX(InputStream is)
            throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(is);

        return reader;
    }
    
    public static String getTextContent(String tagName, XMLStreamReader reader) 
            throws XMLStreamException {
        if (reader != null) {
            while (reader.hasNext()) {                
                int cursor = reader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String sName = reader.getLocalName();
                    if (sName.equals(tagName)) {
                        reader.next();
                        String result =  reader.getText();
                        reader.nextTag();
                        return result;
                    }
                }
            }
        }
        return null;
    }
    
    public static <T> String marshallToString(T obj) throws JAXBException {
        JAXBContext jaxb = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxb.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }
    
    public static <T> T unmarshallingWithValidator(String schemaPath, String xmlString, Class<T> clazz) 
            throws JAXBException, SAXException, ParserConfigurationException, IOException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(schemaPath));
        Validator validator = schema.newValidator();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new InputSource(new StringReader(xmlString)));
        validator.validate(new DOMSource(doc));
        unmarshaller.setSchema(schema);
        
        return (T) unmarshaller.unmarshal(new InputSource(new StringReader(xmlString)));
    }
}
