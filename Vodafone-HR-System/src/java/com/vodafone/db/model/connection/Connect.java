package com.vodafone.db.model.connection;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Get Database Connection
 * @author ahmed_amer
 */
public class Connect {

    public static Connection conn = null;

    /**
     *  Load mysql Driver 
     *  initiate Connection
     * 
     */
    public Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Vodafone_HR_System?user=root&password=pass");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * initiate db Connection to mysql DB
     * @return  mySqlConnection Object
     */
    public static Connection getConnection() {
        if (conn == null) {
            new Connect();
        }
        return conn;
    }

    public static void main(String[] args) {
        Connect connect = new Connect();
        Connect.getConnection();
    }
}
