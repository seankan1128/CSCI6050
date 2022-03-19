package csci.mvc.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc","root","TeamB6");
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Failed to connect Database" + e.getMessage());
        }
    }

    public boolean isAdmin(String adminID, String adminPassword){
        try {
            String SQL = "SELECT * FROM admin WHERE adminID = '" + adminID +"' AND adminPassword = '" + adminPassword + "'";
            rs = st.executeQuery(SQL);
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            System.out.println("Wrong Admin ID or PW" + e.getMessage());
        }
        return false;
    }

}
