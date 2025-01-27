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

public class inventoryItem {
    private String dateEntered;
    private String stockLabel;
    private String brand;
    private String engineNumber;
    private String status;

    public inventoryItem(String brand, String engineNumber, String status) {
        this.dateEntered = LocalDate.now().toString(); // Automatically gather current date
        this.stockLabel = "New"; // Newly entered stock label will always set to "New"
        this.brand = brand;
        this.engineNumber = engineNumber;
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
        return "InventoryItem{" +
                "dateEntered='" + dateEntered + '\'' +
                ", stockLabel='" + stockLabel + '\'' +
                ", brand='" + brand + '\'' +
                ", engineNumber='" + engineNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
