package course;

/**
 *
 * @author wizard
 */
public class Customer implements java.io.Serializable{
    private String customerId;
    private String companyName;
    private String contactName;
    private String country;
    private static final long serialVersionUID = 1;
    
    public void setCustomerId(String customerId){
        this.customerId = customerId;
    }
    
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    
    public void setContactName(String contactName){
        this.contactName = contactName;
    }
    public void setCountry(String country){
        this.country = country;
    }
    
    public String getCustomerId(){
        return this.customerId;
    }
    
    public String getCompanyName(){
        return this.companyName;
    }
    
    public String getContactName(){
        return this.contactName;
    }
    
    public String getCountry(){
        return this.country;
    }
    
    @Override
    public String toString(){
        return this.companyName + "("+this.customerId+")";
    }
    
    @Override
    public boolean equals(Object ob){
        if( ob == null || !(ob instanceof Customer) ) return false;
        Customer c = (Customer)ob;
        
        return c.getCustomerId().equals( getCustomerId());
    }
}
