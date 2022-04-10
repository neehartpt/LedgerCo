package com.example.geektrust.models;

import com.example.geektrust.enums.Operator;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class Command {
    @NonNull private Operator operator;
    @NonNull private List<String> operands;

    public static Command createCommand(String str) {
        try {
            String[] fullCommand = str.split(" ");
            Operator operator = Operator.valueOf(fullCommand[0]);
            List<String> operands = Arrays.stream(fullCommand).skip(1).collect(Collectors.toList());
            Command command = new Command(operator, operands);
            command.validateOperandCardinality();
            return command;
        } catch(Exception e) {
            throw new UnsupportedOperationException("Invalid command: " + str);
        }
    }

    public void validateOperandCardinality() {
        if (this.operator.getNumOfOperands() != operands.size()) {
            throw new InputMismatchException("The number of arguments does not is not correct.");
        }
    }
}
