package course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws InterruptedException   {
        String url = "jdbc:mysql://localhost:3306/";
        String database = "northwind";
        String user = "student"; // Windows:root
        String password = "student";//Windows:"";
        String sql = "SELECT  customerid cid, companyname comp, country " +
                     " FROM customers " +
                     " LIMIT 0,10";
        
        try(Connection conn = DriverManager.getConnection(url + database, user, password);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql)){
            System.out.println("Successful database connection!");
            
            result.afterLast();
            while(result.previous()){
                System.out.println(result.getRow()          + " " +
                                   result.getString("cid")  + "|" +
                                   result.getString("comp") + "|" +
                                   result.getString("country"));
            }
                    
        } catch (SQLException ex) {
            System.err.println("SQL Code:" + ex.getErrorCode());
            System.err.println("SQL State:" + ex.getSQLState());
            System.err.println("SQL Message:" + ex.getMessage());
            ex.printStackTrace();
        }
        
       
    }   
}
