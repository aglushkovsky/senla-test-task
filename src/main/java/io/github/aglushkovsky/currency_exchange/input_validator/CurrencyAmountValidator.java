package io.github.aglushkovsky.currency_exchange.input_validator;

import io.github.aglushkovsky.common.input_validator.InputValidator;

public class CurrencyAmountValidator implements InputValidator {
    @Override
    public void validate(String amount) {
        try {
            Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid currency amount: " + amount, e);
        }
    }
}
