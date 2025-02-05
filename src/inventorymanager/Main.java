package inventorymanager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Scanner;

/**
 *
 * @author dashcodes
 */

public class Main {
    private static final boolean CSV_HEADER = true; /** Flag to control CSV header display */
    
    /**
     * Entry point of the application.
     * Handles user interaction and inventory operations.
     *
     * @param args Command line arguments (not used)
     */
    
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager("inventory_data.csv");
        manager.loadInventory();
        
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            while (true) {
                System.out.println("\nInventory Management System");
                System.out.println("1. Display Inventory");
                System.out.println("2. Add Item");
                System.out.println("3. Delete Item");
                System.out.println("4. Sort Items by Brand");
                System.out.println("5. Search Item");
                System.out.println("6. Reset Sort Order");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                
                choice = scanner.nextInt();
                scanner.nextLine(); // Will enter into a new line
                
                switch (choice) {
                    case 1 -> manager.displayInventory();
                    case 2 -> {
                        System.out.print("Enter engine number: ");
                        String engineNumber = scanner.nextLine();
                        if (!manager.isEngineNumberExists(engineNumber)) {
                            System.out.print("Enter brand: ");
                            String brand = scanner.nextLine();
                            manager.addItem(engineNumber, brand);
                        } else {
                            System.out.println("Error: Engine number already exists!");
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter engine number to delete: ");
                        String engineToDelete = scanner.nextLine();
                        manager.deleteItem(engineToDelete);
                    }
                    case 4 -> {
                        manager.sortItemsByBrand();
                        System.out.println("Items sorted by brand.");
                        manager.displayInventory();
                    }
                    case 5 -> {
                        System.out.print("Enter engine number to search: ");
                        String engineToSearch = scanner.nextLine();
                        inventoryItem foundItem = manager.searchItem(engineToSearch);
                        if (foundItem != null) {
                            System.out.println(CSV_HEADER);
                            System.out.println(foundItem);
                        } else {
                            System.out.println("Item not found.");
                        }
                    }
                    case 6 -> {
                        manager.resetToOriginalOrder();
                        System.out.println("Sort order reset to original.");
                        manager.displayInventory();
                    }
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}