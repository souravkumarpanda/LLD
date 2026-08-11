package kiss;

interface Operation {
    double calculate(double a, double b);
}

class Addition implements Operation {
    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}

class Subtraction implements Operation {
    @Override
    public double calculate(double a, double b) {
        return a - b;
    }
}

class Multiplication implements Operation {
    @Override
    public double calculate(double a, double b) {
        return a * b;
    }
}

class Division implements Operation {
    @Override
    public double calculate(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("kiss.Division by zero is not allowed.");
        }
        return a / b;
    }
}

class Calculator {
    public double execute(Operation op, double a, double b) {
        return op.calculate(a, b);
    }
}

public class ViolateKISS {
    public static void main(String[] args) {

        Calculator calc = new Calculator();

        System.out.println("kiss.Addition: " +
                calc.execute(new Addition(), 10, 5));

        System.out.println("kiss.Subtraction: " +
                calc.execute(new Subtraction(), 10, 5));

        System.out.println("kiss.Multiplication: " +
                calc.execute(new Multiplication(), 10, 5));

        try {
            System.out.println("kiss.Division: " +
                    calc.execute(new Division(), 10, 5));

            // Uncomment to test division by zero
            System.out.println("kiss.Division: " +
                    calc.execute(new Division(), 10, 0));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}