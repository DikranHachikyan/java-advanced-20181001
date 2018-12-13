package course;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main { //extends Frame{
    
    private Frame mainFrame = null;
    
    public Main(){
        mainFrame = new Frame("First App");
        mainFrame.setSize(300,400);
        mainFrame.setLayout( new FlowLayout());
        
        Button button1 = new Button("Click Me First");
        Button button2 = new Button("Click Me 2");
        Button button3 = new Button("Click Me 3");
        
        mainFrame.add(button1);
        mainFrame.add(button2);
        mainFrame.add(button3);
        
        mainFrame.addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing( WindowEvent e){
                System.exit(0);
            }
        });
        mainFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
       new Main();
    }//

}