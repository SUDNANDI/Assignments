package pack.cap.module9.collection;

	import java.util.HashMap;
	import java.util.Scanner;
	 
	public class HashmapExample {
	 
	 
	    public static void searchProduct(HashMap<String, String> productMap, String productName) {
	        if (productMap.containsKey(productName)) {
	            System.out.println("Product found: " + productName + " - " + productMap.get(productName));
	        } else {
	            System.out.println("Product not found: " + productName);
	        }
	    }
	 
	    public static void insertProduct(HashMap<String, String> productMap, String productName, String productDescription) {
	        productMap.put(productName, productDescription);
	        System.out.println("Product inserted: " + productName + " - " + productDescription);
	    }
	 
	    public static void main(String[] args) {
	      
	        HashMap<String, String> productMap = new HashMap<>();
	 
	 
	        productMap.put("Headphone", "A portable set.");
	        productMap.put("Lamp", "A Lamp with a charger.");
	        productMap.put("Smart Watch", "A device with a small screen.");
	       
	      
	        Scanner scanner = new Scanner(System.in);
	       
	   
	        System.out.println("Enter the product name to add: ");
	        String productNameToInsert = scanner.nextLine();
	        System.out.println("Enter the description for the product: ");
	        String productDescriptionToInsert = scanner.nextLine();
	        insertProduct(productMap, productNameToInsert, productDescriptionToInsert);
	 
	   
	        System.out.println("\nEnter the product name to search: ");
	        String productNameToSearch = scanner.nextLine();
	        searchProduct(productMap, productNameToSearch);
	 
	        scanner.close();
	    }
	}
