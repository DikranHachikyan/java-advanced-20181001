package course;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;



public class Main {

    public static void main(String[] args) throws  IOException {
       String filename = "./mem.map";
       int length = 1048576; //1M
       long start = System.currentTimeMillis();
       try( FileChannel channel = FileChannel.open( Paths.get(filename), 
                                                    StandardOpenOption.CREATE,
                                                    StandardOpenOption.READ,
                                                    StandardOpenOption.WRITE)){
           MappedByteBuffer mem = channel.map(FileChannel.MapMode.READ_WRITE,0, length);
           System.out.println("mem:pos=" + mem.position() + " lim=" + mem.limit() + " cap:" + mem.capacity() );
           
           int n = length>>2;
           for( int i = 0; i < n ; i++){
               mem.putChar((char)('A' + i % 26) );
           }
           System.out.println("mem:pos=" + mem.position() + " lim=" + mem.limit() + " cap:" + mem.capacity() );
           mem.flip();
           while( mem.hasRemaining()){
               char c = mem.getChar();
               System.out.print(c);
           }
           System.out.println();
           
       }
       long end = System.currentTimeMillis();
       System.out.printf("Exec Time: %d\n", end - start);
    }
    
}
