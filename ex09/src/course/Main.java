package course;

public class Main {

    public static void main(String[] args) {
       Pair<Integer, String> p1 = new Pair<>(11,"Anna Smith");
       Pair<Integer,Integer>   p2 = new Pair<>(1, 2);
       
       System.out.println("p1 key:" + p1.getKey() + " value:" + p1.getValue());
       System.out.println("p2 key:" + p2.getKey() + " value:" + p2.getValue());
    }
    
}

