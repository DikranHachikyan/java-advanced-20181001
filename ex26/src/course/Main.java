package course;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
       String s = "Lorem ipsum dolor sit amet, consectetur adipisicing elit.";
       byte [] buf = s.getBytes();
       boolean marked = false;
       int i = 0;
       
       ByteArrayInputStream bin = new ByteArrayInputStream(buf);
       int c;
       System.out.println("available:" + bin.available());
       while( (c = bin.read()) != -1){
           if( (char)c == 'a' && !marked){
               //System.out.println(i);
               bin.mark(i);
               marked = true;
           }
           i++;
           System.out.print((char)c);
       }
       System.out.println();
       bin.reset();
       System.out.println("From mark to end");
       while( (c = bin.read()) != -1){
           System.out.print((char)c);
       }
       System.out.println();
    }
    
}
