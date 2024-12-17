package pack.cap.module7.inter;

import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + '}';
    }
}

public class EmployeeSalaryProcessing {
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("John", 30000),
            new Employee("Alice", 50000),
            new Employee("Bob", 35000),
            new Employee("Steve", 78000),
            new Employee("Mary", 32000),
            new Employee("Rachel", 80000),
            new Employee("Tom", 75000),
            new Employee("Linda", 29000),
            new Employee("James", 36000)
        };

        List<Employee> employeeList = Arrays.asList(employees);

      
        List<Employee> employeesWithLowSalary = employeeList.stream()
            .filter(e -> e.getSalary() < 40000)
            .collect(Collectors.toList());
        System.out.println("Employees with salary less than 40,000: " + employeesWithLowSalary);

     
        List<Employee> employeesWithHighSalary = employeeList.stream()
            .filter(e -> e.getSalary() > 75000)
            .collect(Collectors.toList());
        System.out.println("Employees earning more than 75,000: " + employeesWithHighSalary);

       
        employeeList.stream()
            .filter(e -> e.getSalary() >= 33000 && e.getSalary() <= 37000)
            .forEach(e -> e.setSalary(e.getSalary() * 1.10));
        System.out.println("Employees after increment: " + employeeList);

      
        OptionalDouble averageSalary = employeeList.stream()
            .mapToDouble(Employee::getSalary)
            .average();
        System.out.println("Average Salary: " + (averageSalary.isPresent() ? averageSalary.getAsDouble() : "No employees"));

     
        double yearlyTurnover = averageSalary.isPresent() ? averageSalary.getAsDouble() * employeeList.size() : 0;
        System.out.println("Company Yearly Turnover: " + yearlyTurnover);

       
        List<Employee> sortedEmployees = employeeList.stream()
            .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
            .collect(Collectors.toList());
        System.out.println("Employees sorted by salary (Descending): " + sortedEmployees);
    }
}

