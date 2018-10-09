package course;

public class Main {

    public static void main(String[] args) {
      Integer [] nums = {10,1,20,4,6,-3};
      Character [] chrs = {'z','s','d','i'};
      
      Array<Integer> arr1 = new Array<>(nums);
      Array<Character> arr2 = new Array<>(chrs);
      
      System.out.println("arr1 min:" + arr1.min());
      System.out.println("arr1 max:" + arr2.max());
    }
    
}

class SmartPair<K, V, T> extends Pair<K,V>{
    
}