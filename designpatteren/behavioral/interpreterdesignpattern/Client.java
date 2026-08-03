interface Expression {
    int interpret();
}

class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

class AdditionExpression implements Expression {
    private Expression left;
    private Expression right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

class MultiplicationExpression implements Expression {
    private Expression left;
    private Expression right;

    public MultiplicationExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() * right.interpret();
    }
}

public class Client {
    public static void main(String[] args) {

        // Manually building AST for 2 + 3 * 4
        Expression expression = new AdditionExpression(
            new NumberExpression(2),
            new MultiplicationExpression(
                new NumberExpression(3),
                new NumberExpression(4)
            )
        );

        int result = expression.interpret();
        System.out.println("Result: " + result);
    }
}
