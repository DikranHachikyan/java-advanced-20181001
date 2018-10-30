package course;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;



public class Main {

    public static void main(String[] args) throws  IOException {
       Path filePath = Paths.get("./readme.txt");
       try( SeekableByteChannel sbc = Files.newByteChannel(filePath, StandardOpenOption.READ
                                                                   , StandardOpenOption.WRITE)){
           ByteBuffer buf = ByteBuffer.allocate(128);
           int count = 0;
           while( (count = sbc.read(buf)) != -1){
               buf.flip();
               while( buf.hasRemaining()){
                   System.out.print((char)buf.get());
               }
               buf.clear();
           }
       }
    }
    
}
