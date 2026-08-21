package COHESIONANDCOUPLING.INVENTORY_MANAGEMENT_SYSTEM.violation;

import java.util.HashMap;
import java.util.Map;

class InventoryManager {
    // Public field — bad encapsulation
    public Map<String, Integer> stock = new HashMap<>();
    public void addStock(String item, int qty) {
        stock.put(item, stock.getOrDefault(item, 0) + qty);
    }
    // Unrelated concern #1: order processing
    // is crammed into InventoryManager
    public void processOrder(String item, int qty) {
        if (stock.getOrDefault(item, 0) < qty) {
            System.out.println("Not enough stock!");
            return;
        }
        stock.put(item, stock.get(item) - qty);
        // Unrelated concern #2: email notification
        // is also crammed into InventoryManager
        System.out.println("Sending low-stock alert email if needed...");
        if (stock.get(item) < 5) {
            System.out.println("ALERT: " + item + " low on stock!");
        }
    }
}
class OrderProcessor {
    // Hardwired dependency — tight coupling
    private InventoryManager manager = new InventoryManager();
    public void checkout(String item, int qty) {
        // Directly accessing manager's public field
        // This creates tight coupling
        if (manager.stock.getOrDefault(item, 0) >= qty) {
            manager.processOrder(item, qty);
        }
    }
}

public class ViolationCC {
    public static void main(String[] args) {
        // Create OrderProcessor
        OrderProcessor processor = new OrderProcessor();
        // Add stock
        // Note: Because manager is private inside OrderProcessor,
        // we cannot access it directly from here.
        System.out.println("Order processing started.");
        processor.checkout("Laptop", 2);
        System.out.println("Order processing completed.");
    }
}
