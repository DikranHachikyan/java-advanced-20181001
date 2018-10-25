package course;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Point p = new Point(10,20,"P1");
        
        System.out.println("p:" + p);
        try( ObjectOutputStream obs = new ObjectOutputStream(
                                            new FileOutputStream("point.obj"))){
            obs.writeObject(p);
        }
        
        try(ObjectInputStream ibs = new ObjectInputStream( new FileInputStream("point.obj"))){
            Point ps = (Point)ibs.readObject();
            System.out.println("ps:" + ps);
        }
       
    }
    
}

class Point implements Cloneable, Serializable{
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
        Point c = (Point)super.clone();
        c.label = new StringBuffer(this.label);
        return c;
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