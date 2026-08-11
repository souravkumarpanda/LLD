package kiss;

class CalculatorF {

    public double calculate(String operator, double a, double b) {
        switch (operator) {
            case "+":
                return a + b;

            case "-":
                return a - b;

            case "*":
                return a * b;

            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                return a / b;

            default:
                throw new UnsupportedOperationException("Unknown operator: " + operator);
        }
    }
}

public class FollowKISS {
    public static void main(String[] args) {

        CalculatorF calculator = new CalculatorF();

        try {
            System.out.println("Addition: " + calculator.calculate("+", 10, 5));
            System.out.println("Subtraction: " + calculator.calculate("-", 10, 5));
            System.out.println("Multiplication: " + calculator.calculate("*", 10, 5));
            System.out.println("Division: " + calculator.calculate("/", 10, 5));

            // Uncomment to test division by zero
            System.out.println("Division: " + calculator.calculate("/", 10, 0));

            // Uncomment to test an invalid operator
            System.out.println("Result: " + calculator.calculate("%", 10, 5));

        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}