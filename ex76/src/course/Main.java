package course;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try(Socket socket = new Socket("web-dev", 4500);
            BufferedReader socketReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
            );
            BufferedWriter socketWriter = new BufferedWriter(
                        new OutputStreamWriter( socket.getOutputStream())
            );
            BufferedReader console = new BufferedReader(
                        new InputStreamReader(System.in) 
            );){
            
            System.out.println("Started client at " + socket.getLocalSocketAddress());
            
            String prompt = "Eneter a message (quit for exit):";
            
            String msg = null;
            
            System.out.println(prompt);
            while( (msg = console.readLine()) != null){
                //Send
                socketWriter.write(msg);
                socketWriter.newLine(); //socketWriter.write("\n");
                socketWriter.flush();
                if( msg.equalsIgnoreCase("quit")){
                    break;
                }
                //Receive
                String inputMessage = socketReader.readLine();
                System.out.println("Server says:" + inputMessage );
                
                System.out.println(prompt);
                
            }
            
        }
        catch( java.io.IOException ex){
            ex.printStackTrace();
        }
    } 
    
}
