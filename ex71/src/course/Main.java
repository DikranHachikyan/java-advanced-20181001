package course;


import java.sql.Connection;
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
        String sql = "SELECT * FROM contacts LIMIT 0,10";
           
        try(Connection conn = DriverManager.getConnection(url + database,user,password);
            Statement  stm  = conn.createStatement();
            ResultSet  res  = stm.executeQuery(sql)){
           ResultSetMetaData  resMeta = res.getMetaData();
           
           int colCount = resMeta.getColumnCount();
           System.out.println("Columns:" + colCount);
           
           for( int i = 1 ; i <= colCount; i++){
               System.out.println("Column Name:" + resMeta.getColumnName(i) +
                                  " Column Type:" + resMeta.getColumnType(i) +
                                  " Is Not Required:" + resMeta.isNullable(i) +
                                  " Is Autoincrement:" + resMeta.isAutoIncrement(i));
              // System.out.println("------");
           }
           
        } catch (SQLException ex) {
            System.err.println("SQL Code:" + ex.getErrorCode());
            System.err.println("SQL State:" + ex.getSQLState());
            System.err.println("SQL Message:" + ex.getMessage());
            ex.printStackTrace();
        }
        
       
       
    }   
}
