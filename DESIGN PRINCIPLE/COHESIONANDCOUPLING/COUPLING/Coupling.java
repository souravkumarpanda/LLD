package COHESIONANDCOUPLING.COUPLING;

import java.util.HashMap;
import java.util.Map;
// InventoryService exposes its internal data structure 
class TightInventoryService {
    public Map<String, Integer> stockMap = new HashMap<>(); 
}
// OrderService directly accesses InventoryService's internal field 
class TightOrderService {
    private TightInventoryService inventory = new TightInventoryService();
    public void placeOrder(String item, int qty) {
        int current = inventory.stockMap.getOrDefault(item, 0);
        // Directly modifying the internal field 
        inventory.stockMap.put(item, current - qty); 
    } 
}
// InventoryService hides its internal data structure 
class LooseInventoryService {
    private Map<String, Integer> stockMap = new HashMap<>();
    public void reduceStock(String item, int qty) {
        int current = stockMap.getOrDefault(item, 0);
        stockMap.put(item, current - qty); 
    }
    public void addStock(String item, int qty) {
        int current = stockMap.getOrDefault(item, 0);
        stockMap.put(item, current + qty); 
    }
    public int getStock(String item) { 
        return stockMap.getOrDefault(item, 0); 
    } 
}
// OrderService only knows the behavior of InventoryService 
class LooseOrderService {
    private LooseInventoryService inventory;
    // Dependency Injection 
    public LooseOrderService(LooseInventoryService inventory) { 
        this.inventory = inventory; 
    }
    public void placeOrder(String item, int qty) {
        // Calls behavior instead of accessing internal data 
        inventory.reduceStock(item, qty); 
    } 
} 

public class Coupling{
    public static void main(String[] args) { 
       System.out.println("===== TIGHT COUPLING =====");
        TightInventoryService tightInventory = new TightInventoryService();
        // Because the stockMap is public, we can directly modify it
        tightInventory.stockMap.put("Laptop", 10);
        TightOrderService tightOrder = new TightOrderService();
        tightOrder.placeOrder("Laptop", 2);
        System.out.println("Laptop stock after order: " + tightInventory.stockMap.get("Laptop"));
        System.out.println("\n===== LOOSE COUPLING =====");
        LooseInventoryService looseInventory = new LooseInventoryService();
        looseInventory.addStock("Laptop", 10);
        LooseOrderService looseOrder = new LooseOrderService(looseInventory);
        looseOrder.placeOrder("Laptop", 2);
        System.out.println("Laptop stock after order: " + looseInventory.getStock("Laptop"));
    } 
}
