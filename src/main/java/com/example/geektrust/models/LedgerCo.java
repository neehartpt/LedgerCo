package com.example.geektrust.models;

import java.util.*;

public class LedgerCo {
    private String companyName;
    private Map<String, Loan> loanMap;

    public LedgerCo() {
        this.companyName = "LedgerCo";
        this.loanMap = new HashMap<>();
    }
    public String getCompanyName() {
        return companyName;
    }

    public void addLoan(String borrowerName, String bankName, Loan loan) {
        String key = borrowerName.concat(bankName);
        if(!loanMap.containsKey(key)) {
            loanMap.put(key, loan);
        } else {
            throw new RuntimeException("A loan already exists between bank and borrower");
        }
    }

    public Loan getLoan(String borrowerName, String bankName) {
        String key = borrowerName.concat(bankName);
        if(loanMap.containsKey(key)) {
            return loanMap.get(key);
        } else {
            throw new RuntimeException("Given loan details not found");
        }
    }

    /*public void recordLumpSumPayment(Loan loan, long lumpSumAmount, long emiNum) {
        loan.getLumpSumRecord().put(emiNum, lumpSumAmount);
    }*/

    /*public String getBalance(Loan loan, Long emiNum) {
        long emiAmount = loan.getEmi();
        Long[] lumpSumRecordEmi = loan.getLumpSumRecord().keySet().stream().toArray(Long[]::new);
        Long lumpSumPaid = 0l;
        if(lumpSumRecordEmi.length > 0) {
            Arrays.sort(lumpSumRecordEmi);
            for (Long recordMonth : lumpSumRecordEmi) {
                if(emiNum < recordMonth) break;
                lumpSumPaid += loan.getLumpSumRecord().get(recordMonth);
            }
        }
        double totalAmountPayable = loan.getTotalAmountPayable();
        long amountPaid = emiAmount * emiNum + lumpSumPaid;
        long emiNumRemaining = (loan.getTermDuration() * 12) - emiNum;

        double dueRemaining = totalAmountPayable - amountPaid;
        if(dueRemaining > emiAmount) {
            emiNumRemaining = Double.valueOf(
                    Math.ceil(dueRemaining / emiAmount))
                    .longValue();
        } else {
            emiNumRemaining = 1;
        }
        return (amountPaid + " " + emiNumRemaining);
    }*/
}
