package course;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File file = new File("./readme.txt");
        
        System.out.println("File Name:" + file.getName());
        System.out.println("Path:" + file.getPath());
        System.out.println("Abs Path:" + file.getAbsolutePath());
        System.out.println( file.exists()? "Exists":"Does Not Exist");
        System.out.println("Readble:" + file.canRead());
        System.out.println(file.isDirectory()?"is directory":"is file");
        System.out.println("size:" + file.length() + " bytes");
        
        
        
        
    }
    
}
