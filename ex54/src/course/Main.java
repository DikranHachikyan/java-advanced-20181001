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
        List<Callable<Integer> > tasks = new ArrayList<>();
        ArrayCalculator ac = new ArrayCalculator();
        
        tasks.add(()->{
                ac.sum();
                System.out.println("sum = " + ac.getResult());
                return null;
            });
        tasks.add( ()->{
                ac.mult();
                System.out.println("mult = " + ac.getResult());
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
class ArrayCalculator {
    private int [] array = new int[]{1,2,3,4,5};
    private int result = 0;
    private int s;
    private Object lock = new Object();
    public  void sum() throws InterruptedException{
        synchronized(lock){
            s = 0;
            result = 0;
            for(int i = 0 ; i < array.length ; i++ ){
                s += array[i];
                System.out.println(Thread.currentThread().getName() + " s=" + s);
                Thread.sleep(10);
            }
            result = s;
        }
    }
    
    public void mult() throws InterruptedException{
        synchronized(lock){
            s = 1;
            result = 0;
            for( int i = 0; i < array.length ; i++ ){
                s *= array[i];
                System.out.println(Thread.currentThread().getName() + " s=" + s);
                Thread.sleep(10);
            }
            result = s;
        }
    }
    
    public synchronized int getResult(){
        return result;
    }
}