package COHESIONANDCOUPLING.COHESION;

import java.util.Date;
// LOW COHESION — unrelated jobs dumped together
class Utility {
    public double calculateTax(double amount) {
        return amount * 0.18;
    }
    public String formatDate(Date date) {
        return date.toString();
    }
    public void sendEmail(String to, String body) {
        System.out.println("Emailing " + to);
    }
    public boolean isValidPassword(String pw) {
        return pw.length() >= 8;
    }
}

// HIGH COHESION — everything serves ONE purpose: tax calculation
class TaxCalculator {
    private static final double GST_RATE = 0.18;
    public double calculateGst(double amount) {
        return amount * GST_RATE;
    }
    public double calculateTotalWithTax(double amount) {
        return amount + calculateGst(amount);
    }
}

public class Cohesion {
    public static void main(String[] args) {
        Utility utility = new Utility();
        double tax = utility.calculateTax(1000);
        System.out.println("Tax: " + tax);
        String formattedDate = utility.formatDate(new Date());
        System.out.println("Date: " + formattedDate);
        utility.sendEmail(
                "sourav@gmail.com",
                "Welcome to our service!"
        );
        boolean validPassword = utility.isValidPassword("password123");
        System.out.println("Password valid: " + validPassword);
        System.out.println("\n-------------------------------");
        TaxCalculator taxCalculator = new TaxCalculator();
        double amount = 1000;
        double gst = taxCalculator.calculateGst(amount);
        double total = taxCalculator.calculateTotalWithTax(amount);
        System.out.println("Amount: " + amount);
        System.out.println("GST: " + gst);
        System.out.println("Total with GST: " + total);
    }
}
