package course;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Point> list = new LinkedList<>();
        
        list.add( new Point(10,20));
        list.add( new Point(100,27));
        list.add( new Point(10,220));
        
        Point p = new Point(100,27);
        if( list.contains( p )){
            System.out.println(p + " is in list!");
        }
        System.out.println("List:" + list);
    }
    
}

