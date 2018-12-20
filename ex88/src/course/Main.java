package course;


import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main { //extends Frame{
    
    public Main(){
        Model model = new AppModel();
        Controller controller = new AppController(model);
        View       view = new AppView(model,controller);
        
    }
    
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
       //1.
       Runnable runner = ()->{
            new Main();  
       };
       
       //2.
       
       //SwingUtilities.invokeAndWait(runner);
       SwingUtilities.invokeLater(runner);
       System.out.println("After Invoke Method");
    }//

}