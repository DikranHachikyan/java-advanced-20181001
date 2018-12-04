package course;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
    public static final int PACKET_SIZE = 10;
    public static void main(String[] args) {
        try( DatagramSocket udpSocket = new DatagramSocket(4500,
                                      InetAddress.getByName("web-dev"))){
            
            System.out.println("UDP Server: socket " + 
                                udpSocket.getLocalSocketAddress() + "...");
            while(true){
                System.out.println("Waiting from udp packet ...");
                
                DatagramPacket packet = new DatagramPacket( new byte[PACKET_SIZE],PACKET_SIZE);
                
                udpSocket.receive(packet);
                
                showPacketInfo(packet);
                
                udpSocket.send(packet);
            }
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
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
