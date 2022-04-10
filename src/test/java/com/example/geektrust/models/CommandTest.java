package com.example.geektrust.models;

import com.example.geektrust.enums.Operator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    @Mock
    Command command;

    @BeforeEach
    void setUp() {
        command = Mockito.mock(Command.class);
    }

    @Test
    void testWithValidInput() {
        String input = "LOAN IDIDI Dale 5000 1 6";
        Command command = Command.createCommand(input);
        assertEquals(Operator.LOAN, command.getOperator());
        assertEquals(Arrays.asList("IDIDI", "Dale", "5000", "1", "6"), command.getOperands());

    }

    @Test
    void testWithValidInputNoException() {
        String input = "LOAN IDIDI Dale 5000 1 6";
        assertDoesNotThrow(() -> Command.createCommand(input));
    }

    @Test
    void testWithInvalidNumOfArguments() {
        String input = "BALANCE IDIDI Dale";
        assertThrows(UnsupportedOperationException.class, () -> Command.createCommand(input));
    }

    @Test
    void testConstructorWithNullValues() {
        assertThrows(NullPointerException.class, () -> new Command(Operator.LOAN, null));
        assertThrows(NullPointerException.class, () -> new Command(null, null));
        assertThrows(NullPointerException.class, () -> new Command(null, new ArrayList<>()));
    }
}
