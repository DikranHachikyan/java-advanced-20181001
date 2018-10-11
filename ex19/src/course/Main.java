package course;

public class Main {
    
    public static void main(String[] args) {
      Integer x = 5;
      
      System.out.println("factorial 5:" + calc( CMath::factorial, x));
      
      CMath math = new CMath();
      
      System.out.println("sum 1..100:" + calc( math::sum, 100));
      
      IComputable computable = math::sum;
      System.out.println("sum 1..10:" + calc( computable, 10));
    }
    
    public static int calc( IComputable comp, int value){
        return comp.calculate(value);
    }
}

interface IComputable {
    public int calculate( int num);
}

class CMath {
    public static int factorial(int n){
        int res = 1;
        for( int i = 1; i <= n; i++){
            res *= i;
        }
        return res;
    }
    
    public int sum(int n){
        return (n*n + n)>>1;
    }
}