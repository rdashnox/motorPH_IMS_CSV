/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package inventorymanager;

/**
 *
 * @author dashcodes
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class inventoryItem {
    private String dateEntered;
    private String stockLabel;
    private String brand;
    private String engineNumber;
    private String status;

    // Constructor for new items
    public inventoryItem(String brand, String engineNumber) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.dateEntered = LocalDate.now().format(formatter);
        this.stockLabel = "New";
        this.brand = brand.toUpperCase();
        this.engineNumber = engineNumber.toUpperCase();
        this.status = "On-hand";
    }
    
    // Constructor for existing items from CSV
    public inventoryItem(String dateEntered, String stockLabel, String brand, 
                        String engineNumber, String status) {
        this.dateEntered = dateEntered;
        this.stockLabel = stockLabel;
        this.brand = brand.toUpperCase();
        this.engineNumber = engineNumber.toUpperCase();
        this.status = status;
    }

    // Getters
    public String getDateEntered() {
        return dateEntered;
    }

    public String getStockLabel() {
        return stockLabel;
    }

    public String getBrand() {
        return brand;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", 
            dateEntered, stockLabel, brand, engineNumber, status);
    }
}
