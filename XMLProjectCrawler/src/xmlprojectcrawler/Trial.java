/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprojectcrawler;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Trial {
    public static void main(String[] args) {
//        int a = "379,000 đ".lastIndexOf(" ");
//        String price = "379,000 đ".substring(0, a).replace(",", "");
//        int b = "Mã Sản phẩm: Na117".lastIndexOf(" ");
//        System.out.println(Integer.parseInt(price));
//        System.out.println("Mã Sản phẩm: Na117".substring(b + 1));
//        
//        String test = "a,b,c,d,e,";
//        test = test.substring(0, test.length() - 1);
//        System.out.println("TEST: " + test);
            
          String s = "images/giay-wm-309-giay-sneaker-da-de-don-0.jpg;images/giay-wm-309-giay-sneaker-da-de-don-1.jpg;images/giay-wm-309-giay-sneaker-da-de-don-2.jpg;images/giay-wm-309-giay-sneaker-da-de-don-3.jpg;images/giay-wm-309-giay-sneaker-da-de-don-4.jpg;";
          String[] result = s.split(";");
          List<String> thehell = Arrays.asList(result);
          for (String string : thehell) {
              System.out.println(string);
        }
    }
}
