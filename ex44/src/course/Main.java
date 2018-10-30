package course;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
       ExecutorService service = null;
       
       try{
            service  = Executors.newSingleThreadExecutor();
            System.out.println("begin:" + Thread.currentThread().getName());

            service.execute( ()->{
                 System.out.println("first:" + Thread.currentThread().getName());
            });

            service.execute( ()->{
                 System.out.println("second:" + Thread.currentThread().getName());
                 for( int i = 0 ; i < 5; i++ ) System.out.println("i=" + i);
            });

            System.out.println("end");
       }
       finally{
          if( service != null ) service.shutdown(); 
       }
       
    }
    
}
