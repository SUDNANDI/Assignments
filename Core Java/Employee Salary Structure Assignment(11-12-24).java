package emp1;
 
import java.util.*;
public class empsal {
 
    
    static final double PERK_RATE = 0.10;
    static final double TAX_RATE = 0.15;
    static final double STANDARD_DEDUCTION = 5000.0;
    
   
    String name;
    String department;
    double basicSalary;
    double ha;
    double ta;
    double da;
    double perks;
    
    public empsal(String name, String department, double basicSalary) {
        this.name = name;
        this.department = department;
        this.basicSalary = basicSalary;
    }
 
    
    public double calculateHA() {
        this.ha = 0.40 * basicSalary;
        return this.ha;
    }
 
    public double calculateTA() {
        this.ta = 0.20 * basicSalary;
        return this.ta;
    }
 
    public double calculateDA() {
        this.da = 0.30 * basicSalary;
        return this.da;
    }
 
    public double calculatePerks() {
        this.perks = basicSalary * PERK_RATE;
        return this.perks;
    }
 
    
    public double calculateTotalSalary() {
        return basicSalary + calculateHA() + calculateTA() + calculateDA() + calculatePerks();
    }
 
    
    public static double calculateTax(double totalSalary) {
        return totalSalary * TAX_RATE;
    }
 
    public static double applyStandardDeduction() {
        return STANDARD_DEDUCTION;
    }
 
   
    public void displaySalaryDetails() {
        double totalSalary = calculateTotalSalary();
        double tax = calculateTax(totalSalary);
        double finalSalary = totalSalary - tax - applyStandardDeduction();
 
        System.out.println("Employee Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("HRA: " + ha);
        System.out.println("TA: " + ta);
        System.out.println("DA: " + da);
        System.out.println("Perks: " + perks);
        System.out.println("Total Salary Before Tax and Deductions: " + totalSalary);
        System.out.println("Tax Deducted: " + tax);
        System.out.println("Standard Deduction: " + STANDARD_DEDUCTION);
        System.out.println("Final Salary: " + finalSalary);
    }
 
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  
 
        
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        
        System.out.print("Enter Basic Salary: ");
        double basicSalary = scanner.nextDouble();
        
        
        empsal employee = new empsal(name, department, basicSalary);
        
       
        employee.displaySalaryDetails();
 
       
        scanner.close();
    }
}
 
