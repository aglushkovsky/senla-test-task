package io.github.aglushkovsky.hangman;

import io.github.aglushkovsky.common.input_validator.InputValidator;

public class RuLetterValidator implements InputValidator {
    @Override
    public void validate(String input) {
        if (!isSingleLetter(input)) {
            throw new IllegalArgumentException("Invalid letter: " + input);
        }

        if (!Character.isLetter(input.charAt(0))) {
            throw new IllegalArgumentException("Invalid letter: " + input);
        }

        if (!isRuLetter(input.charAt(0))) {
            throw new IllegalArgumentException("Invalid letter: " + input);
        }
    }

    private boolean isSingleLetter(String input) {
        return input.length() == 1;
    }

    private boolean isRuLetter(char c) {
        return Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CYRILLIC;
    }
}
