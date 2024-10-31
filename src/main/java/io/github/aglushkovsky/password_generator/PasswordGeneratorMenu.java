package io.github.aglushkovsky.password_generator;

import io.github.aglushkovsky.common.dialog.ConsoleDialog;
import io.github.aglushkovsky.common.dialog.Dialog;
import io.github.aglushkovsky.common.input_validator.RangeValidator;

public class PasswordGeneratorMenu {
    private final Dialog dialog;
    private final PasswordGenerator generator;

    public PasswordGeneratorMenu() {
        this.generator = new PasswordGenerator();
        RangeValidator rangeValidator = new RangeValidator(generator.getMinLength(), generator.getMaxLength());
        this.dialog = new ConsoleDialog(rangeValidator);
    }

    public void start() {
        int passwordLength = getPasswordLength();
        String generated = generator.generate(passwordLength);
        displayGeneratedPassword(generated);
    }

    private void displayGeneratedPassword(String generated) {
        System.out.println("Результат: " + generated);
    }

    private int getPasswordLength() {
        System.out.println("Введите количество символов (от 8 до 12)");
        return Integer.parseInt(dialog.getInput());
    }
}
