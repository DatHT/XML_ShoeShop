/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhdat.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class DBUtils implements Serializable{
    private static final Logger log = Logger.getLogger(DBUtils.class.getName());
 
    public static Connection makeConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=XML_PROJECT;instanceName=DATHT;useUnicode=true;characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(url, "sa", "thanhdat");
            return con;
        } catch (ClassNotFoundException ex) {
            log.log(Level.SEVERE, "CRAWLER-Not found dirver for DB: " + ex.getMessage());
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "CRAWLER-Connection fail: " + ex.getMessage());
        }
        return null;
    }
}
