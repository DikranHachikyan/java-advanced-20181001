package course;

import java.net.InetAddress;

public class Main {

    public static void main(String[] args) {
        printAddress("expert-bg.org");
        
        printAddress(null);
        
        printAddress("::1");
        
        printAddress("192.168.1.254");
        
        printAddress("192.168.122.232");
        printAddress("fe80::8837:7889:2fd9:1377");
        
        
    } 
    
    public static void printAddress(String host){
        System.out.println("Host: " + host);
        
        try {
            InetAddress address = InetAddress.getByName(host);
            
            System.out.println("Host IP:" + address.getHostAddress());
            System.out.println("Cannonical Name:" + address.getCanonicalHostName());
            int timeout = 10000;
            System.out.println("is reachable:" + address.isReachable(timeout));
            System.out.println("is loopback:" + address.isLoopbackAddress());
            System.out.println("is link local:" + address.isLinkLocalAddress());
            System.out.println("==============");
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
    }
}
