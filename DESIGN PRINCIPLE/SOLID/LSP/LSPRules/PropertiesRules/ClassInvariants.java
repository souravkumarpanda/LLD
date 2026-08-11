package solid.LSP.LSPRules.PropertiesRules;

// Class Invariant of a parent class Object should not be broken by a child class Object.
// Hence, child class can either maintain or strengthen the invariant but never narrow it down.

// Invariant: Balance cannot be negative
class BankAccounts {
    protected double balance;

    public BankAccounts(double b) {
        if (b < 0) throw new IllegalArgumentException("Balance can't be negative");
        balance = b;
    }

    public void withdraw(double amount) {
        if (balance - amount < 0) throw new RuntimeException("Insufficient funds");
        balance -= amount;
        System.out.println("Amount withdrawn. Remaining balance is " + balance);
    }
}

// Breaks invariant: Should not be allowed.
class CheatAccount extends BankAccounts {
    public CheatAccount(double b) {
        super(b);
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount; // LSP break! Negative balance allowed
        System.out.println("Amount withdrawn. Remaining balance is " + balance);
    }
}

public class ClassInvariants {
    public static void main(String[] args) {
        BankAccounts bankAccount = new BankAccounts(100);
        bankAccount.withdraw(100);
    }
}

