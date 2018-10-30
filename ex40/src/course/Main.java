package course;

import java.io.IOException;
import java.nio.ByteBuffer;



public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        String s = "Lorem ipsum dolor sit amet";
        byte [] buf = s.getBytes();
        
        ByteBuffer org  = ByteBuffer.wrap(buf);
        ByteBuffer copy = ByteBuffer.allocate(250);
        
        System.out.println("org: pos=" +org.position() + " lim=" + org.limit() + " cap=" + org.capacity());
        System.out.println("copy: pos=" +copy.position() + " lim=" + copy.limit() + " cap=" + copy.capacity());
        
        //copy.limit( org.limit());
        byte c;
        while(org.hasRemaining()){
            c = org.get();
            System.out.print(org.position() + ":" + (char)c + "|");
            copy.put(c);
        }
        System.out.println();
        System.out.println("org: pos=" +org.position() + " lim=" + org.limit() + " cap=" + org.capacity());
        System.out.println("copy: pos=" +copy.position() + " lim=" + copy.limit() + " cap=" + copy.capacity());
        
        copy.flip();
        copy.rewind();
        System.out.println("copy: pos=" +copy.position() + " lim=" + copy.limit() + " cap=" + copy.capacity());
        
        while(copy.hasRemaining()){
            c = copy.get();
            System.out.print((char)c + "|");
        }
        System.out.println();
    }
    
}
