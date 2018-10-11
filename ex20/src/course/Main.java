package course;

public class Main {
    
    public static void main(String[] args) {
      Integer [] numbers = { 1,4,6,8,1,4,1,6,6,6,7 };
      String  [] names = {"Apple", "Java", "C++", "Eclipse"};
      
      int count = arrayOp( ArrayUtils::<Integer>countEquals, numbers, 6);
      System.out.println("count:" + count);
      
      ArrayUtils<String> utils = new ArrayUtils<>();
      
      int index = arrayOp( utils::<String>find, names, "Python" );
      System.out.println("index:" + index);
      
      
      
    }
    
    public static <T> int arrayOp( IArrayUtils<T> utils, T [] values, T element){
        return utils.run(values, element);
    }
    
}

interface IArrayUtils<T> {
   public int run( T[] values, T element);
}

class ArrayUtils<T>{
    public static <E> int countEquals( E [] values, E value){
        int count = 0;
        for( E v : values){
            if( v == value ) count++;
        }
        return count;
    }
    
    public int find( T [] values, T value){
        for( int i = 0; i < values.length; i++){
            if( values[i] == value) return i;
        }
        return -1;
    }
}
