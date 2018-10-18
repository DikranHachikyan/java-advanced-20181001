package course;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String s = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod";
        byte [] buf = s.getBytes();
        
        try( FileOutputStream fout = new FileOutputStream("./output.txt") ){
            fout.write(buf);
        }
        catch( Exception e){
            System.err.println("Exception:" + e);
        }
        
    }
    
}
