package project1;
 
//super class
class Employee {
	String name;
	int employeeId;
	double salary;
 
	protected Employee(String name, int employeeId, double salary) {
		super();
		this.name = name;
		this.employeeId = employeeId;
		this.salary = salary;
	}
 
	public void work() {
		System.out.println(name + "is workimg as an employee");
	}
 
	public void getSalary() {
		System.out.println(name + " is getting " + salary + " as a normal employee");
	}
}
 
//subclass
class Manager extends Employee {
	String team;
 
	protected Manager(String name, int employeeId, double salary, String team) {
		super(name, employeeId, salary);
		this.team = team;
	}
	
    @Override
    public void work() {
        System.out.println(name + " is working as a manager for team: " + team);
    }

   
    @Override
    public void getSalary() {
        System.out.println(name + " is getting a higher salary of " + salary + " as a manager.");
    }
}
 
 
public class SingleInheritance_Demo {
 
	public static void main(String[] args) {
	
	
        Employee emp = new Employee("John Doe", 101, 50000);
        emp.work();  
        emp.getSalary();  

       
        Manager mgr = new Manager("Alice Smith", 102, 75000, "Development Team");
        mgr.work();  
        mgr.getSalary();  
		
 
	}
 
}
 
