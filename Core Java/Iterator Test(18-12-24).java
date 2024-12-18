package pack.cap.module9.collection;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorTest {
    public static void main(String[] args) {
   
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Grapes");
        
     
        Iterator<String> iterator = fruits.iterator();
        
   
        while (iterator.hasNext()) {
           
            System.out.println(iterator.next());
        }
    }
}

