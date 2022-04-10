package com.example.geektrust.service;

import com.example.geektrust.models.LedgerCo;

import java.util.List;

public interface OperationService {
    String processOperation(List<String> operands, LedgerCo ledgerCo);
}
