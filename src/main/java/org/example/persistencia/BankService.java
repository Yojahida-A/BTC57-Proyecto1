package org.example.persistencia;

import org.example.negocio.AccountType;
import org.example.negocio.BankAccount;
import org.example.negocio.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
The BankService class handles the operations of the bank. HashMap and Optional are
used to handle the storage of customers and accounts.
 */
public class BankService {
    private Map<String, Client> clients = new HashMap<>();

    /*
    The registerClient method registers a new client. If a client with the same ID already exists,
    it throws an exception.
     */
    public void registerClient(String firstName, String lastName, String dni, String email) {
        if (clients.containsKey(dni)) {
            throw new IllegalArgumentException("Client with DNI already exists.");
        }
        clients.put(dni, new Client(firstName, lastName, dni, email));
    }

    /*
    The openBankAccount method allows a customer to open a bank account of a specific type.
     */
    public void openBankAccount(String dni, AccountType accountType) {
        Client client = getClientByDni(dni);
        client.openAccount(accountType);
    }

    /*
    The deposit method allows a deposit to be made into a client's account.
     */
    public void deposit(String dni, String accountNumber, double amount) {
        BankAccount account = getAccountByNumber(dni, accountNumber);
        account.deposit(amount);
    }

    /*
    The withdraw method allows a client to withdraw money from their account.
     */
    public void withdraw(String dni, String accountNumber, double amount) {
        BankAccount account = getAccountByNumber(dni, accountNumber);
        account.withdraw(amount);
    }

    /*
    The check Balance method returns the balance of a specific bank account.
     */
    public double checkBalance(String dni, String accountNumber) {
        BankAccount account = getAccountByNumber(dni, accountNumber);
        return account.getBalance();
    }

    /*
    The getClientByDni method finds a client by its ID. If it does not find one, it throws an exception.
     */
    private Client getClientByDni(String dni) {
        return Optional.ofNullable(clients.get(dni))
                .orElseThrow(() -> new IllegalArgumentException("Client not found."));
    }

    /*
    The getAccountByNumber method looks up a bank account by its number, using Streams.
    If not found, it throws an exception.
     */
    private BankAccount getAccountByNumber(String dni, String accountNumber) {
        Client client = getClientByDni(dni);
        return client.getAccounts().stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found."));
    }


}
