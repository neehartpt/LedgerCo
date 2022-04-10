package com.example.geektrust.service;

import com.example.geektrust.models.LedgerCo;
import com.example.geektrust.models.Loan;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

public class PaymentService implements OperationService{
    @Getter private static final PaymentService serviceInstance = new PaymentService();

    @Override
    public String processOperation(List<String> operands, LedgerCo ledgerCo) {
        String bankName = operands.get(0);
        String borrowerName = operands.get(1);
        Long lumpSumAmount = Long.valueOf(operands.get(2));
        int emiNum = Integer.valueOf(operands.get(3));
        Loan loan = ledgerCo.getLoan(borrowerName, bankName);
        loan.getLumpSumRecord().put(emiNum, lumpSumAmount);
        return null;
    }
}
