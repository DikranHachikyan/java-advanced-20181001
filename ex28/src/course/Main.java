package course;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
       ByteArrayInputStream bin ;
       byte [] buf = {0};
       
       try(FileInputStream fin = new FileInputStream("./readme.txt")){
          buf = new byte[ fin.available()];
          fin.read(buf);
       }
       catch( IOException e){
           System.err.println("Exception:" + e);
       } 
       
       bin = new ByteArrayInputStream(buf);
       int c = 0;
       while( (c = bin.read()) != -1){
           System.out.print( (char)c);
       }
       System.out.print("\n");
       
    }
    
}
