package course;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try( ServerSocket server = new ServerSocket(4500
                                                  , 100
                                                  , InetAddress.getByName("web-site")))
        {
            System.out.println("Server started at " + server);
            
            while(true){
                System.out.println("Waiting for connections...");
                final Socket activeSocket = server.accept();
                System.out.println("Received connection from " + activeSocket);
                
                Runnable runnable = ()->handleClientRequest(activeSocket);
                
                new Thread(runnable).start();
                
            }
            
        }
        catch(java.io.IOException ex){
            ex.printStackTrace();
        }
    } 
    
    public static void handleClientRequest(Socket socket){
        try( BufferedReader socketReader = new BufferedReader(
                        new InputStreamReader( socket.getInputStream())
                    );
             BufferedWriter socketWriter = new BufferedWriter(
                        new OutputStreamWriter( socket.getOutputStream())
                    ); 
            ){
            
            String inputMessage = null;
            while( (inputMessage = socketReader.readLine()) != null){
                System.out.println("Received from client:" + inputMessage);
                
                if( inputMessage.equalsIgnoreCase("quit")){
                    socket.shutdownInput();
                    socket.shutdownOutput();
                    socket.close();
                    break;
                }
                String outputMessage = inputMessage.toUpperCase();
                socketWriter.write(outputMessage);
                socketWriter.newLine();
                socketWriter.flush();
            }//while
        }//try
        catch(java.io.IOException ex){
            ex.printStackTrace();
        }
    }//handle
}
