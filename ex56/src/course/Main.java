package course;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args)   {
        Semaphore sm = new Semaphore(1,true);
        UpCounter up = new UpCounter(sm,"UP:");
        DownCounter down = new DownCounter(sm, "Down:");
        ExecutorService service = Executors.newFixedThreadPool(3);
       
        try{
            System.out.println("begin:" + Thread.currentThread().getName());
            
            service.submit(()->{
                try {
                    up.count();
                } catch (InterruptedException ex) {
                  ex.printStackTrace();
                }
            });
            service.submit(()->{
                try {
                    down.count();
                } catch (InterruptedException ex) {
                  ex.printStackTrace();
                }
            });
            
            sm.acquire();
            service.shutdown();
            sm.release();
            System.out.println("end:");
            
        }
        catch (InterruptedException ex) {
           ex.printStackTrace();
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

class SharedResource{
    public static int count = 0;
}

class UpCounter {
    private Semaphore semaphore;
    private String name;
    
    public UpCounter(Semaphore semaphore, String name){
        this.name = name;
        this.semaphore = semaphore;
    }
    
    public void count() throws InterruptedException{
        System.out.println( name + " is waiting....");
        semaphore.acquire();
        System.out.println( name + " aquired a lock");
        for( int i = 0 ; i < 5; i++ ){
            System.out.println(name + ":" +(++SharedResource.count) );
            Thread.sleep(10);
        }
        semaphore.release();
        System.out.println( name + " released the lock");
    }
}

class DownCounter {
    private Semaphore semaphore;
    private String name;
    
    public DownCounter(Semaphore semaphore, String name){
        this.name = name;
        this.semaphore = semaphore;
    }
    
    public void count() throws InterruptedException{
        System.out.println( name + " is waiting....");
        semaphore.acquire();
        System.out.println( name + " aquired a lock");
        for( int i = 0 ; i < 5; i++ ){
            System.out.println(name + ":" +(--SharedResource.count) );
            Thread.sleep(10);
        }
        semaphore.release();
        System.out.println( name + " released the lock");
    }
}