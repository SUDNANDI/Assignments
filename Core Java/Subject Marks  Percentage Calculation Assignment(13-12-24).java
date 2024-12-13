package pack.cap.module6.inter;

import java.util.Scanner;

public class SubjectMarks{
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        System.out.print("Enter the number of subjects: ");
        int sub = sc.nextInt();
 
     
        int[] marks = new int[sub];
 
        for (int i = 0; i < sub; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }
 
        int totalMarks = 0;
        for (int i = 0; i < sub; i++) {
            totalMarks += marks[i];
        }
        double percentage = (double) totalMarks / (sub * 100) * 100;
 
   
        System.out.println("Total marks: " + totalMarks);
        System.out.println("Percentage: " + percentage + "%");
 
        sc.close();
    }
}
 
