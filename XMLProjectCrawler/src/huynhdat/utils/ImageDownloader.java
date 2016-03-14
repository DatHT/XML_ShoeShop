/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.utils;

import huynhdat.dao.ShoeDao;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class ImageDownloader {
    private static final Logger log = Logger.getLogger(ImageDownloader.class.getName());
    
    public static void saveImage(String urlImage, String savedLocation){
        try {
            URL url = new URL(urlImage);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(savedLocation);
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
            is.close();
            os.close();
        } catch (IOException ex) {
            log.log(Level.SEVERE, "CRAWLER-Download Image fail: " + ex.getMessage());
        }
    }
}
