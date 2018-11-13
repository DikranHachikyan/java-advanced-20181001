package course;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException   {
        Phaser phaser = new Phaser(1);
        ExecutorService service = Executors.newFixedThreadPool(3);
        int currentPhase = 0;
        System.out.println("Start processing...:" + Thread.currentThread().getName());
        service.submit(new DbService(phaser, "A"));
        service.submit(new DbService(phaser, "B"));
        service.submit(new DbService(phaser, "C"));
        
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase:" + currentPhase + " completed");
        
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase:" + currentPhase + " completed");
        
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase:" + currentPhase + " completed");
        
        phaser.arriveAndDeregister();
        if(phaser.isTerminated()){
            System.out.println("End processing");
        }
        service.shutdown();
            
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

class DbService implements Runnable{
    private Phaser phaser;
    private String name;
    
    public DbService( Phaser phaser , String name){
        this.phaser = phaser;
        this.name   = name;
        phaser.register();
    }
    
    @Override
    public void run(){
        System.out.println("Thread " + name + ": phase 0");
        phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep( (long)(200 * Math.random())+ 5);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Thread " + name + ": phase 1");
        phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep( (long)(200 * Math.random())+ 5);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Thread " + name + ": phase 2");
        try {
            Thread.sleep( (long)(200 * Math.random()) + 5);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        phaser.arriveAndDeregister();
    }
}