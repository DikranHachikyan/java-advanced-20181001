package course;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Random;


public class Main {

    public static void main(String[] args) {
       Point [] points = new Point[5];
       Random rand = new Random(0);
       
       Arrays.parallelSetAll( points , (i)->new Point( rand.nextInt(100), rand.nextInt(100)));
       System.out.println("Points:" + Arrays.toString(points) );
       try{
            try( DataOutputStream dout = new DataOutputStream( new FileOutputStream("points.bin"))){
                for( Point p : points){
                    dout.writeInt(p.getX());
                    dout.writeInt(p.getY());
                }
            }//try data
            
            try( RandomAccessFile ras = new RandomAccessFile("points.bin","r")){
                int size = (int)( ras.length()/ Point.POINT_SIZE ); //N Point
                Point [] newPoints = new Point[size];
                
                for( int i = 0; i < size ; i++){
                    newPoints[i] = new Point();
                    ras.seek( (size -i - 1) * Point.POINT_SIZE );
                    newPoints[i].setX( ras.readInt());
                    newPoints[i].setY( ras.readInt());
                }
                
                System.out.println("New Points:" + Arrays.toString(newPoints));
            }
       }
       catch( IOException  e){
           System.err.println("Exception:" + e);
       }
       
    }
    
}

class Point {
    public static final int POINT_SIZE = Integer.BYTES * 2;
    private int x;
    private int y;
    
    public Point( int x, int y){
        super();
        this.x = x;
        this.y = y;
    }
    
    public Point(){
        this(0,0);
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
    @Override
    public String toString(){
        return "("+ getX()+", "+ getY()+")";
    }
            
}