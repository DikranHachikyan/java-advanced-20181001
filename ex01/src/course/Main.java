package course;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        
        list.ensureCapacity(10);
        
        System.out.println("Initial Capacity:" + list.size());
        
        list.add("A");
        list.add("B");
        list.add("C");
        list.add(2, "H");
        
        System.out.println("Elemets:" + list);
        System.out.println("Capacity:" + list.size());
        
        
        list.remove("B");
        System.out.println("Capacity:" + list.size());
        
        for( String s: list){
            System.out.println(s);
            
        }
        
        
        String elems[] = new String[list.size()];
        elems = list.toArray(elems);
        
        for( String s: elems){
            System.out.println(s);
        }
        
        
                
    }
    
}
