package course;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Main {
    public static final int PACKET_SIZE = 10;
    public static void main(String[] args) {
        try( DatagramSocket udpSocket = new DatagramSocket();
             BufferedReader console = new BufferedReader(
                            new InputStreamReader( System.in)
             )){
            
            String msg = null;
            String prompt = "Enter a message (quit to exit):";
            
            System.out.println(prompt);
            while( (msg = console.readLine()) != null){
                DatagramPacket packet = getPacket(msg);
                
                udpSocket.send(packet);
                //чака за пакет
                udpSocket.receive(packet);
                
                showPacketInfo(packet);
                if( msg.equalsIgnoreCase("quit")){
                    break;
                }
                System.out.println(prompt);
            }
            
        }
        catch( java.io.IOException ex){
            ex.printStackTrace();
        }
    } 
    
    public static DatagramPacket getPacket( String message ) throws UnknownHostException {
        byte [] msgBuffer = message.getBytes();
        int length = msgBuffer.length;
        
        if( length > PACKET_SIZE){
            length = PACKET_SIZE;
            System.out.println("message trunkated to :" + new String(msgBuffer, 0, length));
        }
        
        DatagramPacket packet = new DatagramPacket(msgBuffer, length);
        
        int serverPort = 4500;
        String serverName = "web-dev";
        InetAddress serverAddress = InetAddress.getByName(serverName);
        
        packet.setAddress(serverAddress);
        packet.setPort(serverPort);
        return packet;
    }
    
    public static void showPacketInfo( DatagramPacket packet){
        byte [] msgBuffer = packet.getData();
        int  length = packet.getLength();
        int  offset = packet.getOffset();
        int  remotePort = packet.getPort();
        InetAddress remoteAddress = packet.getAddress();
        String msg = new String(msgBuffer, offset, length);
        
        System.out.println("Received a packet (IP:"+ remoteAddress   +
                                               ", port:"+ remotePort +
                                               ", message:" + msg    + ")");
        
    }
}
