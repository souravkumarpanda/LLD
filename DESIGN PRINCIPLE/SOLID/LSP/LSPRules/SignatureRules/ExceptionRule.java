package solid.LSP.LSPRules.SignatureRules;

// Exception Rule:
// A subclass should throw fewer or narrower exceptions
// (but not additional or broader exceptions) than the parent.
// Java enforces this only for checked Exceptions.

/*
└── java.lang.Exception                        // Conditions your application might want to catch
    ├── java.io.IOException                    // Checked I/O failures
    │   ├── java.io.FileNotFoundException
    │   ├── java.io.EOFException
    │   └── java.net.MalformedURLException
    ├── java.lang.ClassNotFoundException       // Checked reflect/… failures
    ├── java.lang.InterruptedException         // Checked thread interruption
    ├── java.sql.SQLException                  // Checked SQL/database errors
    ├── java.text.ParseException               // Checked parsing errors
    └── java.lang.RuntimeException             // Unchecked; subclasses may be thrown anywhere
        ├── java.lang.ArithmeticException      // e.g. divide by zero
        ├── java.lang.NullPointerException
        ├── java.lang.ArrayIndexOutOfBoundsException
        ├── java.lang.StringIndexOutOfBoundsException
        ├── java.lang.IllegalArgumentException
        │    └── java.lang.NumberFormatException
        ├── java.lang.IllegalStateException
        ├── java.lang.UnsupportedOperationException
        └── java.lang.IndexOutOfBoundsException // parent of the two “…OutOfBounds” above
*/

class Parent1 {
    public void getValue() throws RuntimeException {
        throw new RuntimeException("Parent error");
    }
}

// Subclass overrides getValue and throws the narrower ChildException
class Child1 extends Parent1 {
    @Override
    public void getValue() throws ArithmeticException {
        throw new ArithmeticException("Child error");
        //throw new Exception("Child error"); // This is wrong and not allowed
    }
}

// Client that invokes getValue and catches the parent exception type
class Client1 {
    private Parent1 p;

    public Client1(Parent1 p) {
        this.p = p;
    }

    public void takeValue() {
        try {
            p.getValue();
        } catch (RuntimeException e) {
            System.out.println("RuntimeException occurred: " + e.getMessage());
        }
    }
}

public class ExceptionRule {
    public static void main(String[] args) {
        Parent1 parent = new Parent1();
        Child1 child   = new Child1();

        Client1 client = new Client1(parent);
        //Client1 client = new Client1(child);

        client.takeValue();
    }
}
