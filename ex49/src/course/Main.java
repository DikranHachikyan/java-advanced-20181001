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
       ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();;
       

        System.out.println("begin:" + Thread.currentThread().getName());

        ScheduledFuture<?> t2= service.scheduleAtFixedRate(()->{
            Random rand = new Random(0);
            System.out.println("Get Random Number:"+ rand.nextInt(100));
        }, 5, 1, TimeUnit.SECONDS);

        System.out.println("scheduled t2:");

        Runnable stopTask = ()->{
          service.shutdown();
          System.out.println("Finished ...");
        };
        service.schedule(stopTask, 10, TimeUnit.SECONDS);
        //t2.get();
        System.out.println("delay:" + t2.getDelay(TimeUnit.SECONDS));

        System.out.println("end:");
      
       try{
           if(! service.awaitTermination(15, TimeUnit.SECONDS)){
              service.shutdownNow();
              System.out.println("Service terminated");
          } 
       }
       catch(InterruptedException ex){
           ex.printStackTrace();
       }
       
    }
    
}
