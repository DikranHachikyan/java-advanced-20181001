package course;

public class Main {

    public static void main(String[] args) {
        IFactorial fact = (n)->{
            int res = 1;
            for( int i = 1; i <= n; i++){
                res *= i;
            }
            return res;
        };
       
        System.out.println("fact 3:" + fact.calculate(3));
        System.out.println("fact 5:" + fact.calculate(5));
    }
}

interface IFactorial {
    public int calculate(int n);
}