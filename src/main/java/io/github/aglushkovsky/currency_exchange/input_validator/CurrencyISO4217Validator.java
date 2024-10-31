package io.github.aglushkovsky.currency_exchange.input_validator;

import io.github.aglushkovsky.common.input_validator.InputValidator;

public class CurrencyISO4217Validator implements InputValidator {
    private static final int NUMBER_OF_CHARACTERS_IN_CURRENCY_CODE = 3;

    @Override
    public void validate(String currency) {
        if (currency.length() != NUMBER_OF_CHARACTERS_IN_CURRENCY_CODE) {
            throw new IllegalArgumentException("Currency code must contains three characters: " + currency);
        }

        if (!isCurrencyCodeInUpperCase(currency)) {
            throw new IllegalArgumentException("Currency code must be in uppercase: " + currency);
        }

        if (!currency.chars().allMatch(Character::isLetter)) {
            throw new IllegalArgumentException("All characters of currency code must be letters: " + currency);
        }
    }

    private boolean isCurrencyCodeInUpperCase(String currency) {
        return currency.toUpperCase().equals(currency);
    }
}
