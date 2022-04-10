package com.example.geektrust.models;

public class Person {
    private String name;
    private Loan loan;
    private String bankName;

    public Person(String name, Loan loan, String bankName) {
        this.name = name;
        this.loan = loan;
        this.bankName = bankName;
    }

    public String getName() {
        return this.name;
    }

    public Loan getLoan() {
        return this.loan;
    }

    public String getBankName() {
        return bankName;
    }
}
