import java.util.Scanner;

public class Main {

    public static double calculateTotalPrice(double itemPrice, int quantity, double discountRate, double taxRate) 
    {
        double totalPriceBeforeTax = itemPrice * quantity;
        double discount = totalPriceBeforeTax * (discountRate / 100);
        double totalPriceAfterDiscount = totalPriceBeforeTax - discount;
        double tax = totalPriceAfterDiscount * (taxRate / 100);
        double totalPrice = totalPriceAfterDiscount + tax;

        return totalPrice;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the price of one item: ");
        double itemPrice = scanner.nextDouble(); 

        System.out.print("Enter the quantity of items: ");
        int quantity = scanner.nextInt(); 

        System.out.print("Enter the discount rate (percentage): ");
        double discountRate = scanner.nextDouble(); 

        System.out.print("Enter the sales tax rate (percentage): ");
        double taxRate = scanner.nextDouble(); 
        double totalPrice = calculateTotalPrice(itemPrice, quantity, discountRate, taxRate);

        
        System.out.printf("Total price after discount and tax: $%.2f\n", totalPrice);

        
        scanner.close();
    }
}
