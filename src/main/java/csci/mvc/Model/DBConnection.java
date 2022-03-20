package csci.mvc.Model;

import java.sql.*;

public class DBConnection {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/moviebookings","root","TeamB6");
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Failed to connect Database" + e.getMessage());
        }
    }

    public boolean isAdmin(String adminID, String adminPassword){
        try {
            String SQL = "SELECT * FROM usrating";
            rs = st.executeQuery(SQL);

            ResultSetMetaData rsmd = rs.getMetaData();
            int colNum = rsmd.getColumnCount();
            while (rs.next()){
                for(int i = 1; i < colNum; i++) {
                    if (i > 1) System.out.print(", ");
                    String colVal = rs.getString(i);
                    System.out.print(colVal + " " + rsmd.getColumnName(i));
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
