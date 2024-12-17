package pack.cap.module9.collection;

import java.util.ArrayList;
import java.util.Scanner;

  public class EmployeeListDemo {
       public static void main(String[] args) {
       // TODO Auto-generated method stub
          ArrayList<String> names=new ArrayList<String>();
          ArrayList<String> address=new ArrayList<String>();
                Scanner s=new Scanner(System.in);
                System.out.println("Enter No of Emp :");
                 int n=s.nextInt();
                System.out.println("Enter Emp Names :");
     for(int i=0;i<n;i++) {
                System.out.print("Enter Emp "+(i+1)+" Names: ");
                names.add(s.next());
}
                System.out.println(names);
     for(int i=0;i<n;i++) {
                System.out.print("Enter Emp "+(i+1)+" Address:");
                address.add(s.next());
}               System.out.println(address);
                System.out.println("Remove element from names "+names.remove(1));
                System.out.println("After Removing "+names);
                System.out.println("set new element"+names.set(1, "Rajat"));
                address.add(2, "Bihar");
                System.out.println("names size"+names.size());
                System.out.println("address size"+address.size());
                System.out.println("Get the element which is at index1 from address: "+address.get(1));
                names.addAll(address);
                System.out.println("After Two arrays combined"+names);
                
}
 
