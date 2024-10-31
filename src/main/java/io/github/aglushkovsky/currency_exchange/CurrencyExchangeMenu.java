package io.github.aglushkovsky.currency_exchange;

import io.github.aglushkovsky.common.dialog.ConsoleDialog;
import io.github.aglushkovsky.common.dialog.Dialog;
import io.github.aglushkovsky.currency_exchange.converter.ExchangeConverter;
import io.github.aglushkovsky.currency_exchange.converter.ExchangeConverterImpl;
import io.github.aglushkovsky.currency_exchange.input_validator.CurrencyAmountValidator;
import io.github.aglushkovsky.currency_exchange.input_validator.CurrencyISO4217Validator;
import io.github.aglushkovsky.currency_exchange.repository.ExchangeRatesRepository;

import java.util.Optional;

public class CurrencyExchangeMenu {
    private final Dialog amountDialog;
    private final Dialog currencyDialog;
    private final ExchangeConverter converter;
    private final ExchangeRatesRepository repository;

    public CurrencyExchangeMenu() {
        this.currencyDialog = new ConsoleDialog(new CurrencyISO4217Validator());
        this.amountDialog = new ConsoleDialog(new CurrencyAmountValidator());
        this.converter = new ExchangeConverterImpl();
        this.repository = ExchangeRatesRepository.getInstance();
    }

    public void start() {
        convertCurrency();
    }

    private void convertCurrency() {
        String baseCurrency = getBaseCurrency();
        String amount = getAmount();
        for (var exchangeRate : repository.getAll()) {
            if (exchangeRate.getTargetCurrency().equals(baseCurrency)) {
                continue;
            }

            Optional<Double> converted = converter.convert(baseCurrency, exchangeRate.getTargetCurrency(), Double.parseDouble(amount));
            if (converted.isEmpty()) {
                System.out.printf("%s - %s - курс отсутствует%n", baseCurrency, exchangeRate.getTargetCurrency());
            } else {
                System.out.printf("%s - %s - %.2f%n", baseCurrency, exchangeRate.getTargetCurrency(), converted.get());
            }
        }
    }

    private String getBaseCurrency() {
        System.out.println("Введите валюту (в формате ISO 4217)");
        return currencyDialog.getInput();
    }

    private String getAmount() {
        System.out.println("Введите количество");
        return amountDialog.getInput();
    }
}
