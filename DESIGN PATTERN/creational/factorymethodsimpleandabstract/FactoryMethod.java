package designpatteren.creational.factorymethodsimpleandabstract;

// Product Interface and subclasses
interface Burger1 {
    void prepare();
}

class BasicBurger1 implements Burger1 {
    public void prepare() {
        System.out.println("Preparing Basic Burger with bun, patty, and ketchup!");
    }
}

class StandardBurger1 implements Burger1 {
    public void prepare() {
        System.out.println("Preparing Standard Burger with bun, patty, cheese, and lettuce!");
    }
}

class PremiumBurger1 implements Burger1 {
    public void prepare() {
        System.out.println(
                "Preparing Premium Burger with gourmet bun, premium patty, cheese, lettuce, and secret sauce!");
    }
}

class BasicWheatBurger1 implements Burger1 {
    public void prepare() {
        System.out.println("Preparing Basic Wheat Burger with bun, patty, and ketchup!");
    }
}

class StandardWheatBurger1 implements Burger1 {
    public void prepare() {
        System.out.println("Preparing Standard Wheat Burger with bun, patty, cheese, and lettuce!");
    }
}

class PremiumWheatBurger1 implements Burger1 {
    public void prepare() {
        System.out.println(
                "Preparing Premium Wheat Burger with gourmet bun, premium patty, cheese, lettuce, and secret sauce!");
    }
}

// Factory Interface and Concrete Factories
interface BurgerFactory1 {
    Burger1 createBurger(String type);
}

class SinghBurger1 implements BurgerFactory1 {
    public Burger1 createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicBurger1();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardBurger1();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumBurger1();
        } else {
            System.out.println("Invalid burger type!");
            return null;
        }
    }
}

class KingBurger1 implements BurgerFactory1 {
    public Burger1 createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicWheatBurger1();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardWheatBurger1();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumWheatBurger1();
        } else {
            System.out.println("Invalid burger type!");
            return null;
        }
    }
}

// Main Class
public class FactoryMethod {
    public static void main(String[] args) {
        String type = "basic";

        BurgerFactory1 myFactory = new SinghBurger1();
        Burger1 burger = myFactory.createBurger(type);

        if (burger != null) {
            burger.prepare();
        }
    }
}
