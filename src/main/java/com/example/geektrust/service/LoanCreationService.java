package com.example.geektrust.service;

import com.example.geektrust.models.Bank;
import com.example.geektrust.models.LedgerCo;
import com.example.geektrust.models.Loan;
import com.example.geektrust.models.Person;
import lombok.Getter;

import java.util.List;

public class LoanCreationService implements OperationService{
    @Getter private static final LoanCreationService serviceInstance = new LoanCreationService();

    @Override
    public String processOperation(List<String> operands, LedgerCo ledgerCo) {
        String bankName = operands.get(0);
        String borrowerName = operands.get(1);
        Long principal = Long.valueOf(operands.get(2));
        int term = Integer.valueOf(operands.get(3));
        int rate = Integer.valueOf(operands.get(4));
        Loan loan = new Loan(principal, term, rate);
        ledgerCo.addLoan(borrowerName, bankName, loan);
        return null;
    }
}
