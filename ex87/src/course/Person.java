/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course;

/**
 *
 * @author wizard
 */
public class Person {
    private String firstName;
    private String lastName;
    
    public Person(){
        this("","");
    }
    
    public Person(String firstName, String lastName){
        super();
        setFirstName(firstName);
        setLastName(lastName);
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFirstName(){ return firstName;}
    public String getLastName(){ return lastName;}
    
    @Override
    public String toString(){
        return getFirstName() + " " + getLastName();
    }
}
