package io.github.aglushkovsky.currency_exchange.converter;

import io.github.aglushkovsky.currency_exchange.repository.ExchangeRate;
import io.github.aglushkovsky.currency_exchange.repository.ExchangeRatesRepository;

import java.util.Optional;

public class ExchangeConverterImpl implements ExchangeConverter {
    private static final ExchangeRatesRepository exchangeRatesRepository = ExchangeRatesRepository.getInstance();
    private static final String USD_CURRENCY_CODE = "USD";

    @Override
    public Optional<Double> convert(String from, String to, double amount) {
        Optional<ExchangeRate> result = findDirectRate(from, to);
        if (result.isPresent()) {
            return result.map(exchangeRate -> calculateAmountRate(amount, exchangeRate.getRate()));
        }

        result = findReverseRate(from, to);
        if (result.isPresent()) {
            return result.map(exchangeRate -> calculateAmountRate(amount, exchangeRate.getRate()));
        }

        result = findCrossRate(from, to);
        return result.map(exchangeRate -> calculateAmountRate(amount, exchangeRate.getRate()));
    }

    private static double calculateAmountRate(double amount, double rate) {
        return rate * amount;
    }

    private Optional<ExchangeRate> findCrossRate(String from, String to) {
        Optional<ExchangeRate> usdFromRate = exchangeRatesRepository.getExchangeRate(USD_CURRENCY_CODE, from);
        Optional<ExchangeRate> usdToRate = exchangeRatesRepository.getExchangeRate(USD_CURRENCY_CODE, to);

        if (usdFromRate.isEmpty() || usdToRate.isEmpty()) {
            return Optional.empty();
        }

        double baseExchangeRate = usdFromRate.get().getRate();
        double targetExchangeRate = usdToRate.get().getRate();
        double result = calculateCrossRate(baseExchangeRate, targetExchangeRate);

        return Optional.of(ExchangeRate.of(
                usdFromRate.get().getBaseCurrency(),
                usdToRate.get().getTargetCurrency(),
                result
        ));
    }

    private double calculateCrossRate(double baseExchangeRate, double targetExchangeRate) {
        return targetExchangeRate / baseExchangeRate;
    }

    private Optional<ExchangeRate> findReverseRate(String from, String to) {
        Optional<ExchangeRate> reverseExchangeRateOptional = exchangeRatesRepository.getExchangeRate(to, from);
        if (reverseExchangeRateOptional.isEmpty()) {
            return Optional.empty();
        }

        ExchangeRate reverseExchangeRate = reverseExchangeRateOptional.get();
        double result = calculateReverseRate(reverseExchangeRate.getRate());

        return Optional.of(ExchangeRate.of(
                reverseExchangeRate.getBaseCurrency(),
                reverseExchangeRate.getTargetCurrency(),
                result
        ));
    }

    private double calculateReverseRate(double rate) {
        return 1 / rate;
    }

    private Optional<ExchangeRate> findDirectRate(String from, String to) {
        return exchangeRatesRepository.getExchangeRate(from, to);
    }
}
