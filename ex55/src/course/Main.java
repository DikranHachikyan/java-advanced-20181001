package course;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args)   {
        ExecutorService service = Executors.newFixedThreadPool(3);
        List<Callable<Integer> > tasks = new ArrayList<>();
        
        Counter cnt = new Counter();
        tasks.add(()->{
                cnt.incrementAndPrint();
                return null;
            });
        tasks.add( ()->{
                cnt.incrementAndPrint();
                return null;
            });
        tasks.add(()->{
                cnt.incrementAndPrint();
                return null;
            });
        tasks.add( ()->{
                cnt.incrementAndPrint();
                return null;
            });
        tasks.add(()->{
                cnt.incrementAndPrint();
                return null;
            });
        tasks.add( ()->{
                cnt.incrementAndPrint();
                return null;
            });
        try{
            System.out.println("begin:" + Thread.currentThread().getName());
            
            
            service.invokeAll(tasks);
          
            
            System.out.println("end:");
            
        }
        catch (InterruptedException ex) {
           ex.printStackTrace();
        }        
        finally{
            service.shutdown();
        }
        
       try{
           if(! service.awaitTermination(15, TimeUnit.SECONDS)){
              service.shutdownNow();
              if(! service.awaitTermination(15, TimeUnit.SECONDS)){
                  System.err.println("Service did not terminate");
              }
              System.out.println("Service terminated");
          } 
       }
       catch(InterruptedException ex){
           ex.printStackTrace();
       }
       
    }   
}

//Thread Unsafe
class Counter {
    //private int count = 0;
    private AtomicInteger count = new AtomicInteger(0);
    public void incrementAndPrint() throws InterruptedException{
        Thread.sleep(10);
        System.out.println( count.incrementAndGet() + " " + Thread.currentThread().getName());
    }
}