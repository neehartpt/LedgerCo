package com.example.geektrust.service;

import com.example.geektrust.models.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileProcessorServiceTest {
    @Mock
    FileProcessorService service;

    @Mock
    Command command;

    @BeforeEach
    void setUp() {
        service = new FileProcessorService("src/test/data/test-data.txt");

    }

    @Test
    void testParsingSuccess() {
        assertDoesNotThrow(() -> service.parseCommands());
    }

    @Test
    void testParsingException() {
        service = new FileProcessorService("src/test/data/invalid-file.txt");
        assertThrows(RuntimeException.class, () -> service.parseCommands());
    }

    @Test
    void testNullFilePath() {
        assertThrows(NullPointerException.class, () -> new FileProcessorService(null));
    }
}
