package course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args)  {
        String url = "jdbc:mysql://localhost:3306/";
        String database = "northwind";
        String user = "student"; // Windows:root
        String password = "student";//Windows:"";
        String sql = "SELECT  customerid cid, companyname comp, city " +
                     " FROM customers " +
                     " WHERE country = ?" +
                     " LIMIT 0,10";
                
        String [] countries = {"Germany","UK","AUSA", "France"};
        try(Connection conn = DriverManager.getConnection(url + database, user, password);
            PreparedStatement prepStm = conn.prepareStatement(sql)){
            System.out.println("Successful database connection!");
            for(String country :countries){
                System.out.println("\n--------" + country + "---------\n");
                prepStm.setString(1, country);
                try(ResultSet result = prepStm.executeQuery()){
                    while( result.next()){
                        System.out.println(result.getRow() + " " +
                                           result.getString(1) + "|" +
                                           result.getString(2) + "|" +
                                           result.getString(3) );
                    }
                }
            }
           
                    
        } catch (SQLException ex) {
            System.err.println("SQL Code:" + ex.getErrorCode());
            System.err.println("SQL State:" + ex.getSQLState());
            System.err.println("SQL Message:" + ex.getMessage());
            ex.printStackTrace();
        }
       
       
    }   
}
