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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args)   {
        ExecutorService service = Executors.newFixedThreadPool(10);
        List< Callable<Integer> > tasks = new ArrayList<>();
       tasks.add( ()->{
           int fact = 1;
           System.out.println(Thread.currentThread().getName() + " Calculate factoriel:");
           for( int i = 2; i <= 5; i++ ){
               fact *= i;
               Thread.sleep(10);
           }
           return fact;
       });
       tasks.add( ()->{
           int suma = 1;
           System.out.println(Thread.currentThread().getName() + " Calculate sum: 1..100");
           for( int i = 2; i <= 100 ; i++ ) suma += i;
           return suma;
       });
       
       tasks.add( ( )->{
           Random rand = new Random(0);
           System.out.println(Thread.currentThread().getName() +" Get Random Number:");
           int rnd = rand.nextInt(100);
           Thread.sleep(1200);
           return rnd;
       });
       
       tasks.add( ()->{
           int suma = 2;
           System.out.println(Thread.currentThread().getName() + " Calculate sum: 2+4+...+98+100");
           for( int i = 4; i <= 100 ; i += 2 ) suma += i;
           return suma;
       });
        System.out.println("begin:" + Thread.currentThread().getName());
        try{
            for( Callable<?> c: tasks ){
                Future<?> res = service.submit(c);
                System.out.println("result:" + res.get());
            }

            System.out.println("end:");
            
        }
        catch (InterruptedException| ExecutionException  ex) {
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
