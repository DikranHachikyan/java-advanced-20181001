package course;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
       ExecutorService service = null;
       
       try{
            service  = Executors.newSingleThreadExecutor();
            System.out.println("begin:" + Thread.currentThread().getName());

            Future<Integer> result = service.submit(()->{
                int suma = 1;
                for( int i = 2; i <= 100 ; i++ ) suma += i;
                Thread.sleep(4000);
                return suma;
            });
//            while( ! result.isDone()){
//                System.out.println("...");
//            }
            int sum = result.get(2, TimeUnit.SECONDS);
            System.out.println("end:" + sum);
       }
        catch (InterruptedException | ExecutionException | TimeoutException ex) {
            ex.printStackTrace();
        }       
       finally{
          if( service != null ) service.shutdown(); 
       }
       
    }
    
}
