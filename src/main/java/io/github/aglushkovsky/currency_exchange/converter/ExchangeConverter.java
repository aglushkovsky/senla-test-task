package io.github.aglushkovsky.currency_exchange.converter;

import java.util.Optional;

public interface ExchangeConverter {
    Optional<Double> convert(String from, String to, double amount);
}
