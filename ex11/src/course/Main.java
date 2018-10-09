package course;

public class Main {

    public static void main(String[] args) {
       Pair<? extends Number, String> p1 = new Pair<>(11,"Anna Smith");
       Pair<Integer,Integer> p2 = new Pair<>(1, 2000);
       Pair<String,String>   p3 = new Pair<>("AOL","Apple");
       
       if( p1 instanceof Pair<?,?>){
           System.out.println("p1 is instance of Pair");
       }
       if( p2 instanceof Pair){
           System.out.println("p2 is instance of Pair");
       }
       
    }
    
}

