package org.example.persistencia;

import org.example.negocio.AccountType;
import org.example.negocio.BankAccount;
import org.example.negocio.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BankService {
    private Map<String, Client> clients = new HashMap<>();

    public void registerClient(String firstName, String lastName, String dni, String email) {
        if (clients.containsKey(dni)) {
            throw new IllegalArgumentException("Client with DNI already exists.");
        }
        clients.put(dni, new Client(firstName, lastName, dni, email));
    }

    public void openBankAccount(String dni, AccountType accountType) {
        Client client = getClientByDni(dni);
        client.openAccount(accountType);
    }

    public void deposit(String dni, String accountNumber, double amount) {
        BankAccount account = getAccountByNumber(dni, accountNumber);
        account.deposit(amount);
    }

    public void withdraw(String dni, String accountNumber, double amount) {
        BankAccount account = getAccountByNumber(dni, accountNumber);
        account.withdraw(amount);
    }

    public double checkBalance(String dni, String accountNumber) {
        BankAccount account = getAccountByNumber(dni, accountNumber);
        return account.getBalance();
    }

    private Client getClientByDni(String dni) {
        return Optional.ofNullable(clients.get(dni))
                .orElseThrow(() -> new IllegalArgumentException("Client not found."));
    }

    private BankAccount getAccountByNumber(String dni, String accountNumber) {
        Client client = getClientByDni(dni);
        return client.getAccounts().stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found."));
    }


}
