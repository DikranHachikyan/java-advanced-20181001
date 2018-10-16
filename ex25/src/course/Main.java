package course;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        int size = 0;
        try( FileInputStream fin = new FileInputStream("./readme.txt")){
            size = fin.available();
            System.out.println("size:" + size + " bytes");
            int n = size>>2;
            System.out.println("Read first " + n + " bytes");
            for( int i = 0; i < n ; i++){
                System.out.print((char)fin.read());
            }
            System.out.print("\n");
            System.out.print("available:" + fin.available());
            System.out.print("\n");
            byte [] buf = new byte[n];
            fin.read(buf);
            System.out.println("next:" + (new String(buf, 0, buf.length)) );
            fin.skip(n);
            
            fin.read(buf);
            System.out.println("next:" + (new String(buf, 0, buf.length)) );
            System.out.println("available:" + fin.available() );
            
            
        }
        catch( IOException e){
            System.err.println("Exception:" + e);
        }
    }
    
}
