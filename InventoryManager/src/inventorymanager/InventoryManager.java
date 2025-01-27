/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventorymanager;

/**
 *
 * @author dashcodes
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InventoryManager {
    private final List<inventoryItem> inventory;

    public InventoryManager() {
        inventory = new ArrayList<>(); 
    }

     /**
     *
     * @param filePath
     */
    
       // Load inventory from CSV
     public void loadInventory(String filePath) {
        try 
            (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                if (values.length < 5)
                {
                    System.out.println("Invalid line in CSV: " + line);
                    continue; // Skip invalid lines
                }
                // Adjust indices based on your CSV structure
                String brand = values[2]; // Brand is placed in the third column
                String engineNumber = values[3]; // EngineNumber is placed in the fourth column
                String status = values[4]; //Status is placed in the fifth and final column

                // Create a new InventoryItem and add it to the inventory
                inventory.add(new inventoryItem(brand, engineNumber, status));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
}

    // Add an item
    public void addItem(String brand, String engineNumber, String status) {
        inventory.add(new inventoryItem(brand, engineNumber, status));
    }

    // Delete an item only by engine number
    public void deleteItem(String engineNumber) {
    boolean deleted = false; // Flag to check if an item was deleted
    for (inventoryItem item : inventory) {
        if (item.getEngineNumber().equals(engineNumber)) {
            
            // Check if the item can be deleted
            if ("Old".equals(item.getStockLabel()) && "Sold".equals(item.getStatus())) {
                inventory.remove(item);
                deleted = true; // Set flag to true if deletion is successful
                System.out.println("Item deleted successfully.");
                break; // Exit the loop after deletion
            } else {
                System.out.println("Not authorized for deletion.");
                return; // Exit the method if not authorized
            }
        }
    }
    // If no item was found with the given engine number
    if (!deleted) {
        System.out.println("Item with engine number " + engineNumber + " not found.");
    }
}

    // Sort items by brand
    public void sortItemsByBrand() {
        Collections.sort(inventory, Comparator.comparing(inventoryItem::getBrand));
    }

    // Search for an item by engine number
    public inventoryItem searchItem(String engineNumber) {
        for (inventoryItem item : inventory) {
            if (item.getEngineNumber().equals(engineNumber)) {
                return item;
            }
        }
        return null; // Not found
    }

    // Display all items
    public void displayInventory() {
        for (inventoryItem item : inventory) {
            System.out.println(item);
        }
    }
}    

