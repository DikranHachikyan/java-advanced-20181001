package course;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main { //extends Frame{
  
    public Main(){
       JFrame frame = new JFrame("Swing App");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       JButton button1 = new JButton("<html><font color=red><b>Click</b></font> <i>Me</i></html>");
       JButton button2 = new JButton("Button Center");
       
       button1.setActionCommand("open");
       button2.setActionCommand("close");
       
       ActionListener listener1 = (e)->{
           System.out.println("Command:" + e.getActionCommand());
           
       };
       
       ActionListener listener2 = (e)->{
           if( (e.getModifiers() & ActionEvent.CTRL_MASK ) == ActionEvent.CTRL_MASK){
               System.out.println("Ctrl Pressed");
           }
       };
       button1.addActionListener( listener1);
       button2.addActionListener( listener1);
       button2.addActionListener( listener2);
       
       frame.add(button1, BorderLayout.NORTH);
       frame.add(button2, BorderLayout.CENTER);
       
       frame.pack();
       frame.setVisible(true);
       
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