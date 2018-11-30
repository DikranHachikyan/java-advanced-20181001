package course;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String database = "northwind";
        String user = "student"; // Windows:root
        String password = "student";//Windows:"";
                 
        try(Connection conn = DriverManager.getConnection(url + database,user,password)){
            Customers customers = new Customers(conn);
            Orders    orders    = new Orders(conn);
            
            customers.read(" country = 'UK' ");
            
            orders.read( customers.getCustomers());
            
           
            for( Customer c: orders.getOrders().keySet()){
                System.out.println("======== " + c + "========");
                for( Order order: orders.getOrders().get(c)){
                    System.out.println("Order:" + order.getOrderId() + 
                                        " "     + order.getOrderDate());
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

class Orders {
    private final HashMap<Customer, List<Order>> orders = new HashMap<>();
    private String sql ="SELECT orderid " +
                        ", orderdate " +
                        ", customerid " +
                        " FROM orders " +
                        " WHERE customerid = ?";
    private PreparedStatement pstm = null;
    private Connection conn = null;
    
    public Orders(Connection conn) throws SQLException{
        this.conn = conn;
        this.pstm = conn.prepareStatement(sql);
    }
    
    public void read( List<Customer> custs){
        try{
            this.orders.clear();
            for( Customer c : custs){
                pstm.setString(1, c.getCustomerId());
                ResultSet res = pstm.executeQuery();
                ArrayList<Order> ords = new ArrayList<>();
                while( res.next()){
                    Order order = new Order();
                    order.setOrderId( res.getInt("orderid"));
                    order.setOrderDate( res.getObject("orderdate", java.sql.Date.class));
                    order.setCustomerId( res.getString("customerid"));
                    ords.add(order);
                }
                this.orders.put(c, ords);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public Map<Customer,List<Order>> getOrders(){
        return this.orders;
    }
}

//============================================
class Customers {
    private final ArrayList<Customer> customers = new ArrayList<>();
    private String sql = "SELECT customerid " +
                        ", companyName " +
                        ", contactName " +
                        ", country  " +
                        " FROM customers" + 
                        " WHERE 1=1 ";
    private Connection conn = null;
    
    public Customers(Connection conn){
        this.conn = conn;
    }
    
    public void read(String where) throws SQLException{
        String query = sql + ((where != null && where.trim().length() > 0)?
                                " AND " + where: "");
        try( Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery(query)){
             this.customers.clear();
             while( res.next()){
                 Customer c = new Customer();
                 c.setCustomerId( res.getString("customerid"));
                 c.setCompanyName( res.getString("companyName"));
                 c.setContactName( res.getString("contactName"));
                 c.setCountry(res.getString("country"));
                 
                 this.customers.add(c);
             }
        }
    }
    
    public List<Customer> getCustomers(){
        return this.customers;
    }
}