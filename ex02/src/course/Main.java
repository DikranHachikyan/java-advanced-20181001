package course;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        //Integer x = 2; Boxing
        list.add(10);
        list.add(12);
        list.add(21);
        list.add(17);
        list.add(14);
        
        list.addFirst(100);
        
        System.out.println("List:" + list);
        
        
        if( list.remove(new Integer(21) )){
            System.out.println("21 removed");
        }
        
        System.out.println("List:" + list);
        Integer first = list.removeFirst();
        Integer last  = list.removeLast();
        
        Integer value = list.get(2);
        list.set(2, last);
        System.out.println("firrst:" + first + " last:" + last);
        System.out.println("List:" + list);
        
        
    }
    
}
