package com.example.geektrust;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.security.InvalidParameterException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Mock
    Main main;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testInvalidArguments() {
        assertThrows(InvalidParameterException.class, () -> Main.main(new String[]{}));
    }

    @Test
    void testException() {
        assertThrows(RuntimeException.class, () -> Main.main(new String[]{"invalid-file.txt"}));
    }

    @Test
    void testValidFileInput() {
        assertDoesNotThrow(() -> Main.main(new String[]{"src/test/data/test-data.txt"}));
    }
}