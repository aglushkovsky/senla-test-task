package io.github.aglushkovsky.common.input_validator;

public class RangeValidator implements InputValidator {
    private final int min;
    private final int max;

    public RangeValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(String input) {
        if (!isInteger(input)) {
            throw new IllegalArgumentException(input + " is not a valid integer");
        }

        int integerInput = Integer.parseInt(input);
        if (integerInput < min || integerInput > max) {
            throw new IllegalArgumentException("Input must be between " + min + " and " + max);
        }
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
