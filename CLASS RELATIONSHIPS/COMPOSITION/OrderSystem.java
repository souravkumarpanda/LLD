import java.util.List;
import java.util.ArrayList;

class LineItem {
    private String productName;
    private int quantity;
    private double unitPrice;

    public LineItem(String productName, int quantity, double unitPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public double getSubtotal() {
        return quantity * unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void describe() {
        System.out.printf("%s x%d @ $%.2f = $%.2f%n",
            productName, quantity, unitPrice, getSubtotal());
    }
}

class Order {
    private String orderId;
    private List<LineItem> lineItems;

    public Order(String orderId) {
        this.orderId = orderId;
        this.lineItems = new ArrayList<>();
    }

    public void addItem(String product, int quantity, double unitPrice) {
        lineItems.add(new LineItem(product, quantity, unitPrice));
    }

    public void removeItem(String product) {
        lineItems.removeIf(item -> item.getProductName().equals(product));
    }

    public double getTotal() {
        double total = 0;
        for (LineItem item : lineItems) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void printReceipt() {
        System.out.println("Order: " + orderId);
        for (LineItem item : lineItems) {
            item.describe();
        }
        System.out.printf("Total: $%.2f%n", getTotal());
    }
}

public class OrderSystem {
    public static void main(String[] args) {
        Order order = new Order("ORD-1001");
        order.addItem("Wireless Mouse", 2, 29.99);
        order.addItem("USB-C Cable", 3, 9.99);
        order.addItem("Laptop Stand", 1, 49.99);

        order.printReceipt();

        // When order is deleted, all LineItems are destroyed with it.
        // No LineItem exists outside an Order.
    }
}