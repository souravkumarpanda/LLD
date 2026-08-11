package solid.SRP;

import java.util.ArrayList;
import java.util.List;

// Product class representing any item of any ECommerce.
class Product1 {
    public String name;
    public double price;

    public Product1(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// Violating SRP: ShoppingCart is handling multiple responsibilities
class ShoppingCart1 {
    private List<Product1> products = new ArrayList<>();

    public void addProduct(Product1 p) {
        products.add(p);
    }

    public List<Product1> getProducts() {
        return products;
    }

    // 1. Calculates total price in cart.
    public double calculateTotal() {
        double total = 0;
        for (Product1 p : products) {
            total += p.price;
        }
        return total;
    }

    // 2. Violating SRP - Prints invoice (Should be in a separate class)
    public void printInvoice() {
        System.out.println("Shopping Cart Invoice:");
        for (Product1 p : products) {
            System.out.println(p.name + " - Rs " + p.price);
        }
        System.out.println("Total: Rs " + calculateTotal());
    }

    // 3. Violating SRP - Saves to DB (Should be in a separate class)
    public void saveToDatabase() {
        System.out.println("Saving shopping cart to database...");
    }
}

public class SRPViolated {
    public static void main(String[] args) {
        ShoppingCart1 cart = new ShoppingCart1();

        cart.addProduct(new Product1("Laptop", 50000));
        cart.addProduct(new Product1("Mouse", 2000));

        cart.printInvoice();
        cart.saveToDatabase();
    }
}
