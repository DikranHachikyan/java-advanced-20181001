package course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private static BufferedReader console = null;
    public static void main(String[] args) {
       int port = 4500;
       String host = "web-dev";
       try(Selector selector = Selector.open();
           SocketChannel socket = SocketChannel.open()){
           
           socket.configureBlocking(false);
           socket.connect(new InetSocketAddress( InetAddress.getByName(host),
                                                 port));
           int operations = SelectionKey.OP_CONNECT | 
                            SelectionKey.OP_READ    |
                            SelectionKey.OP_WRITE;
           socket.register(selector, operations);
           
           console = new BufferedReader( new InputStreamReader(System.in));
           
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
       finally{
           if( console != null){
               try {
                   console.close();
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
       }
    }

    private static boolean processReadySet(Set<SelectionKey> readySet) throws IOException {
        SelectionKey key = null;
        Iterator<SelectionKey> iterator = readySet.iterator();
        while( iterator.hasNext()){
            key  = iterator.next();
            
            iterator.remove();
            
            if( key.isConnectable()){
                //not connected ->exit
                if( !processConnect(key) ) return true;
            }
            
            if( key.isReadable() ){
                String message = processRead(key);
                System.out.println("[server]:" + message);
            }
            
            if( key.isWritable() ){
                String message = getUserInput();
                if( message.equalsIgnoreCase("quit")){
                    ((SocketChannel)key.channel()).shutdownInput();
                    ((SocketChannel)key.channel()).shutdownOutput();
                    key.cancel();
                    return true;
                }//exit
                else{
                    processWrite(key, message);
                }
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

    private static String processRead(SelectionKey key) throws IOException {
        SocketChannel socket = (SocketChannel) key.channel();
        ByteBuffer    buffer = ByteBuffer.allocate(BUF_SIZE);
        
        socket.read(buffer);
        
        buffer.flip();
        
        Charset        charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer  charBuffer = decoder.decode(buffer);
        return charBuffer.toString();
    }

    private static String getUserInput() throws IOException {
        String prompt = "Enter a message (quit to exit):";
        System.out.print(prompt);
        String message = console.readLine();
        return message;
    }

    private static void processWrite(SelectionKey key, String message) throws IOException {
        SocketChannel socket = (SocketChannel) key.channel();
        
        ByteBuffer buffer = ByteBuffer.wrap( message.getBytes());
        
        while( buffer.hasRemaining()){
            socket.write(buffer);
        }
    }

}
