package course;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;


public class Main {
    public static final int BUF_SIZE = 1024;
    public static void main(String[] args) {
       int port = 4500;
       String host = "web-dev";
       try(Selector selector = Selector.open();
           SocketChannel socket = SocketChannel.open()){
           
           socket.configureBlocking(false);
           socket.connect(new InetSocketAddress( InetAddress.getByName(host),
                                                 port));
           int operations = SelectionKey.OP_CONNECT | 
                            SelectionKey.OP_READ |
                            SelectionKey.OP_WRITE;
           socket.register(selector, operations);
           
           while(true){
               //тук блокира
               if( selector.select() > 0 ){
                   if( processReadySet( selector.selectedKeys()) ) break;
               }
           }
       }
       catch(java.io.IOException ex){
           ex.printStackTrace();
       }
    }

    private static boolean processReadySet(Set<SelectionKey> readySet) {
        SelectionKey key = null;
        Iterator<SelectionKey> iterator = readySet.iterator();
        while( iterator.hasNext()){
            key  = iterator.next();
            
            iterator.remove();
            
            if( key.isConnectable()){
                //not connected ->exit
                if( !processConnect(key) ) return true;
            }
        }
        return false;
    }

    private static boolean processConnect(SelectionKey key) {
        SocketChannel socket = (SocketChannel)key.channel();
        try {
            while( socket.isConnectionPending() ){
                socket.finishConnect();
            }
        } catch (IOException ex) {
            //отменя регистрацията
            key.cancel();
            ex.printStackTrace();
            return false;
            
        }
        return true;
    }

}
