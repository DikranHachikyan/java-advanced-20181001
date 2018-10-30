package course;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Point p1 = new Point(10,20,"P1");
        
        System.out.println("Point 1:" + p1);
        Point p2 = p1.clone();
        System.out.println("Point 2:" + p2);
        p1.setLabel("PP1");
        System.out.println("Point 1:" + p1);
        System.out.println("Point 2:" + p2);
        
                
    }
    
}

class Point implements  Serializable{
    private static final long serialVersionUID = 0xFFL;
    private int x;
    private int y;
    private StringBuffer label;
    
    public Point( int x, int y, String label){
        super();
        this.x = x;
        this.y = y;
        this.label = new StringBuffer();
        setLabel(label);
    }
    
    public Point clone() throws CloneNotSupportedException{
        Point ob = null;
        try{
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try( ObjectOutputStream oos = new ObjectOutputStream(bout)){
                oos.writeObject(this);
            }
            ByteArrayInputStream bis = new ByteArrayInputStream( bout.toByteArray());
            try( ObjectInputStream ois = new ObjectInputStream(bis)){
                ob =(Point)ois.readObject();
            }
        }
        catch(IOException | ClassNotFoundException e){
            System.err.println("Exception:" + e);
            CloneNotSupportedException ex = new CloneNotSupportedException();
            ex.initCause(e);
            throw ex;
        }
        return ob;
    }
    public void show(){
        System.out.println("Hello");
    }
    public Point(){
        this(0,0,"");
    }
    
    public void setX( int x ){
        this.x = x;
    }
    
    public void setY( int y ){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setLabel(String label){
        this.label.append(label);
    }
    public String getLabel(){
        return label.toString();
    }
    
    @Override
    public String toString(){
        return getLabel() + "("+ getX()+", "+ getY()+")";
    }
            
}