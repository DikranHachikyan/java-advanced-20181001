package course;

public class Main {

    public static void main(String[] args) {
        INumber num = ()->Math.random() * 100;
            
        System.out.println("Random:" + num.getValue());    
        System.out.println("Random:" + num.getValue());    
        System.out.println("Random:" + num.getValue());    
            
    }
}

interface INumber {
    public double getValue();
}