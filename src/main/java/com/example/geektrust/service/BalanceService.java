package com.example.geektrust.service;

import com.example.geektrust.models.LedgerCo;
import com.example.geektrust.models.Loan;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class BalanceService implements OperationService {
    @Getter private static final BalanceService serviceInstance = new BalanceService();

    @Override
    public String processOperation(List<String> operands, LedgerCo ledgerCo) {
        String bankName = operands.get(0);
        String borrowerName = operands.get(1);
        int emiNum = Integer.valueOf(operands.get(2));
        Loan loan = ledgerCo.getLoan(borrowerName, bankName);
        String balance = getBalance(loan, emiNum);
        return bankName + " " + borrowerName + " " + balance;
    }

    private String getBalance(Loan loan, int emiNum) {
        long emiAmount = loan.getEmi();
        long lumpSumPaid = getLumpSumPaid(loan, emiNum);
        double totalAmountPayable = loan.getTotalAmountPayable();
        long amountPaid = emiAmount * emiNum + lumpSumPaid;
        int emiNumRemaining;
        double dueRemaining = totalAmountPayable - amountPaid;
        if(dueRemaining <= 0) {
            amountPaid = Double.valueOf(totalAmountPayable).longValue();
            emiNumRemaining = 0;
        }
        else if(dueRemaining > emiAmount) {
            emiNumRemaining = Double.valueOf(Math.ceil(dueRemaining / emiAmount)).intValue();
        } else {
            emiNumRemaining = 1;
        }
        return (amountPaid + " " + emiNumRemaining);
    }

    private long getLumpSumPaid(Loan loan, int emiNum) {
        Integer[] lumpSumRecordEmi = loan.getLumpSumRecord().keySet().stream().toArray(Integer[]::new);
        Long lumpSumPaid = 0l;
        if(lumpSumRecordEmi.length > 0) {
            Arrays.sort(lumpSumRecordEmi);
            for (int recordMonth : lumpSumRecordEmi) {
                if(emiNum < recordMonth) break;
                lumpSumPaid += loan.getLumpSumRecord().get(recordMonth);
            }
        }
        return lumpSumPaid;
    }
}
