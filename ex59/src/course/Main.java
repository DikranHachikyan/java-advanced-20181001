package course;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException   {
        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService service = Executors.newFixedThreadPool(3);
        
        System.out.println("begin:" + Thread.currentThread().getName());
        
        service.execute( new Producer(exchanger));
        service.execute( new Consumer(exchanger));
        
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

class Producer implements Runnable{
    private Exchanger<String> exchanger;
    private String text = "";
    
    public Producer(Exchanger<String> exchanger){
        this.exchanger = exchanger;
    }
    
    @Override
    public void run(){
        char c = 'A';
        
        for( int i = 0 ; i < 4; i++){
            for(int j = 0 ; j < 5; j++){
                text += c++; 
            }
            try {
                text = exchanger.exchange(text);
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}


class Consumer implements Runnable{
    private Exchanger<String> exchanger;
    private String text = "";
    
    public Consumer(Exchanger<String> exchanger){
        this.exchanger = exchanger;
    }
    
    @Override
    public void run(){
        for( int i = 0 ; i < 4; i++){
            try {
                System.out.println("waiting...");
                text = exchanger.exchange(text);
                System.out.println("Received:" + text);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
        }
    }
}