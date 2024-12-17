package pack.cap.module7.inter;

import java.util.function.*;

public class PredicateExample {
    public static void main(String[] args) {
        
        Predicate<Integer> isEven = (num) -> num % 2 == 0;

     
        Predicate<Integer> isGreaterThan = (num) -> num > 100;

      
        Predicate<Integer> isEvenAndGreaterThan = isEven.and(isGreaterThan);

       
        Predicate<Integer> isEvenOrGreaterThanHundred = isEven.or(isGreaterThan);

     
        Predicate<Integer> isNotEven = isEven.negate();

  
        Integer[] testNumbers = {50, 150, 25, 101, 200, 75};

        // Print results for each test number with different predicates
        System.out.println("Checking numbers with predicates:\n");

        for (Integer num : testNumbers) {
            System.out.println("Number: " + num);
            System.out.println("Is Even? " + isEven.test(num)); 
            System.out.println("Is Greater Than 100? " + isGreaterThan.test(num));  
            System.out.println("Is Even AND Greater Than 100? " + isEvenAndGreaterThan.test(num));
            System.out.println("Is Even OR Greater Than 100? " + isEvenOrGreaterThanHundred.test(num));  
            System.out.println("Is Not Even? " + isNotEven.test(num));  
            System.out.println();  
        }
    }
}
