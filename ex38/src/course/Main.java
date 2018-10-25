package course;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Point [] points = new Point[5];
        Random rand = new Random(0);
        Arrays.parallelSetAll(points, (i)->new Point(rand.nextInt(100), rand.nextInt(100), "P"+ i));
        System.out.println("Points:" + Arrays.toString(points));
        
        ArrayQueue<Point> aqp = new ArrayQueue<>(100);
        aqp.addAll( Arrays.asList(points));
        Iterator<Point> it = aqp.iterator();
        while( it.hasNext()){
            System.out.print(it.next() + " ");
        }
        System.out.print("\n");
        
        try( ObjectOutputStream obs = new ObjectOutputStream( new FileOutputStream("points.q"))){
            obs.writeObject(aqp);
        }
        
        System.out.println("From output stream:");
        
        try( ObjectInputStream ibs = new ObjectInputStream( new FileInputStream("points.q"))){
            ArrayQueue<Point> aqp2 = (ArrayQueue<Point>)ibs.readObject();
            
            Iterator<Point> it2 = aqp2.iterator();
            while( it2.hasNext()){
                System.out.print(it2.next() + " ");
            }
            System.out.print("\n");
        }
        
        
                
    }
    
}



class ArrayQueue<E> extends AbstractQueue<E> implements Serializable{
    private static final long serialVersionUID = 1L;
    transient private E [] elements;
    private int head = 0;
    transient private int tail = 0;
    transient private int count = 0;
    private int modcount = 0; /*брояч на промени в данните на опашката */
    
    public ArrayQueue( int capacity){
        super();
        Object [] elms = new Object[capacity];
        elements = (E[])elms;
    }
    
    private void writeObject(ObjectOutputStream out) 
            throws IOException{
        out.defaultWriteObject();
        out.writeInt(count);
        E el;
        while( (el = poll()) != null){
            out.writeObject(el);
        }
        out.writeObject(null);
    }
    
    private void readObject(ObjectInputStream in) 
            throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        int n = in.readInt();
        Object [] elms = new Object[n];
        elements = (E[])elms;
        E el;
        while( (el = (E) in.readObject()) != null){
            offer(el);
        }
    }
    
    @Override
    public boolean offer( E newElement){
        if( newElement == null ) return false;
        if( count < elements.length ){
            elements[tail] = newElement;
            tail = (tail + 1) % elements.length;
            count++;
            modcount++;
            return true;
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public E peek(){
        if( count == 0 ) return null;
        return (E)elements[head];
    }
    
    @Override
    public E poll(){
        if( count == 0 ) return null;
        E ret = peek();
        elements[head] = null;
        head = ( head + 1) % elements.length;
        count--;
        modcount++;
        return ret;
    }
    
    @Override
    public int size(){
        return count;
    }
    
    @Override
    public Iterator<E> iterator(){
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<E>{
        private int offset = 0;
        private int expectedModeCount = modcount;
        
        public QueueIterator(){
            super();
        }
        
        @Override
        public boolean hasNext(){
            if( modcount != expectedModeCount )
                throw new ConcurrentModificationException();
            return offset < count;
        }
        
        @Override
        public E next(){
            if( !hasNext() ) throw new NoSuchElementException();
            @SuppressWarnings("unchecked")
            E ret =(E) elements[ (head + offset) % elements.length ];
            offset++;
            return ret;
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