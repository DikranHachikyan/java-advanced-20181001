package course;

import java.io.File;
import java.io.FilenameFilter;

public class Main {

    public static void main(String[] args) {
        String dirname = "./docs";
        File dr = new File(dirname);
        
        FilenameFilter filter = (dir,name)->name.endsWith(".java");
        String [] names = dr.list(filter);
        for( String name: names){
            System.out.println("file:"+ name);
        }
        
        File [] files = dr.listFiles(filter);
        for( File f: files){
            System.out.println("File:" + f.getName());
        }
        
        File dirs = new File("./docs/a/b/c/d");
        dirs.mkdirs();
    }
    
}
