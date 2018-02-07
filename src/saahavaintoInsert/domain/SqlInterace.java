package saahavaintoInsert.domain;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laura
 */
public class SqlInterace {
    
    public static Connection connectToSqlTable(String table) {
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            // handle the error
        }
        
        com.mysql.jdbc.Connection conn = null;
        String DB_URL = "jdbc:mysql://localhost:3306/" + table;


        String USER = "root";
        String PASS = ""; 
        
        try{

        conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(DB_URL,USER,PASS);

        
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return conn;
        
    }
    
    public static void insertRandomDataToSaahavainto(Saahavainto currentHavainto, com.mysql.jdbc.Connection connToSaahavainto) {
        
        
        Statement stmt = null;

        
        try {
            stmt = connToSaahavainto.createStatement();
            
            //new java.sql.Date(currentHavainto.getTimestamp().getTimeInMillis()) muuntaa Calendarin sql:n dateTime tyyppiseksi
                
            stmt.execute("INSERT INTO `saahavainto`(`id`, `dateAndTime`, `place`, `userId`, `temperature`, `windSpeed`, `havainto`) VALUES" + "(NULL, '" + new java.sql.Timestamp(currentHavainto.getDateTime().getTimeInMillis()) + 
                        "', '" + currentHavainto.getPlace() + "', '" + currentHavainto.getUser().getId() + "', '" + currentHavainto.getTemperature()+ "', '" + currentHavainto.getWindSpeed() +
                             "', '" + currentHavainto.getHavainto()+ "')");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
             // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
    }
    
    }
    
    public static User getRandomUser(com.mysql.jdbc.Connection connToUsers) {
        
        Statement stmt1 =null;
        ResultSet rs = null; 
        User selectedUser =null;
        
        try {
        
        stmt1 = connToUsers.createStatement();
        rs = stmt1.executeQuery("SELECT * FROM users ORDER BY RAND() LIMIT 1");  //valitaan 1 satunnainen rivi
            
        while(rs.next()) {  //luodaan valitun rivin datasta user-olio
            selectedUser = new User(rs.getInt("id"), rs.getString("username"), rs.getString("firstName"), rs. getString("lastName"));
        }
        
                } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
             // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt1 != null) {
                try {
                    stmt1.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt1 = null;
            }
            
            return selectedUser;
    }
    }    
}

