package com.csci6050.ebooking.DB;

import java.sql.*;

public class DBConnection {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnection() {
    }

    public Statement connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/moviebookings","root","TeamB6");
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Failed to connect Database" + e.getMessage());
        }
        return st;
    }
}
