package course;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
       char [] buf = new char[128];
       int c=0;
       try( FileReader fr = new FileReader("./readme.txt");
            FileWriter fw = new FileWriter("./readme1.txt")){
            while( (c = fr.read(buf)) != -1){
                fw.write(buf, 0, c);
            }
            
       }
       catch( IOException e){
           System.err.println("Exception:" + e);
       }
    }
    
}
