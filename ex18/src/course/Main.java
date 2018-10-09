package course;

public class Main {
    static String modifyString( IStringOperation op, String s ){
        return op.func(s);
    }
    public static void main(String[] args) {
        String input = "lorem ipsum dolor";
        
        System.out.println("output:" + modifyString((str)->str.toUpperCase(), input));
    }
}

interface IStringOperation {
    public String func(String s);
}