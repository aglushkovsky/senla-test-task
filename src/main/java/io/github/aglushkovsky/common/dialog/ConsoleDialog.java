package io.github.aglushkovsky.common.dialog;

import io.github.aglushkovsky.common.input_validator.InputValidator;

import java.util.Objects;
import java.util.Scanner;

public class ConsoleDialog implements Dialog {
    private static final String PROMPT = ">>> ";
    private static final InputValidator EMPTY_INPUT_VALIDATOR = s -> {};

    private final InputValidator inputValidator;

    public ConsoleDialog(InputValidator inputValidator) {
        this.inputValidator = Objects.requireNonNullElse(inputValidator, EMPTY_INPUT_VALIDATOR);
    }

    @Override
    public String getInput() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print(PROMPT);
                String input = readInput(scanner);
                inputValidator.validate(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String readInput(Scanner scanner) {
        return scanner.nextLine().trim();
    }
}
