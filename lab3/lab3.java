import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Laboratory work #3: Classes in Java
 * Variant 6: Sports equipment class
 */
public class Lab3 {
    
    /**
     * Class representing sports equipment.
     * Has 5 fields: name, category, weight, quantity, price.
     */
    static class SportsEquipment {
        private String name;
        private String category;
        private double weight; // in kg
        private int quantity;
        private double price; // in currency units
        
        private static final double EPSILON = 0.0001;
        
        /**
         * Constructor for SportsEquipment class.
         * @param name Equipment name
         * @param category Category
         * @param weight Weight (kg)
         * @param quantity Quantity
         * @param price Price
         */
        public SportsEquipment(String name, String category, double weight, int quantity, double price) {
            this.name = name;
            this.category = category;
            this.weight = weight;
            this.quantity = quantity;
            this.price = price;
        }
        
        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getWeight() { return weight; }
        public int getQuantity() { return quantity; }
        public double getPrice() { return price; }
        
        /**
         * Returns string representation of the object.
         * @return String with all fields
         */
        @Override
        public String toString() {
            return String.format("SportsEquipment[name='%s', category='%s', weight=%.2f kg, quantity=%d, price=%.2f]",
                    name, category, weight, quantity, price);
        }
        
        /**
         * Compares two SportsEquipment objects for equality.
         * Uses EPSILON tolerance for double comparison.
         * @param obj Object to compare
         * @return true if objects are equal
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            
            SportsEquipment that = (SportsEquipment) obj;
            return Math.abs(that.weight - weight) < EPSILON &&
                   Math.abs(that.price - price) < EPSILON &&
                   quantity == that.quantity &&
                   name.equals(that.name) &&
                   category.equals(that.category);
        }
        
        /**
         * Generates hash code for the object.
         * Consistent with equals() method.
         * @return Hash code
         */
        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + category.hashCode();
            result = 31 * result + (int)(weight * 1000);
            result = 31 * result + quantity;
            result = 31 * result + (int)(price * 100);
            return result;
        }
    }
    
    /**
     * Main method that performs all tasks according to the assignment.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter the number of sports equipment items: ");
            int count = scanner.nextInt();
            scanner.nextLine();
            
            if (count <= 0) {
                System.out.println("Number must be positive. Set to 3.");
                count = 3;
            }
            
            SportsEquipment[] equipmentArray = new SportsEquipment[count];
            
            System.out.println("\nEnter data for each item:");
            for (int i = 0; i < count; i++) {
                System.out.println("\n--- Item " + (i + 1) + " ---");
                
                System.out.print("Name: ");
                String name = scanner.nextLine();
                
                System.out.print("Category: ");
                String category = scanner.nextLine();
                
                System.out.print("Weight (kg): ");
                double weight = scanner.nextDouble();
                
                System.out.print("Quantity: ");
                int quantity = scanner.nextInt();
                
                System.out.print("Price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                
                equipmentArray[i] = new SportsEquipment(name, category, weight, quantity, price);
            }
            
            System.out.println("\n=== ORIGINAL ARRAY ===");
            for (int i = 0; i < equipmentArray.length; i++) {
                System.out.println("[" + i + "] " + equipmentArray[i]);
            }
            
            Arrays.sort(equipmentArray, Comparator.comparingDouble(SportsEquipment::getWeight));
            System.out.println("\n=== SORTED BY WEIGHT (ASCENDING) ===");
            for (int i = 0; i < equipmentArray.length; i++) {
                System.out.println("[" + i + "] " + equipmentArray[i]);
            }
            
            Arrays.sort(equipmentArray, Comparator.comparingInt(SportsEquipment::getQuantity).reversed());
            System.out.println("\n=== SORTED BY QUANTITY (DESCENDING) ===");
            for (int i = 0; i < equipmentArray.length; i++) {
                System.out.println("[" + i + "] " + equipmentArray[i]);
            }
            
            System.out.println("\n=== SEARCH FOR IDENTICAL OBJECT ===");
            System.out.println("Enter data of the object to search:");
            
            System.out.print("Name: ");
            String searchName = scanner.nextLine();
            
            System.out.print("Category: ");
            String searchCategory = scanner.nextLine();
            
            System.out.print("Weight (kg): ");
            double searchWeight = scanner.nextDouble();
            
            System.out.print("Quantity: ");
            int searchQuantity = scanner.nextInt();
            
            System.out.print("Price: ");
            double searchPrice = scanner.nextDouble();
            
            SportsEquipment searchItem = new SportsEquipment(searchName, searchCategory, 
                                                           searchWeight, searchQuantity, searchPrice);
            
            System.out.println("\nSearching for: " + searchItem);
            
            boolean found = false;
            int foundIndex = -1;
            
            for (int i = 0; i < equipmentArray.length; i++) {
                if (equipmentArray[i].equals(searchItem)) {
                    found = true;
                    foundIndex = i;
                    break;
                }
            }
            
            if (found) {
                System.out.println("Found identical object at position: " + foundIndex);
            } else {
                System.out.println("Object not found in array");
            }
            
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            System.err.println("Using test data...");
            
            useTestData();
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Method with test data for use in case of input errors.
     * Creates array with guaranteed duplicate for testing search functionality.
     */
    private static void useTestData() {
        System.out.println("\n=== TEST DATA ===");
        
        SportsEquipment[] testArray = new SportsEquipment[4];
        testArray[0] = new SportsEquipment("Ball", "Team Sports", 0.5, 10, 150.0);
        testArray[1] = new SportsEquipment("Racket", "Tennis", 0.3, 5, 800.0);
        testArray[2] = new SportsEquipment("Dumbbell", "Gym", 5.0, 20, 200.0);
        testArray[3] = new SportsEquipment("Ball", "Team Sports", 0.5, 10, 150.0);
        
        System.out.println("Original array:");
        for (int i = 0; i < testArray.length; i++) {
            System.out.println("[" + i + "] " + testArray[i]);
        }
        
        Arrays.sort(testArray, Comparator.comparingDouble(SportsEquipment::getWeight));
        System.out.println("\nSorted by weight (ascending):");
        for (int i = 0; i < testArray.length; i++) {
            System.out.println("[" + i + "] " + testArray[i]);
        }
        
        Arrays.sort(testArray, Comparator.comparingInt(SportsEquipment::getQuantity).reversed());
        System.out.println("\nSorted by quantity (descending):");
        for (int i = 0; i < testArray.length; i++) {
            System.out.println("[" + i + "] " + testArray[i]);
        }
        
        SportsEquipment searchItem = new SportsEquipment("Ball", "Team Sports", 0.5, 10, 150.0);
        System.out.println("\nSearching for object: " + searchItem);
        
        boolean found = false;
        int index = -1;
        for (int i = 0; i < testArray.length; i++) {
            if (testArray[i].equals(searchItem)) {
                found = true;
                index = i;
                break;
            }
        }
        
        if (found) {
            System.out.println("Found at position: " + index);
        } else {
            System.out.println("Not found");
        }
    }
}