package course;

public class Main {

    public static void main(String[] args) {
       Pair<Integer, String> p1 = new Pair<>(11,"Anna Smith");
       Pair<String,String>   p2 = new Pair<>("JD", "John Doe");
       
       System.out.println("p1 key:" + p1.getKey() + " value:" + p1.getValue());
       System.out.println("p2 key:" + p2.getKey() + " value:" + p2.getValue());
    }
    
}

