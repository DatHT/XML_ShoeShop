/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.servlet;

import huynhdat.entities.TblShoe;
import huynhdat.entities.order.TblOrder;
import huynhdat.entities.orderdetail.OrderDetails;
import huynhdat.entities.orderdetail.TblOrderDetail;
import huynhdat.service.IBaseService;
import huynhdat.service.IShoeService;
import huynhdat.service.impl.OrderDetailServiceImpl;
import huynhdat.service.impl.OrderServiceImpl;
import huynhdat.service.impl.ShoeServiceImpl;
import huynhdat.utils.XMLUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Daniel
 */
public class CheckoutServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(CheckoutServlet.class.getName());

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String xmlOrder = request.getParameter("orderXml");
            String xmlOrderDetail = request.getParameter("orderDetailXml");
            System.out.println(xmlOrder);
            String realPath = getServletContext().getRealPath("/");
            String realOrderPath = realPath + orderPath;
            String realOrderDetailPath = realPath + orderDetailPath;
            boolean valid = false;
            try {
                TblOrder order = XMLUtils.unmarshallingWithValidator(realOrderPath, xmlOrder, TblOrder.class);
                OrderDetails orderDetails = null;
                if (order != null) {
                    order.setOrderTime(order.getOrderTime());
                    IBaseService orderService = new OrderServiceImpl();
                    int id = orderService.insert(order);
                    if (id > 0) {
                        IShoeService shoeService = new ShoeServiceImpl();
                        orderDetails = XMLUtils.unmarshallingWithValidator(realOrderDetailPath,
                                xmlOrderDetail, OrderDetails.class);
                        if (orderDetails != null) {
                            List<TblOrderDetail> list = orderDetails.getTblOrderDetail();
                            IBaseService orderDetailService = new OrderDetailServiceImpl();
                            for (int i = 0; i < list.size(); i++) {
                                TblOrderDetail detail = list.get(i);
                                detail.setOrderId(id);
                                orderDetailService.insert(detail);
                                TblShoe shoe = shoeService.getById(detail.getShoeId());
                                System.out.println("ID shoe update: " + detail.getShoeId());
                                shoeService.updateShoe(detail.getShoeId(), shoe.getBoughtNum() + 1);
                            }

                            valid = true;
                        }
                    }

                }
                if (valid) {
                    response.getWriter().write("Cảm ơn bạn đã mua sản phẩm của chúng tôi. Chúng tôi sẽ liên hệ với bạn sớm nhất");
                } else {
                    response.getWriter().write("Xin lỗi, có chút vấn đề xảy ra.");
                }
            } catch (JAXBException ex) {
                log.log(Level.SEVERE, "Marshalling error with validator: " + ex.getMessage());
            } catch (SAXException ex) {
                log.log(Level.SEVERE, "Marshalling error with validator: " + ex.getMessage());
            } catch (ParserConfigurationException ex) {
                log.log(Level.SEVERE, "Marshalling error with validator: " + ex.getMessage());
            } finally {
                response.getWriter().close();
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

}
