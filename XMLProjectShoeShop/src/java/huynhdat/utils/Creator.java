/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.utils;

/**
 *
 * @author Daniel
 */
public class Creator {
    public static void main(String[] args) {
        boolean order = XMLUtils.generateJavaObject("src/java/", "huynhdat.entities.order", "web/WEB-INF/orderSchema.xsd");
        if (order) {
            System.out.println("Done Order");
            boolean orderDetail = XMLUtils.generateJavaObject("src/java/", "huynhdat.entities.orderdetail", "web/WEB-INF/orderDetailSchema.xsd");
            if (orderDetail) {
                System.out.println("Done OrderDatail");
            }
        }
    }
    
}
