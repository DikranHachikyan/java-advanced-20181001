package course;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
       String s= "Lorem #ipsum dolor# sit amet, #consectetur# adipisicing #elit#";
       int length = s.length();
       char [] chrs = new char[length];
       boolean marked = false;
       
       s.getChars(0, length, chrs, 0);
       
       int c = 0;
       int i = 0;
       int pos = 0;
       
       try( CharArrayReader car = new CharArrayReader(chrs);
            CharArrayWriter caw = new CharArrayWriter()){
           while( (c = car.read()) != -1){
               if( (char)c == '#'){
                   if( !marked ){
                       car.mark(i);
                       pos = i;
                   }
                   else{
                       car.reset();
                       while( pos < (i-1) && (c = car.read()) !=-1){
                           caw.write(c);
                           pos++;
                       }
                       caw.write('|');
                       car.skip(2);
                   }
                   marked = !marked;
               }
               i++;
           }//while
           System.out.println("Result:" + caw.toString());
       }
       catch( IOException e){
           System.err.println("Exception:" + e);
       }
    }
    
}
