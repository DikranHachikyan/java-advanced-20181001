package course;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
       String s = "Lorem ipsum dolor sit amet, consectetur adipisicing elit";
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
       byte [] buf = s.getBytes();
       try{
           bout.write(buf);
       }
       catch( IOException e){
           System.err.println("Exception:" + e);
       }
       
       System.out.println("Buffer as string:" + bout.toString());
       
       try( FileOutputStream fout = new FileOutputStream("./byte-array")){
           bout.writeTo(fout);
       }
       catch(IOException e){
           System.err.println("Exception:" + e);
       }
       bout.write('X');
       System.out.println("Buffer as string:" + bout.toString());
       bout.reset();
       bout.write('A');
       System.out.println("Buffer as string:" + bout.toString());
       
    }
    
}
