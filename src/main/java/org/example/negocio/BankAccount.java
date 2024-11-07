package org.example.negocio;
import java.util.concurrent.atomic.AtomicLong;

public class BankAccount {
    private static final AtomicLong accountNumberGenerator = new AtomicLong(1000000);
    private String accountNumber;
    private double balance;
    private AccountType accountType;
    private static final double OVERDRAFT_LIMIT = -500.0;

    public BankAccount(AccountType accountType) {
        this.accountNumber = String.valueOf(accountNumberGenerator.incrementAndGet());
        this.balance = 0.0;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive.");
        }
        if (accountType == AccountType.SAVINGS && (balance - amount) < 0) {
            throw new IllegalArgumentException("Savings account cannot have negative balance.");
        }
        if (accountType == AccountType.CHECKING && (balance - amount) < OVERDRAFT_LIMIT) {
            throw new IllegalArgumentException("Overdraft limit exceeded.");
        }
        balance -= amount;
    }
}
