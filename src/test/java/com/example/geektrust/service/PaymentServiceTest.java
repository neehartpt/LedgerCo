package com.example.geektrust.service;

import com.example.geektrust.models.LedgerCo;
import com.example.geektrust.models.Loan;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {
    @Mock
    PaymentService service;

    @Mock
    LedgerCo ledgerCo;

    Loan loan;

    @BeforeEach
    void setUp() {
        service = PaymentService.getServiceInstance();
        ledgerCo = new LedgerCo();
        loan = new Loan(10000, 5, 5);
        ledgerCo.addLoan("P1", "B1", loan);
    }

    @Test
    void testLumpSumPayment() {
        service.processOperation(Arrays.asList("B1", "P1", "1000", "5"), ledgerCo);
        assertTrue(ledgerCo.getLoan("P1", "B1").getLumpSumRecord().containsKey(5));
        assertEquals(ledgerCo.getLoan("P1", "B1").getLumpSumRecord().get(5), 1000);
    }

    @Test
    void testInvalidLoan() {
        assertThrows(RuntimeException.class, () -> ledgerCo.getLoan("P2", "B2"));
    }

}
