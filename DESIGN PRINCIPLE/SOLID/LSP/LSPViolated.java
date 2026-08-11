package solid.LSP;

import java.util.ArrayList;
import java.util.List;

// Account interface
interface Accounts {
    void deposit(double amount);
    void withdraw(double amount);
}

class SavingAccount2 implements Accounts {
    private double balance;

    public SavingAccount2() {
        balance = 0;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " in Savings Account. New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " from Savings Account. New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds in Savings Account!");
        }
    }
}

class CurrentAccount2 implements Accounts {
    private double balance;

    public CurrentAccount2() {
        balance = 0;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " in Current Account. New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " from Current Account. New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds in Current Account!");
        }
    }
}

class FixedTermAccount2 implements Accounts {
    private double balance;

    public FixedTermAccount2() {
        balance = 0;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " in Fixed Term Account. New Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawal not allowed in Fixed Term Account!");
    }
}

class BankClient2 {
    private List<Accounts> accounts;

    public BankClient2(List<Accounts> accounts) {
        this.accounts = accounts;
    }

    public void processTransactions() {
        for (Accounts acc : accounts) {
            acc.deposit(1000);  // All accounts allow deposits

            // Assuming all accounts support withdrawal (LSP Violation)
            try {
                acc.withdraw(500);
            } catch (UnsupportedOperationException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }
}

public class LSPViolated {
    public static void main(String[] args) {
        List<Accounts> accounts = new ArrayList<>();
        accounts.add(new SavingAccount2());
        accounts.add(new CurrentAccount2());
        accounts.add(new FixedTermAccount2());

        BankClient2 client = new BankClient2(accounts);
        client.processTransactions(); // Throws exception when withdrawing from FixedTermAccount
    }
}
