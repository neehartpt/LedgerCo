package com.example.geektrust.service;

import com.example.geektrust.models.LedgerCo;
import com.example.geektrust.models.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class LoanCreationServiceTest {

    @Mock
    LoanCreationService service;

    @Mock
    LedgerCo ledgerCo;

    Loan loan;

    @BeforeEach
    void setUp() {
        service = LoanCreationService.getServiceInstance();
        ledgerCo = new LedgerCo();
    }

    @Test
    void testLoanCreationOperation() {
        service.processOperation(Arrays.asList("B1", "P1", "10000", "5", "5"), ledgerCo);
        loan = ledgerCo.getLoan("P1", "B1");
        assertNotNull(loan);
        assertEquals(loan.getPrincipal(), 10000);
        assertEquals(loan.getTermDuration(), 5);
        assertEquals(loan.getEmi(), 209);
        assertEquals(loan.getRateOfInterest(), 5);
    }

    @Test
    void testDuplicateLoanException() {
        service.processOperation(Arrays.asList("B1", "P1", "10000", "5", "5"), ledgerCo);
        loan = ledgerCo.getLoan("P1", "B1");
        assertThrows(RuntimeException.class,
                () -> service.processOperation(Arrays.asList("B1", "P1", "3000", "5", "3"), ledgerCo));
    }
}
