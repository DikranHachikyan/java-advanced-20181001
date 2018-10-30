package course;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;




public class Main {

    public static void main(String[] args) throws  IOException {
       Path dir = Paths.get("./docs");
       System.out.println("start:" + dir);
       
       Files.walkFileTree(dir,  new SimpleFileVisitor<Path>(){
           @Override
           public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException{
               System.out.println("direcotry:" + dir);
               return FileVisitResult.CONTINUE;
           }
           
           @Override
           public FileVisitResult visitFile( Path file, BasicFileAttributes attrs) throws IOException{
               System.out.println("file:" + file + " size:" + attrs.size());
               return FileVisitResult.CONTINUE;
           }
           
           @Override
           public FileVisitResult visitFileFailed( Path file, IOException ex) throws IOException{
               return FileVisitResult.TERMINATE;
           }
       });
    }
    
}
