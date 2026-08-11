import java.util.List;
import java.util.ArrayList;

interface Flyable {
    void fly();
    String getFlightInfo();
}

class Bird implements Flyable {
    private String species;
    private double wingSpan;

    public Bird(String species, double wingSpan) {
        this.species = species;
        this.wingSpan = wingSpan;
    }

    public void fly() {
        System.out.println(species + " flaps its wings and takes off.");
    }

    public String getFlightInfo() {
        return species + " (wingspan: " + wingSpan + "m, powered by muscle)";
    }
}

class Airplane implements Flyable {
    private String model;
    private int maxAltitude;

    public Airplane(String model, int maxAltitude) {
        this.model = model;
        this.maxAltitude = maxAltitude;
    }

    public void fly() {
        System.out.println(model + " engines roar as it accelerates down the runway.");
    }

    public String getFlightInfo() {
        return model + " (max altitude: " + maxAltitude + "ft, powered by jet engines)";
    }
}

class Drone implements Flyable {
    private int batteryLevel;
    private double maxRange;

    public Drone(int batteryLevel, double maxRange) {
        this.batteryLevel = batteryLevel;
        this.maxRange = maxRange;
    }

    public void fly() {
        System.out.println("Drone propellers spin up. Battery at " + batteryLevel + "%.");
    }

    public String getFlightInfo() {
        return "Drone (range: " + maxRange + "km, battery: " + batteryLevel + "%)";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Flyable> flyingThings = new ArrayList<>();
        flyingThings.add(new Bird("Eagle", 2.3));
        flyingThings.add(new Airplane("Boeing 737", 41000));
        flyingThings.add(new Drone(85, 10.0));

        for (Flyable flyer : flyingThings) {
            System.out.println(flyer.getFlightInfo());
            flyer.fly();
            System.out.println();
        }
    }
}
