package csci.mvc.Model;

import csci.mvc.Model.DBConnection;

public class main {
    public static void main (String[] args){
        DBConnection connection = new DBConnection();
        System.out.println("Whether admin: " + connection.isAdmin("root", "TeamB6"));
    }
}
