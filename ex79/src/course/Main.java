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
           ServerSocketChannel  channel = ServerSocketChannel.open();){
           
           channel.configureBlocking(false);
           channel.socket().bind( new InetSocketAddress( 
                                             InetAddress.getByName(host),port));
           //регистрация на канала за ACCEPT
           channel.register(selector, SelectionKey.OP_ACCEPT);
           System.out.println("Waiting for connections...");
           while(true){
               //блокиращо чакане за канали готови да приемат операцията
               if( selector.select() <= 0) continue;
               //Process requests...
               processReadySet(selector.selectedKeys());
           }
           
       }
       catch(java.io.IOException ex){
           ex.printStackTrace();
       }
    }

    private static void processReadySet(Set<SelectionKey> readySet) throws java.io.IOException{
        SelectionKey key = null;
        Iterator<SelectionKey> iterator = readySet.iterator();
        while( iterator.hasNext()){
            //взимаме поредният ready елемент
            key = iterator.next();
            
            iterator.remove();
            
            if( key.isAcceptable()){
                processAccept(key);
            }
            
            if( key.isReadable()){
                processRead(key);
                
            }
            
            if( key.isValid() && key.isWritable()){
                processWrite(key);
            }
        }//has next
       
    }

    //Ако този метод е извикан, означава, че имаме заявка за свързване
    private static void processAccept(SelectionKey key) throws IOException {
        ServerSocketChannel  channel = (ServerSocketChannel) key.channel();
        //приемаме заявката за свързване
        SocketChannel socket = channel.accept();
        //разрешаваме неблок. изпълнение
        socket.configureBlocking(false);
        
        //регистрираме сокета за четене
        socket.register( key.selector(),SelectionKey.OP_READ |SelectionKey.OP_WRITE,
                ByteBuffer.allocate(BUF_SIZE));
    }

    private static void processRead(SelectionKey key) throws IOException {
        SocketChannel socket = (SocketChannel)key.channel();
        ByteBuffer    buffer = (ByteBuffer)key.attachment();
        buffer.clear();
        int bytesCount = socket.read(buffer);
        String msg = "";
        //System.out.println("bytes read:" + bytesCount);
        if( bytesCount > 0 ){
            buffer.flip();
            Charset         charset = Charset.forName("UTF-8");
            CharsetDecoder  decoder = charset.newDecoder();
            CharBuffer   charBuffer = decoder.decode(buffer);
            msg  = charBuffer.toString();
            System.out.println("Received:" + msg);
            key.interestOps(SelectionKey.OP_WRITE);
        }
        else{
            socket.shutdownInput();
            socket.shutdownOutput();
            
        }
    }

    private static void processWrite(SelectionKey key) throws IOException {
        SocketChannel socket = (SocketChannel)key.channel();
        ByteBuffer    buffer = (ByteBuffer)key.attachment();
        buffer.flip();
        while(buffer.hasRemaining()){
            socket.write(buffer);
        }
        
        key.interestOps(SelectionKey.OP_READ);
    }
   
}
