package course;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Point p1 = new Point(10,20, "P1");
        
        System.out.println("Point 1:" + p1);
        Point p2 = p1.clone();
        p1.setLabel("PP1");
        System.out.println("Point 2:" + p2);
        System.out.println("Point 1:" + p1);
       
    }
    
}

class Point implements Cloneable{
    
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