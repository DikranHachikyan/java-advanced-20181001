package course;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    public static void main(String[] args)  {
        String url = "jdbc:mysql://localhost:3306/";
        String database = "northwind";
        String user = "student"; // Windows:root
        String password = "student";//Windows:"";
        String sql = "SELECT * FROM contacts WHERE company_name LIKE 'C%'";
                
        try(Connection conn = DriverManager.getConnection(url + database, user, password);
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_UPDATABLE);
            ResultSet result = stm.executeQuery(sql)){
            System.out.println("Successful database connection!");
            System.out.println("Before update/delete");
            while(result.next()){
                System.out.println(result.getString("id") +"|" +
                                   result.getString("last_name") + "|"+
                                   result.getString("status"));
            }
            
            result.absolute(1);
            do{
                if( result.getString("last_name").equals("Barr")){
                    result.deleteRow();
                }
                else{
                    result.updateString("status", "B");
                }
                result.updateRow();
            }
            while(result.next()); 
            
            System.out.println("After update/delete");
            result.beforeFirst();
            while(result.next()){
                System.out.println(result.getString("id") +"|" +
                                   result.getString("last_name") + "|"+
                                   result.getString("status"));
            }
        } catch (SQLException ex) {
            System.err.println("SQL Code:" + ex.getErrorCode());
            System.err.println("SQL State:" + ex.getSQLState());
            System.err.println("SQL Message:" + ex.getMessage());
            ex.printStackTrace();
        }
       
       
    }   
}
