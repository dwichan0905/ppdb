/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwi Candra Permana
 */
public class ConnectionBuilder {
    
    private Connection conn;
    private String hostname;
    private int port;
    private String dbName;
    private String username;
    private String password;
    
    private void readProperties() {
        FileReader fr = null;
        try {
            Properties prop = new Properties();
            fr = new FileReader(new File("Connection.properties"));
            prop.load(fr);
            
            hostname = prop.getProperty("IP");
            port = Integer.parseInt(prop.getProperty("Port"));
            dbName = prop.getProperty("DB");
            username = prop.getProperty("Uname");
            password = prop.getProperty("Pass");
            
            fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public Connection getConnection() {
        try {
            readProperties();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + String.valueOf(port) + "/" + dbName, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            conn = null;
            Logger.getLogger(ConnectionBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return conn;
    }
    
    public ResultSet executeSelect(String query) throws SQLException {
        readProperties();
        Statement st = getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }
    
    public int executeUpdate(String query) throws SQLException {
        readProperties();
        Statement st = getConnection().createStatement();
        return st.executeUpdate(query);
    }
    
    public boolean isConnected() {
        try {
            readProperties();
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + String.valueOf(port) + "/" + dbName, username, password);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
            //Logger.getLogger(ConnectionBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect() {
        conn = null;
    }
}
