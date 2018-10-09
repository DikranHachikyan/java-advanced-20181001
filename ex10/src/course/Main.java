package course;

public class Main {

    public static void main(String[] args) {
       Pair<Integer, String> p1 = new Pair<>(11,"Anna Smith");
       Pair<Integer,Integer> p2 = new Pair<>(1, 2000);
       
       Pair raw = new Pair("APL", "Apple");
       
       String name = (String)raw.getValue();
       System.out.println("name:" + name);
       
       raw = p1;
       System.out.println("p1 key:" + raw.getKey());
       
    }
    
}

