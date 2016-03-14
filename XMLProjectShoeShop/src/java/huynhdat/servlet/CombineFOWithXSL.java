/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.servlet;

import huynhdat.dto.InvoiceDto;
import huynhdat.dto.OrderDetail;
import huynhdat.entities.TblShoe;
import huynhdat.entities.order.TblOrder;
import huynhdat.entities.orderdetail.OrderDetails;
import huynhdat.entities.orderdetail.TblOrderDetail;
import huynhdat.service.IShoeService;
import huynhdat.service.impl.ShoeServiceImpl;
import huynhdat.utils.XMLUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import static org.apache.jasper.tagplugins.jstl.core.Out.output;
import org.xml.sax.SAXException;

/**
 *
 * @author Daniel
 */
public class CombineFOWithXSL extends HttpServlet {

    private static final Logger log = Logger.getLogger(CombineFOWithXSL.class.getName());

    private static final String orderPath = "WEB-INF/orderSchema.xsd";
    private static final String orderDetailPath = "WEB-INF/orderDetailSchema.xsd";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ByteArrayOutputStream out = null;
        response.setContentType("application/pdf");
        try {
            //create content for pdf
            String strOrder = request.getParameter("xmlOrder");
            String strOrderDetails = request.getParameter("xmlOrderDetail");

            String realPath = getServletContext().getRealPath("/");
            String realOrderPath = realPath + orderPath;
            String realOrderDetailPath = realPath + orderDetailPath;

//            TblOrder order = XMLUtils.unmarshallingWithValidator(realOrderPath, strOrder, TblOrder.class);
//            OrderDetails orderDetails = XMLUtils.unmarshallingWithValidator(realOrderDetailPath,
//                    strOrderDetails, OrderDetails.class);
            TblOrder order = XMLUtils.unmarshallingXMLString(strOrder, TblOrder.class);
            OrderDetails orderDetails = XMLUtils.unmarshallingXMLString(strOrderDetails, OrderDetails.class);
            List<OrderDetail> details = new ArrayList<OrderDetail>();
            IShoeService shoeService = new ShoeServiceImpl();
            for (TblOrderDetail e : orderDetails.getTblOrderDetail()) {
                TblShoe shoe = shoeService.getById(e.getShoeId());
                OrderDetail temp = new OrderDetail(shoe.getName(), e.getSize(), e.getQuantity(), e.getPrice());
                details.add(temp);
            }

            InvoiceDto invoice = new InvoiceDto(order.getCusName(), order.getCusEmail(), order.getCusPhone(),
                    order.getCusAddress(), order.getNote(), order.getTotal(), details);
            invoice.setTime(new Date());
            String xmlString = XMLUtils.marshallToString(invoice);

            String path = this.getServletContext().getRealPath("/");
            String xslPath = path + "WEB-INF/invoiceFO.xsl";
            String foPath = path + "WEB-INF/Invoice-FO.fo";
            methodTrAX(xslPath, xmlString, foPath);
            File file = new File(foPath);
            FileInputStream input = new FileInputStream(file);
            out = new ByteArrayOutputStream();

            FopFactory ff = FopFactory.newInstance();
            ff.setUserConfig(path + "/WEB-INF/config.xml");
            FOUserAgent fua = ff.newFOUserAgent();
            Fop fop = ff.newFop(MimeConstants.MIME_PDF, fua, out);

            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer trans = tff.newTransformer();
            File fo = new File(foPath);
            Source src = new StreamSource(fo);
            Result result = new SAXResult(fop.getDefaultHandler());
            trans.transform(src, result);
            byte[] content = out.toByteArray();
            response.setContentLength(content.length);

            response.setDateHeader("Expires", 0);
            response.getOutputStream().write(content);
            response.getOutputStream().flush();

        } catch (SAXException ex) {
            Logger.getLogger(CombineFOWithXSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(CombineFOWithXSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CombineFOWithXSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(CombineFOWithXSL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (out != null) {

                    out.close();
                }
                response.getOutputStream().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void methodTrAX(String xslPath, String xmlString, String output) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xsltFile = new StreamSource(xslPath);
            Transformer trans = tf.newTransformer(xsltFile);

            StreamSource xmlFile = new StreamSource(new StringReader(xmlString));
            StreamResult resultFile = new StreamResult(new FileOutputStream(output));
            trans.transform(xmlFile, resultFile);
        } catch (TransformerConfigurationException ex) {
            log.log(Level.SEVERE, "Transformer config with PDF error: " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            log.log(Level.SEVERE, "File not found error: " + ex.getMessage());
        } catch (TransformerException ex) {
            log.log(Level.SEVERE, "Transformer with PDF error: " + ex.getMessage());
        }

    }

}
