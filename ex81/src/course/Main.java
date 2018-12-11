package course;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static final int BUF_SIZE = 1024;
    
    public static void main(String[] args) {
       int port = 4500;
       String host = "web-dev";
       try( Selector selector = Selector.open();
            DatagramChannel channel = DatagramChannel.open()){
           
            channel.configureBlocking(false);
            channel.socket().bind( new InetSocketAddress(port));
            
            channel.register(selector, SelectionKey.OP_READ, new Data());
            
            System.out.println("Waiting for data...");
            while( true ){
                if( selector.select() == 0 ) continue;
                
                processReadySet( selector.selectedKeys());
            }
       }
       catch(java.io.IOException ex){
           ex.printStackTrace();
       }
       
    }

    private static void processReadySet(Set<SelectionKey> selectedKeys) throws IOException {
        Iterator<SelectionKey> iterator = selectedKeys.iterator();
        
        while( iterator.hasNext()){
            SelectionKey key = (SelectionKey)iterator.next();
            
            iterator.remove();
            
            if( key.isReadable()){
                processRead(key);
            }
            
            if(key.isValid() && key.isWritable()){
                processWrite(key);
            }
        }//has next
    }//

    private static void processRead(SelectionKey key) throws IOException {
        DatagramChannel channel = (DatagramChannel)key.channel();
        Data            data    = (Data)key.attachment();
        
        data.buffer.clear();
        data.remoteAddress = channel.receive(data.buffer);
        
        if( data.remoteAddress != null ){
            key.interestOps(SelectionKey.OP_WRITE);
        }
    }

    private static void processWrite(SelectionKey key) throws IOException {
        DatagramChannel channel = (DatagramChannel) key.channel();
        Data            data    = (Data)key.attachment();
        
        data.buffer.flip();
        int bytes = channel.send(data.buffer, data.remoteAddress);
        if( bytes > 0 ){
            key.interestOps(SelectionKey.OP_READ);
        }
    }

}

class Data {
    public SocketAddress remoteAddress;
    public ByteBuffer buffer = ByteBuffer.allocate(Main.BUF_SIZE);
}