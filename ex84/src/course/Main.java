package course;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main { //extends Frame{
    
    private Frame mainFrame = null;
    
    public Main(){
        mainFrame = new Frame("First App");
        //mainFrame.setSize(300,400);
        mainFrame.setLayout( new GridLayout(1,0));
        
        Button button1 = new Button("Click Me First");
        Button button2 = new Button("Click Me 2");
        Button button3 = new Button("Click Me 3");
        Button button4 = new Button("Click Me 3");
        Button button5 = new Button("Click Me 3");
        
        mainFrame.add(button1);
        mainFrame.add(button2);
        mainFrame.add(button3);
        mainFrame.add(button4);
        mainFrame.add(button5);
        
        mainFrame.addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing( WindowEvent e){
                System.exit(0);
            }
        });
        
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
       new Main();
    }//

}