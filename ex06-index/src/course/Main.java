package course;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int length  = 7;
        Object o = null;
        assert o != null;
        for( int i = 0; i < 20; i++){
            System.out.println("index:" + ( (i + 1) % length) + " i=" + i);
        }
                
    }
    
}
