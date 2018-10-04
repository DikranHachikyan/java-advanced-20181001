package course;

import java.util.Iterator;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
       Queue<String> q  = new ArrayQueue<>(5);
       
       q.add("1");
       q.add("2");
       q.add("3");
       q.add("4");
       q.remove();
       q.add("5");
       q.remove();
       if( q.offer("new element")){
           System.out.println("Element added");
       }
       
       System.out.println("\n\n");
       for( String s: q){
           System.out.println(s);
       }
       
       Iterator<String> iter = q.iterator();
       while( iter.hasNext()){
           
           iter.next();
       }
       
       System.out.println("\n\n");
       String el = q.poll();
       
       for( String s: q){
           System.out.println(s);
           
       }
       
       for( ;q.size() > 0;){
           System.out.println(q.poll());
       }
    }
    
}

