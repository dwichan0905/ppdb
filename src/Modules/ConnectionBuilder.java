/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwi Candra Permana
 */
public class ConnectionBuilder {
    
    private static Connection conn;
    private static final String hostname  = "localhost";
    private static final int port         = 3306;
    private static final String dbName    = "ppdb";
    private static final String username  = "root";
    private static final String password  = "";
    
    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + String.valueOf(port) + "/" + dbName, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            conn = null;
            Logger.getLogger(ConnectionBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return conn;
    }
    
    public ResultSet executeSelect(String query) throws SQLException {
        Statement st = getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }
    
    public int executeUpdate(String query) throws SQLException {
        Statement st = getConnection().createStatement();
        return st.executeUpdate(query);
    }
    
    public boolean isConnected() {
        try {
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
