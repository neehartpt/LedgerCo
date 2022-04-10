package com.example.geektrust.enums;

import com.example.geektrust.service.BalanceService;
import com.example.geektrust.service.LoanCreationService;
import com.example.geektrust.service.OperationService;
import com.example.geektrust.service.PaymentService;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Getter
public enum Operator {
    LOAN(5, LoanCreationService::getServiceInstance),
    PAYMENT(4, PaymentService::getServiceInstance),
    BALANCE(3, BalanceService::getServiceInstance);

    @NonNull private Integer numOfOperands;
    @NonNull private Supplier<? extends OperationService> operationService;
}
