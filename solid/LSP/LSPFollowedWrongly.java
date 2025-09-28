package solid.LSP;

import java.util.ArrayList;
import java.util.List;

// Account interface
interface Account {
    void deposit(double amount);
    void withdraw(double amount);
}

class SavingAccount1 implements Account {
    private double balance;

    public SavingAccount1() {
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

class CurrentAccount1 implements Account {
    private double balance;

    public CurrentAccount1() {
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

class FixedTermAccount1 implements Account {
    private double balance;

    public FixedTermAccount1() {
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

class BankClient1 {
    private List<Account> accounts;

    public BankClient1(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void processTransactions() {
        for (Account acc : accounts) {
            acc.deposit(1000);

            // Checking account type explicitly
            if (acc instanceof FixedTermAccount) {
                System.out.println("Skipping withdrawal for Fixed Term Account.");
            } else {
                try {
                    acc.withdraw(500);
                } catch (UnsupportedOperationException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
        }
    }
}

public class LSPFollowedWrongly {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingAccount1());
        accounts.add(new CurrentAccount1());
        accounts.add(new FixedTermAccount1());

        BankClient1 client = new BankClient1(accounts);
        client.processTransactions();
    }
}
