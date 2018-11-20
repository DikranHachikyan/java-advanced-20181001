package course;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

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
        String sql = "{? = call find_avg_orders(?)}";
                
        try(Connection conn = DriverManager.getConnection(url + database, user, password);
            CallableStatement callStm = conn.prepareCall(sql)){
            System.out.println("Successful database connection!");
            
            callStm.setString(2, "ALFKI");
            callStm.registerOutParameter(1, java.sql.Types.DECIMAL);
            callStm.execute();
            System.out.println("Total (ALFKI):" + callStm.getDouble(1));
              
        } catch (SQLException ex) {
            System.err.println("SQL Code:" + ex.getErrorCode());
            System.err.println("SQL State:" + ex.getSQLState());
            System.err.println("SQL Message:" + ex.getMessage());
            ex.printStackTrace();
        }
       
       
    }   
}
