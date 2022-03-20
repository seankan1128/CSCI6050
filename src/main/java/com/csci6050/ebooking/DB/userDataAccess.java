package com.csci6050.ebooking.DB;

import java.sql.ResultSetMetaData;
import java.sql.*;


public class userDataAccess extends DBConnection {
    private ResultSet rs;

   private Statement st = super.connect();

    public boolean createBeanList(String adminID, String adminPassword){
        try {
            String SQL = "SELECT firstname FROM users WHERE email='ewm92737@uga.edu' AND password = 'TeamB5'";
            rs = st.executeQuery(SQL);

            ResultSetMetaData rsmd = rs.getMetaData();
            int colNum = rsmd.getColumnCount();
            while (rs.next()){
                for(int i = 1; i <= colNum; i++) {
                    if (i > 1) System.out.print(", ");
                    String colVal = rs.getString(i);
                    System.out.print(colVal + " ");
                }
                System.out.println("");
            }

            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            System.out.println("Wrong Admin ID or PW" + e.getMessage());
        }
        return false;
    }

    public boolean isUser(String inputEmail, String inputPassword){
        String email = "'" + inputEmail + "'";
        String password = "'" + inputPassword + "'";

        try {
            String SQL = "SELECT firstName FROM users WHERE email=" + email + " AND password=" + password;
            rs = st.executeQuery(SQL);

            ResultSetMetaData rsmd = rs.getMetaData();
            int colNum = rsmd.getColumnCount();
            while (rs.next()){
                for(int i = 1; i <= colNum; i++) {
                    if (i > 1) System.out.print(", ");
                    String colVal = rs.getString(i);
                    System.out.print(colVal + " ");
                }
                System.out.println("");
            }
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            System.out.println("Wrong Admin ID or PW" + e.getMessage());
        }
        return false;
    }
}
