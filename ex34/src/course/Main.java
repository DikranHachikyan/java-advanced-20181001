package course;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Main {

    public static void main(String[] args) {
       String [] fs = new String[]{"./readme.txt","./readm-copy.txt","./readme1.txt"};
       FileInputStreamEnumeration fenum = new FileInputStreamEnumeration(Arrays.asList(fs))
               ;
       try(InputStream in = new SequenceInputStream(fenum) ){
         int len = 0;
         byte [] buf = new byte[100];
         
         System.out.println("Before read Bytes av:" + in.available());
         while( (len = in.read(buf)) != -1){
             System.out.println("Bytes av:" + in.available());
             System.out.println( new String( buf, 0, len));
         }
       }
       catch( IOException | NullPointerException e){
           System.err.println("Exception:" + e);
       }
       
    }
    
}

class FileInputStreamEnumeration implements Enumeration<FileInputStream>{
    private Enumeration<String> files;
    
    public FileInputStreamEnumeration( List<String> files){
        this.files = Collections.enumeration(files);
    }
    
    @Override
    public boolean hasMoreElements() {
        return files.hasMoreElements();
    }

    @Override
    public FileInputStream nextElement() {
        String filename = files.nextElement();
        try{
            return new FileInputStream(filename);
        } catch (FileNotFoundException ex) {
            System.err.println("Exception:" + ex);
            return null;
        }
    }
    
}