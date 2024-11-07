package org.example.negocio;
import  java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Client {
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private List<BankAccount> accounts = new ArrayList<>();

    public Client(String firstName, String lastName, String dni, String email) {
        if(firstName == null || lastName == null || dni == null || email == null) {
            throw new IllegalArgumentException("All fields are mandatory.");
        }
        if(!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
    }

    public void openAccount(AccountType accountType) {
        accounts.add(new BankAccount(accountType));
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }
}
