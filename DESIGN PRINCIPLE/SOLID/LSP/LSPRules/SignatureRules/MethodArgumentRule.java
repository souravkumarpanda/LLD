package solid.LSP.LSPRules.SignatureRules;

// Method Argument Rule:
// Subtype method arguments can be identical or wider than the supertype
// Java enforces this by requiring the same method signature for overrides

class Parents {
    public void print(String msg) {
        System.out.println("Parent: " + msg);
    }
}

class Children extends Parents {
    @Override
    public void print(String msg) {
        System.out.println("Child: " + msg);
    }
}

// Client that passes a String msg as the client expects.
class Clients {
    private Parents p;

    public Clients(Parents p) {
        this.p = p;
    }

    public void printMsg() {
        p.print("Hello");

    }
}

public class MethodArgumentRule {
    public static void main(String[] args) {
        Parents parent = new Parents();
        Parents child  = new Children();

        Clients client = new Clients(parent);
//        Clients client = new Clients(child);

        client.printMsg();
    }
}
