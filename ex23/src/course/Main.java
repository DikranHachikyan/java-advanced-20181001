package course;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String dirname = "./docs";
        File dir = new File(dirname);
        
        if( dir.isDirectory()){
            String [] names = dir.list();
            for( String name: names){
                File f = new File(dirname + "/" + name);
                if( f.isDirectory()){
                    System.out.println("subdirectory:" + f.getName() + " ("+name+")");
                    continue;
                }
                    System.out.println("file:" + f.getName() + " ("+name+")");
                
            }
        }
        
  
        
    }
    
}
