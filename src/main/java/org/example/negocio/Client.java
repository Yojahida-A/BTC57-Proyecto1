package org.example.negocio;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/*
The Client class consists of 5 attributes and three methods, where the Client method is
the constructor, the openAccount method allows opening a bank account type, and finally
 the getAccount method returns a list of bank accounts.
 */
public class Client {
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private List<BankAccount> accounts = new ArrayList<>();

    /*
    The constructor method validates that all required fields are provided, otherwise it
    throws an exception. In the condition to validate the email format, the Pattern library
    is used, if in case we enter a wrong format it also throws an error.
    */
    public Client(String firstName, String lastName, String dni, String email) {
        if(firstName == null || lastName == null || dni == null || email == null) {
            throw new IllegalArgumentException("All fields are mandatory.");
        }
        if(!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        // If the validations are correct, the client attributes are initialized.
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
    }
// Allows a customer to open a new bank account of a specific type (Savings or Checking).
    public void openAccount(AccountType accountType) {
        accounts.add(new BankAccount(accountType));
    }
// Returns the list of the customer's bank accounts.
    public List<BankAccount> getAccounts() {
        return accounts;
    }
}
