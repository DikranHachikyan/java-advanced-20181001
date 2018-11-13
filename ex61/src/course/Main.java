package course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws InterruptedException   {
        String url = "jdbc:mysql://localhost:3306/";
        String database = "northwind";
        String user = "student"; // Windows:root
        String password = "student";//Windows:"";
        
        //try(Connection conn = DriverManager.getConnection(url + database, user, password)){
        try(Connection conn = DriverManager.getConnection(url + database+"?user=" + user +"&password=" + password)){
            System.out.println("Successful database connection!");
        } catch (SQLException ex) {
            System.err.println("SQL Code:" + ex.getErrorCode());
            System.err.println("SQL State:" + ex.getSQLState());
            System.err.println("SQL Message:" + ex.getMessage());
            ex.printStackTrace();
        }
        
       
    }   
}
