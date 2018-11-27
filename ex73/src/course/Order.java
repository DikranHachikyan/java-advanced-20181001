package course;

import java.sql.Date;

public class Order  implements java.io.Serializable{
    private int orderId;
    private Date orderDate;
    private String customerId;
    
    private static final long serialVersionUID = 1;
    
    public void setOrderId(int orderId){
        this.orderId = orderId;
    }
    public void setOrderDate(Date orderDate){
        this.orderDate = orderDate;
    }
    public void setCustomerId(String customerId){
        this.customerId  = customerId;
    }
    
    public int getOrderId(){
        return this.orderId;
    }
    public Date getOrderDate(){
        return this.orderDate;
    }
    public String getCustomerId(){
        return this.customerId;
    }
    
    //TODO: toString(), equals()
}
