package io.github.aglushkovsky.currency_exchange.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ExchangeRatesRepository {
    private static final ExchangeRatesRepository INSTANCE = new ExchangeRatesRepository();
    private static final double UNSPECIFIED_RATE = -1.0;

    List<ExchangeRate> exchangeRates = new ArrayList<>(List.of(
            ExchangeRate.of("USD", "RUB", 97.92),
            ExchangeRate.of("USD", "RSD", 108.21),
            ExchangeRate.of("RUB", "BYN", 0.033),
            ExchangeRate.of("BYN", "USD", 0.31),
            ExchangeRate.of("USD", "JPY", 153.36)
    ));

    public List<ExchangeRate> getAll() {
        return Collections.unmodifiableList(exchangeRates);
    }

    public Optional<ExchangeRate> getExchangeRate(String baseCurrency, String targetCurrency) {
        ExchangeRate targetExchangeRate = ExchangeRate.of(baseCurrency, targetCurrency, UNSPECIFIED_RATE);
        for (ExchangeRate exchangeRate : exchangeRates) {
            if (isTarget(exchangeRate, targetExchangeRate)) {
                return Optional.of(exchangeRate);
            }
        }
        return Optional.empty();
    }

    private boolean isTarget(ExchangeRate exchangeRate, ExchangeRate targetExchangeRate) {
        return isBaseCurrenciesEquals(exchangeRate.getBaseCurrency(), targetExchangeRate.getBaseCurrency()) &&
                isTargetCurrenciesEquals(exchangeRate.getTargetCurrency(), targetExchangeRate.getTargetCurrency());
    }

    private boolean isBaseCurrenciesEquals(String firstBaseCurrency, String secondBaseCurrency) {
        return firstBaseCurrency.equals(secondBaseCurrency);
    }

    private boolean isTargetCurrenciesEquals(String firstTargetCurrency, String secondTargetCurrency) {
        return firstTargetCurrency.equals(secondTargetCurrency);
    }

    public static ExchangeRatesRepository getInstance() {
        return INSTANCE;
    }

    private ExchangeRatesRepository() {
    }
}
