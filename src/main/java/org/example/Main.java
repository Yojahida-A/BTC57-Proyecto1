package org.example;

import org.example.negocio.AccountType;
import org.example.persistencia.BankService;


public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        //Register a new client
        bankService.registerClient("Mónica", "Arroyo", "45293103", "yojahidaa@gmail.com");
        // open a bank account
        bankService.openBankAccount("45293103", AccountType.SAVINGS);
        // Deposit money
        bankService.deposit("45293103", "1000001", 1500.0);
        // Check balance
        System.out.println("Balance: " + bankService.checkBalance("45293103", "1000001"));

        // Withdraw money
        bankService.withdraw("45293103", "1000001", 900.0);
        // Check balance again
        System.out.println("Balance: " + bankService.checkBalance("45293103", "1000001"));

    }
}