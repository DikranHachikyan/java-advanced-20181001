package course;

import java.awt.Button;
import java.awt.Frame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main { //extends Frame{
    
    private Frame mainFrame = null;
    
    public Main(){
        mainFrame = new Frame("First App");
        //mainFrame.setSize(300,400);
        MainPanel mp = new MainPanel();
        mainFrame.add(mp);
        
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