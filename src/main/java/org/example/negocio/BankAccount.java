package org.example.negocio;
import java.util.concurrent.atomic.AtomicLong;

/*
In the bankAccount class, five attributes and five methods were created. For the first attribute,
the AtomicLong class is used to automatically generate the account numbers.
*/

public class BankAccount {
    private static final AtomicLong accountNumberGenerator = new AtomicLong(1000000);
    private String accountNumber;
    private double balance;
    private AccountType accountType;
    private static final double OVERDRAFT_LIMIT = -500.0;

    /*The constructor method initializes a bank account with a unique account number,
     a balance of 0.0, and the account type (Savings or Checking).
     */
    public BankAccount(AccountType accountType) {
        this.accountNumber = String.valueOf(accountNumberGenerator.incrementAndGet());
        this.balance = 0.0;
        this.accountType = accountType;
    }

    // Returns the account number of the bank account.
    public String getAccountNumber() {
        return accountNumber;
    }

    //Returns the current balance of the account.
    public double getBalance() {
        return balance;
    }

    //Returns the account type (Savings or Checking).
    public AccountType getAccountType() {
        return accountType;
    }

    /* Allows you to deposit money into the account. If the amount is less than or equal to zero,
     it throws an exception.
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    /*
    Allows you to withdraw money. Check that the overdraft limit on current accounts
    is not exceeded and that savings accounts do not go into the red.
     */
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
