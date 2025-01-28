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
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        manager.loadInventory("inventory_data.csv");
        
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            // Using a for loop with a break condition
            for (;;) { // This will cause an infinite loop
                System.out.println("\nInventory Management System");
                System.out.println("1. Display Inventory");
                System.out.println("2. Add Item");
                System.out.println("3. Delete Item");
                System.out.println("4. Sort Items by Brand");
                System.out.println("5. Search Item");
                System.out.println("6. Reset Sorting");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Will enter into a new line

                switch (choice) {
                    case 1 -> manager.displayInventory();
                    case 2 -> {
                        System.out.print("Enter brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("Enter engine number: ");
                        String engineNumber = scanner.nextLine();
                        System.out.print("Enter status: ");
                        String status = scanner.nextLine();
                        manager.addItem(brand, engineNumber, status);
                        }
                    case 3 -> {
                        System.out.print("Enter engine number to delete: ");
                        String engineToDelete = scanner.nextLine();
                        manager.deleteItem(engineToDelete);
                    }
                    case 4 -> {
                        manager.sortItemsByBrand();
                        System.out.println("Items sorted by brand.");
                    }
                    case 5 -> {
                        System.out.print("Enter engine number to search: ");
                        String engineToSearch = scanner.nextLine();
                        inventoryItem foundItem = manager.searchItem(engineToSearch);
                        if (foundItem != null) {
                            System.out.println("Found Item: " + foundItem);
                        } else {
                            System.out.println("Item not found.");
                        }
                    }
                    case 6 -> {
                        manager.resetToOriginalOrder();
                        System.out.println("Items reset to original order.");
                    }
                    case 0 -> {
                        System.out.println("Exiting...");
                        break; // Exit the loop
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}