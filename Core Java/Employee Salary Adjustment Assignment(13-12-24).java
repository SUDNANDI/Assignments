package pack.cap.module6.inter;

public class EmployeeSalaryAdjustment {
	 public static void main(String[] args) {
	        double[] salaries = {32000, 20000, 15000, 10000, 50000, 18000};

	        for (int i = 0; i < salaries.length; i++) {
	            double salary = salaries[i];
	            System.out.println("Original Salary of Employee " + (i + 1) + ": " + salary);
	            
	            if (salary >= 30000) {
	                salary += salary * 0.10;
	                System.out.println("Salary after 10% increment: " + salary);
	            }
	            if (salary == 20000) {
	                salary += 2000;
	                System.out.println("Salary after bonus of 2000: " + salary);
	            }
	            if (salary < 15000) {
	                System.out.println("Salary Improvement Advice: Work harder to improve your salary.");
	            }
	            
	            double hra = salary * 0.10;  
	            double ta = salary * 0.05;   
	            double da = salary * 0.08;  

	            System.out.println("House Rent Allowance (HRA): " + hra);
	            System.out.println("Travel Allowance (TA): " + ta);
	            System.out.println("Dearness Allowance (DA): " + da);
	            System.out.println("-----------------------------------------------------");
	        }
	    }
	}


	        
