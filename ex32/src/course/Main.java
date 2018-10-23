package course;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
       String inFile  = "./readme.txt";
       String outFile = "./readme-copy.txt";
       try( 
               BufferedInputStream bis = new BufferedInputStream(
                                                new FileInputStream(inFile)    
                                            );
               BufferedOutputStream bos = new BufferedOutputStream(
                                                new FileOutputStream(outFile)
                                            )
          ){
          
           int size = bis.available();
           byte [] buf = new byte[size];
           
           bis.read(buf);
           bos.write(buf);
       }
       catch( IOException e){
           System.err.println("Exception:" + e);
       }
    }
    
}
