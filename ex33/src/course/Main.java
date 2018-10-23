package course;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

public class Main {

    public static void main(String[] args) {
       String s  = "Lorem ipsum #dolor sit amet, #consectetur #adipisicing elit";
       byte [] buf = s.getBytes();
       
       ByteArrayInputStream bin = new ByteArrayInputStream(buf);
       
       int c=0;
       try( PushbackInputStream pstr = new PushbackInputStream( bin )){
          while( (c = pstr.read()) != -1){
              if( c == '#'){
                  pstr.unread('|');
                  continue;
              }
              System.out.print((char)c);
          }
          System.out.print("\n");
          System.out.println("Is mark supported:" + pstr.markSupported());
       }
       catch( IOException e){
           System.err.println("Exception:" + e);
       }
       bin.reset();
       while( (c = bin.read()) != -1){
           System.out.print((char)c);
       }
       System.out.print("\n");
    }
    
}