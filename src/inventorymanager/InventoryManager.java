/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventorymanager;

/**
 *
 * @author dashcodes
 */

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class InventoryManager {
    private final List<inventoryItem> inventory;
    private final List<inventoryItem> originalInventory;
    private final String csvFilePath;
    private static final String CSV_HEADER = "DateEntered,StockLabel,Brand,EngineNumber,Status";

    public InventoryManager(String csvFilePath) {
        this.inventory = new ArrayList<>();
        this.originalInventory = new ArrayList<>();
        this.csvFilePath = csvFilePath;
    }
    
       // Load inventory from CSV
    public void loadInventory() {
        try {
            // Create file if it doesn't exist
            File file = new File(csvFilePath);
            if (!file.exists()) {
                try (FileWriter fw = new FileWriter(file)) {
                    fw.write(CSV_HEADER);
                }
                return;
            }
            
            // Read existing inventory
            try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                boolean isFirstLine = true;
                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue; // Skip header
                    }
                    String[] values = line.split(",");
                    if (values.length == 5) {
                        inventoryItem item = new inventoryItem(
                            values[0], // dateEntered
                            values[1], // stockLabel
                            values[2], // brand
                            values[3], // engineNumber
                            values[4]  // status
                        );
                        inventory.add(item);
                        originalInventory.add(item);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
     
    // Confirm if engine Number is already existing
     public boolean isEngineNumberExists(String engineNumber) {
        return inventory.stream()
            .anyMatch(item -> item.getEngineNumber().equalsIgnoreCase(engineNumber));
    }

    // Add an item
     public void addItem(String engineNumber, String brand) {
        if (!isEngineNumberExists(engineNumber)) {
            inventoryItem newItem = new inventoryItem(brand, engineNumber);
            inventory.add(newItem);
            appendToCsv(newItem);
            System.out.println("Item added successfully.");
        } else {
            System.out.println("Error: Engine number already exists!");
        }
    }
     
     //Filewriter
     private void appendToCsv(inventoryItem item) {
        try (FileWriter fw = new FileWriter(csvFilePath, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("\n" + item.toString());
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    // Delete an item only by engine number
   public void deleteItem(String engineNumber) {
        Optional<inventoryItem> itemToDelete = inventory.stream()
            .filter(item -> item.getEngineNumber().equalsIgnoreCase(engineNumber))
            .findFirst();

        if (itemToDelete.isPresent()) {
            inventoryItem item = itemToDelete.get();
            if (!"Old".equals(item.getStockLabel()) || !"Sold".equals(item.getStatus())) {
                System.out.println("Error: Cannot delete item. Item must have StockLabel 'Old' and Status 'Sold'");
                return;
            }
            
            inventory.remove(item);
            updateCsvFile();
            System.out.println("Item deleted successfully.");
        } else {
            System.out.println("Error: Engine number not found.");
        }
    }
    
    //Reflecting the changes to the CSV File
    private void updateCsvFile() {
        try {
            List<String> lines = new ArrayList<>();
            lines.add(CSV_HEADER);
            for (inventoryItem item : inventory) {
                lines.add(item.toString());
            }
            Files.write(Paths.get(csvFilePath), lines);
        } catch (IOException e) {
            System.err.println("Error updating CSV file: " + e.getMessage());
        }
    }

    // Sort items by brand
    public void sortItemsByBrand() {
        inventory.sort(Comparator.comparing(inventoryItem::getBrand));
    }
    
    
    // Reset inventory to original order
    public void resetToOriginalOrder() {
        inventory.clear();
        inventory.addAll(originalInventory);
    }

    // Search for an item by engine number
    public inventoryItem searchItem(String engineNumber) {
        return inventory.stream()
            .filter(item -> item.getEngineNumber().equalsIgnoreCase(engineNumber))
            .findFirst()
            .orElse(null);
    }

    // Display all items
    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        System.out.println(CSV_HEADER);
        inventory.forEach(System.out::println);
    }
}

