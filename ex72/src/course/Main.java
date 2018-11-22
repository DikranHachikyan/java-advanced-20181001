package course;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String database = "northwind";
        String user = "student"; // Windows:root
        String password = "student";//Windows:"";
       
           
        try(Connection conn = DriverManager.getConnection(url + database,user,password)){
           DatabaseMetaData dbmt = conn.getMetaData();
           
           System.out.println("List of tables:");
           ResultSet res = dbmt.getTables("northwind", null , null, null);
           while( res.next()){
               System.out.println(res.getString("TABLE_NAME"));
           }
           
           
           
        } catch (SQLException ex) {
            System.err.println("SQL Code:" + ex.getErrorCode());
            System.err.println("SQL State:" + ex.getSQLState());
            System.err.println("SQL Message:" + ex.getMessage());
            ex.printStackTrace();
        }
        
       
       
    }   
}
