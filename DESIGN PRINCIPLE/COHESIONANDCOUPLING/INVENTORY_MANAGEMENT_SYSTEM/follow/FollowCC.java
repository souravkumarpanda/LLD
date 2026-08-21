package COHESIONANDCOUPLING.INVENTORY_MANAGEMENT_SYSTEM.follow;

import java.util.HashMap;
import java.util.Map;

class InventoryRepository {
    private Map<String, Integer> stock = new HashMap<>();
    public void addStock(String item, int qty) {
        stock.put(item, stock.getOrDefault(item, 0) + qty);
    }
    public boolean hasEnoughStock(String item, int qty) {
        return stock.getOrDefault(item, 0) >= qty;
    }
    public void reduceStock(String item, int qty) {
        stock.put(item, stock.getOrDefault(item, 0) - qty);
    }
    public int getStockLevel(String item) {
        return stock.getOrDefault(item, 0);
    }
}
class StockAlertService {
    private static final int LOW_STOCK_THRESHOLD = 5;
    public void checkAndAlert(String item, int currentLevel) {
        if (currentLevel < LOW_STOCK_THRESHOLD) {
            System.out.println("ALERT: " + item + " low on stock!");
        }
    }
}
class OrderProcessor {
    private InventoryRepository inventory;
    private StockAlertService alertService;
    // Dependencies are injected, not hardwired
    public OrderProcessor(InventoryRepository inventory, StockAlertService alertService) {
        this.inventory = inventory;
        this.alertService = alertService;
    }
    public void checkout(String item, int qty) {
        if (!inventory.hasEnoughStock(item, qty)) {
            System.out.println("Not enough stock!");
            return;
        }
        inventory.reduceStock(item, qty);
        alertService.checkAndAlert(item, inventory.getStockLevel(item));
    }
}

public class FollowCC {
    public static void main(String[] args) {
        // Create dependencies
        InventoryRepository inventory = new InventoryRepository();
        StockAlertService alertService = new StockAlertService();
        // Add initial stock
        inventory.addStock("Laptop", 10);
        inventory.addStock("Mouse", 6);
        // Dependency Injection
        OrderProcessor orderProcessor = new OrderProcessor(inventory, alertService);
        System.out.println("Initial Laptop stock: " + inventory.getStockLevel("Laptop"));
        orderProcessor.checkout("Laptop", 6);
        System.out.println("Remaining Laptop stock: " + inventory.getStockLevel("Laptop"));
        System.out.println("\nInitial Mouse stock: " + inventory.getStockLevel("Mouse"));
        orderProcessor.checkout("Mouse", 2);
        System.out.println("Remaining Mouse stock: " + inventory.getStockLevel("Mouse"));
        System.out.println("\nTrying to order 20 Laptops...");
        orderProcessor.checkout("Laptop", 20);
    }
}
