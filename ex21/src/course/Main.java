package course;

import java.util.Arrays;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
       int [] array = new int[10];
       Random rand = new Random();
       rand.setSeed(2);
       for( int i = 0; i < array.length ; i++ ){
           array[i] = rand.nextInt() % 100; 
       }
       show(array);
       System.out.println("Sorted:");
       Arrays.fill(array,2, 5, 40);
       Arrays.sort(array);
       show(array);
//     int idx = Arrays.binarySearch(array, 38);
//     System.out.println("index:" + idx);
       //int [] other = Arrays.copyOf(array,4);
       //System.out.println("Copy:");
       //show(other);
       Arrays.parallelPrefix(array, (a,b)->a+b);
       show(array);
       
       int [] nums = new int[10];
       Arrays.parallelSetAll(nums, (i)->rand.nextInt() % 100);
       show(nums);
       Arrays.parallelSort(nums);
       show(nums);
    }
    
    public static void show(int [] array){
        for( int v : array){
            System.out.print(v + "|");
        }
        System.out.print("\n");
    }
    
}
