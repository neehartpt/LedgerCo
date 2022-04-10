package com.example.geektrust.service;

import com.example.geektrust.models.Command;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class FileProcessorService {
    @NonNull private String filePath;

    public List<Command> parseCommands() {
        try (Stream<String> lines = Files.lines(Paths.get(this.filePath))) {

            return lines
                    .filter(line -> !isEmpty(line))
                    .map(Command::createCommand)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error while reading from input file: " +  filePath);
        }
    }

    private boolean isEmpty(String line) {
        return (line.isEmpty() || line.trim().isEmpty());
    }
}
