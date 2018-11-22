package course;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String database = "northwind";
        String user = "student"; // Windows:root
        String password = "student";//Windows:"";
        String sql = "INSERT INTO contacts " +
                     " (id, first_name, last_name, phone,company_name,country,status) " +
                     " VALUES " +
                     " (null, 'Maria','Anders','222-11-11', 'MA Ltd','Germany','P') ";
        Connection conn = null;        
        try{
            conn = DriverManager.getConnection(url + database, user, password);
            conn.setAutoCommit(false);
            System.out.println("Isolation Level:" + conn.getTransactionIsolation());
            
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            
            try(Statement stm = conn.createStatement()){
                int num = stm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                System.out.println("Enter (c)ommit, (r)ollback:");
                Scanner scan = new Scanner(System.in);
                char ans = scan.next().charAt(0);
                if( ans == 'c'){
                    conn.commit();
                    System.out.println("num=" + num + " ins=" + stm.getUpdateCount() );
                    
                    try(ResultSet res = stm.getGeneratedKeys()){
                        while(res.next()){
                            System.out.println("contact id=" + res.getInt(1));
                        }
                    }//res
                }// commit
                else if( ans == 'r'){
                    conn.rollback();
                    System.out.println("rollback");
                }
            }//stm
        } catch (SQLException ex) {
            System.err.println("SQL Code:" + ex.getErrorCode());
            System.err.println("SQL State:" + ex.getSQLState());
            System.err.println("SQL Message:" + ex.getMessage());
            ex.printStackTrace();
            conn.rollback();
        }
        finally{
            if( conn != null ) conn.close();
        }
       
       
    }   
}
