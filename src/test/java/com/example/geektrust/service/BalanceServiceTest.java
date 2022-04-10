package com.example.geektrust.service;

import com.example.geektrust.models.LedgerCo;
import com.example.geektrust.models.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BalanceServiceTest {
    @Mock
    BalanceService service;

    @Mock
    LedgerCo ledgerCo;

    Loan loan;

    @BeforeEach
    void setUp() {
        service = BalanceService.getServiceInstance();
        ledgerCo = new LedgerCo();
        Loan loan = new Loan(10000, 5, 5);
        ledgerCo.addLoan("P1", "B1", loan);

    }

    @Test
    void testGetBalanceOperation() {
        String balance1 = service.processOperation(Arrays.asList("B1", "P1", "0"), ledgerCo);
        assertEquals(balance1, "B1 P1 0 60");
        String balance2 = service.processOperation(Arrays.asList("B1", "P1", "60"), ledgerCo);
        assertEquals(balance2, "B1 P1 12500 0");
    }

    @Test
    void testBalanceAfterLumpSumPayment() {
        ledgerCo.getLoan("P1", "B1").getLumpSumRecord().put(5, 1000l);
        String balance1 = service.processOperation(Arrays.asList("B1", "P1", "5"), ledgerCo);
        assertEquals(balance1, "B1 P1 2045 51");
        String balance2 = service.processOperation(Arrays.asList("B1", "P1", "3"), ledgerCo);
        assertEquals(balance2, "B1 P1 627 57");
    }

    @Test
    void testFinalEmiWithDueHigherThanEmi() {
        ledgerCo.getLoan("P1", "B1").getLumpSumRecord().put(5, 1000l);
        String balance = service.processOperation(Arrays.asList("B1", "P1", "55"), ledgerCo);
        assertEquals(balance, "B1 P1 12495 1");
    }
}
