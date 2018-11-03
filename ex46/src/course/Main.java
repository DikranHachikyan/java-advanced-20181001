package course;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) {
       ExecutorService service = null;
       List< Callable<Integer> > tasks = new ArrayList<>();
       tasks.add( ()->{
           int fact = 1;
           System.out.println("Calculate factoriel:");
           for( int i = 2; i <= 5; i++ ){
               fact *= i;
               Thread.sleep(10);
           }
           return fact;
       });
       tasks.add( ()->{
           int suma = 1;
           System.out.println("Calculate sum: 1..100");
           for( int i = 2; i <= 100 ; i++ ) suma += i;
           return suma;
       });
       
       tasks.add( ( )->{
           Random rand = new Random(0);
           System.out.println("Get Random Number:");
           int rnd = rand.nextInt(100);
           Thread.sleep(1200);
           return rnd;
       });
       
       tasks.add( ()->{
           int suma = 2;
           System.out.println("Calculate sum: 2+4+...+98+100");
           for( int i = 4; i <= 100 ; i += 2 ) suma += i;
           return suma;
       });
       try{
            service  = Executors.newSingleThreadExecutor();
            System.out.println("begin:" + Thread.currentThread().getName());

            List< Future<Integer> > results = service.invokeAll(tasks, 200,TimeUnit.MILLISECONDS);
            
            for( Future<?> r:results){
                if( !r.isCancelled()){
                    System.out.println("result:" + r.get());
                }
            }  
            System.out.println("end:");
       }
        catch (InterruptedException |ExecutionException|NullPointerException| RejectedExecutionException ex) {
            ex.printStackTrace();
        }       
       finally{
          if( service != null ) service.shutdown(); 
       }
       
    }
    
}
