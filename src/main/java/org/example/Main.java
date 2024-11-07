package org.example;

import org.example.persistencia.BankService;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankService();

        bankService.registerClient("Mónica", "Arroyo", "45293103", "yojahidaa@gmail.com");



    }
}