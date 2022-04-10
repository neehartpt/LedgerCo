package com.example.geektrust.models;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Person> borrowers;

    public Bank(String name) {
        this.name = name;
        borrowers = new ArrayList<>();
    }
    public String getName() {
        return this.name;
    }

    public boolean addBorrower(Person borrower) {
        try {
            borrowers.add(borrower);
        } catch(Exception e) {
            System.out.println("Failure in adding Borrower." + e.getMessage());
            return false;
        }
        return true;
    }

    public List<Person> getBorrowers() {
        return this.borrowers;
    }
}
