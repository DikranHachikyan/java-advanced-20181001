package course;

import java.util.Iterator;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
       Queue<String> q  = new ArrayQueue<>(String.class,5);
       
       q.add("1");
       q.add("2");
       q.add("3");
       q.add("4");
       q.remove();
       q.add("5");
       q.remove();
       
//       for( String s: q){
//           System.out.println(s);
//       }
       
       Iterator<String> iter = q.iterator();
       while( iter.hasNext()){
           System.out.println(iter.next());
       }
    }
    
}

