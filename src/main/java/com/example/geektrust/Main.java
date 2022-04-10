package com.example.geektrust;

import com.example.geektrust.models.Command;
import com.example.geektrust.models.LedgerCo;
import com.example.geektrust.service.FileProcessorService;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args)  {
        try {
            if (args.length != 1) {
                throw new InvalidParameterException();
            }
            String filePath = args[0];
            // parse the input file
            FileProcessorService fileProcessorService = new FileProcessorService(filePath);
            List<Command> commands = fileProcessorService.parseCommands();

            LedgerCo ledgerCo = new LedgerCo();

            List<String> outputs =
                    commands.stream()
                        .map(
                            command ->
                                command
                                    .getOperator()
                                    .getOperationService()
                                    .get()
                                    .processOperation(command.getOperands(), ledgerCo))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            outputs.forEach(System.out::println);
        } catch (InvalidParameterException e) {
            throw new InvalidParameterException("Please provide input file path as the only argument");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
	}
}
