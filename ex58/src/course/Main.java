package course;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws InterruptedException   {
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService service = Executors.newFixedThreadPool(3);
        ServiceA serviceA = new ServiceA(barrier);
        ServiceB serviceB = new ServiceB(barrier);
        System.out.println("begin:" + Thread.currentThread().getName());

        service.execute(serviceA);
        service.execute(serviceB);
        
        try {
            barrier.await();
        } catch (BrokenBarrierException ex) {
            ex.printStackTrace();
        }
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

class ServiceA implements Runnable{
    private CyclicBarrier barrier;
    
    public ServiceA( CyclicBarrier barrier){
        this.barrier = barrier;
    }
    
    @Override
    public void run() {
        System.out.println("Startinig service A ...");
        try {
            System.out.println("Process data ...");
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Service A finished, waiting for others ...");
        try {
            barrier.await();
        } catch (InterruptedException|BrokenBarrierException ex) {
            ex.printStackTrace();
        }
        System.out.println("Service A:Waiting is over!");
    }
    
}

class ServiceB implements Runnable{
    private CyclicBarrier barrier;
    
    public ServiceB( CyclicBarrier barrier){
        this.barrier = barrier;
    }
    
    @Override
    public void run() {
        System.out.println("Startinig service B ...");
        try {
            System.out.println("Process data ...");
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Service B finished, waiting for others ...");
        try {
            barrier.await();
        } catch (InterruptedException|BrokenBarrierException ex) {
            ex.printStackTrace();
        }
        System.out.println("Service B:Waiting is over!");
    }
    
}