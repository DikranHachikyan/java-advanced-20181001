package course;

public class Main {

    public static void main(String[] args) {
        int n = 5;
        
        IFactorial fact = ()->{
            int res = 1;
            for( int i = 1; i <= n; i++){
                res *= i;
            }
            //n++; //Error!!
            return res;
        };
       
        System.out.println("fact 3:" + fact.calculate());
    }
}

interface IFactorial {
    public int calculate();
}