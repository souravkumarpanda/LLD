package solid.OCP;

import java.util.ArrayList;
import java.util.List;

// Product class representing any item in eCommerce.
class Products {
    String name;
    double price;

    Products(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// 1. ShoppingCart: Only responsible for Cart related business logic.
class ShoppingCarts {
    private List<Products> products = new ArrayList<>();

    void addProduct(Products p) {
        products.add(p);
    }

    List<Products> getProducts() {
        return products;
    }

    double calculateTotal() {
        double total = 0;
        for (Products p : products) {
            total += p.price;
        }
        return total;
    }
}

// 2. ShoppingCartPrinter: Only responsible for printing invoices
class ShoppingCartPrinters {
    private ShoppingCarts cart;

    ShoppingCartPrinters(ShoppingCarts cart) {
        this.cart = cart;
    }

    void printInvoice() {
        System.out.println("Shopping Cart Invoice:");
        for (Products p : cart.getProducts()) {
            System.out.println(p.name + " - Rs " + p.price);
        }
        System.out.println("Total: Rs " + cart.calculateTotal());
    }
}

// 3. ShoppingCartStorage: Only responsible for saving cart to DB
class ShoppingCartStorage {
    private ShoppingCarts cart;

    ShoppingCartStorage(ShoppingCarts cart) {
        this.cart = cart;
    }

    void saveToSQLDatabase() {
        System.out.println("Saving shopping cart to SQL DB...");
    }

    void saveToMongoDatabase() {
        System.out.println("Saving shopping cart to Mongo DB...");
    }

    void saveToFile() {
        System.out.println("Saving shopping cart to File...");
    }
}

public class OCPViolated {
    public static void main(String[] args) {
        ShoppingCarts cart = new ShoppingCarts();

        cart.addProduct(new Products("Laptop", 50000));
        cart.addProduct(new Products("Mouse", 2000));

        ShoppingCartPrinters printer = new ShoppingCartPrinters(cart);
        printer.printInvoice();

        ShoppingCartStorage db = new ShoppingCartStorage(cart);
        db.saveToSQLDatabase();
    }
}
