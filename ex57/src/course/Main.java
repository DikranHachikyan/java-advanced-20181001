package course;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException   {
        CountDownLatch cdl = new CountDownLatch(5);
        UpCounter up = new UpCounter(cdl,"UP:");
        ExecutorService service = Executors.newFixedThreadPool(3);

        System.out.println("begin:" + Thread.currentThread().getName());

        service.submit(()->{
            try {
                up.count();
            } catch (InterruptedException ex) {
              ex.printStackTrace();
            }
        });
        
        cdl.await();

        service.shutdown();
        System.out.println("end:");
            
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
    private CountDownLatch latch;
    private String name;
    
    public UpCounter(CountDownLatch latch, String name){
        this.name = name;
        this.latch = latch;
    }
    
    public void count() throws InterruptedException{
        for(int i = 0; i < 5; i++){
            System.out.println(name + ":" +(++SharedResource.count) );
            latch.countDown();
            Thread.sleep(10);
        }
        System.out.println( name + " released the lock");
    }
}

class DownCounter {
    private CountDownLatch latch;
    private String name;
    
    public DownCounter(CountDownLatch latch, String name){
        this.name = name;
        this.latch = latch;
    }
    
    public void count() throws InterruptedException{
        for(int i = 0; i < 8; i++){
            System.out.println(name + ":" +(--SharedResource.count) );
            latch.countDown();
            Thread.sleep(10);
        }
        System.out.println( name + " released the lock");
    }
}