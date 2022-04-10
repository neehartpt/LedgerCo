package com.example.geektrust.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Loan {
    private long principal;
    private int termDuration;
    private int rateOfInterest;

    private double totalAmountPayable;
    private long emi;

    @Setter
    private Map<Integer, Long> lumpSumRecord;

    public Loan(long principal,int termDuration, int rateOfInterest) {
        this.principal = principal;
        this.termDuration = termDuration;
        this.rateOfInterest = rateOfInterest;

        double interest = (principal * rateOfInterest * termDuration) / 100;
        totalAmountPayable = principal + interest;
        double installment = totalAmountPayable / (12 * termDuration);

        this.emi = Double.valueOf(Math.ceil(installment)).longValue();

        this.lumpSumRecord = new HashMap<>();
    }
}
