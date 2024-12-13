package pack.cap.module5.module4;

class TransportationSystem {
    String name;
    String dateOfMaking;
    String modelNo;
    String engineType;
 
    public TransportationSystem(String name, String dateOfMaking, String modelNo, String engineType) {
        this.name = name;
        this.dateOfMaking = dateOfMaking;
        this.modelNo = modelNo;
        this.engineType = engineType;
    }
 
    
    public void displayInfo() {
        System.out.println("Vehicle Name: " + name);
        System.out.println("Date of Making: " + dateOfMaking);
        System.out.println("Model Number: " + modelNo);
        System.out.println("Engine Type: " + engineType);
    }
}
 
 
class Car extends TransportationSystem {
    String carType;
 
    public Car(String name, String dateOfMaking, String modelNo, String engineType, String carType) {
        super(name, dateOfMaking, modelNo, engineType);
        this.carType = carType;
    }
 
    
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Car Type: " + carType);
    }
}
 
 
class Bike extends TransportationSystem {
    String bikeType;
 
    public Bike(String name, String dateOfMaking, String modelNo, String engineType, String bikeType) {
        super(name, dateOfMaking, modelNo, engineType);
        this.bikeType = bikeType;
    }
 
    
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Bike Type: " + bikeType);
    }
}
 
 
class ECAR extends Car {
    int batteryCapacity;
 
    public ECAR(String name, String dateOfMaking, String modelNo, String engineType, String carType, int batteryCapacity) {
        super(name, dateOfMaking, modelNo, engineType, carType);
        this.batteryCapacity = batteryCapacity;
    }
 
    
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Battery Capacity: " + batteryCapacity + " kWh");
    }
}
 
 
class BCAR extends Car {
    String fuelType;
 
    public BCAR(String name, String dateOfMaking, String modelNo, String engineType, String carType, String fuelType) {
        super(name, dateOfMaking, modelNo, engineType, carType);
        this.fuelType = fuelType;
    }
 
    
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Fuel Type: " + fuelType);
    }
}
 
 
public class Multilevel_Inheritance_Assignment1 {
    public static void main(String[] args) {
        
        TransportationSystem vehicle1 = new TransportationSystem("Generic Vehicle", "2022-01-01", "GV1234", "Diesel");
        Car car1 = new Car("Toyota Corolla", "2021-03-15", "TC5678", "Petrol", "Sedan");
        Bike bike1 = new Bike("Yamaha R15", "2020-07-20", "YR1234", "Petrol", "Sports");
        ECAR ecar1 = new ECAR("Tesla Model 3", "2023-06-10", "TM9876", "Electric", "Sedan", 75);
        BCAR bcar1 = new BCAR("BMW i3", "2022-09-15", "BI3345", "Electric", "Hatchback", "Battery");
 
        
        System.out.println("Vehicle Info:");
        vehicle1.displayInfo();
 
        System.out.println("\nCar Info:");
        car1.displayInfo();
 
        System.out.println("\nBike Info:");
        bike1.displayInfo();
 
        System.out.println("\nElectric Car Info:");
        ecar1.displayInfo();
 
        System.out.println("\nBattery Car Info:");
        bcar1.displayInfo();
    }
}
