package course;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) {
       ScheduledExecutorService service = null;
       
       try{
            service  = Executors.newSingleThreadScheduledExecutor();
            
            System.out.println("begin:" + Thread.currentThread().getName());
            
            ScheduledFuture<?> t1= service.schedule(()->{
                int suma = 1;
                System.out.println("Calculate sum: 1..100");
                for( int i = 2; i <= 100 ; i++ ) suma += i;
                return suma;
            }, 5, TimeUnit.SECONDS);
            System.out.println("scheduled t1:");
            
            ScheduledFuture<?> t2= service.schedule(()->{
                Random rand = new Random(0);
                System.out.println("Get Random Number:");
                int rnd = rand.nextInt(100);
                return rnd;
            }, 1, TimeUnit.SECONDS);
            System.out.println("scheduled t2:");
            
            System.out.println("result t1:" + t1.get() + " dalay:" + t1.getDelay(TimeUnit.SECONDS));
            System.out.println("result t2:" + t2.get() + " dalay:" + t2.getDelay(TimeUnit.SECONDS));
            System.out.println("end:");
       }
        catch (InterruptedException|ExecutionException ex) {
            ex.printStackTrace();
        }       
       finally{
          if( service != null ) service.shutdown(); 
       }
       
    }
    
}
