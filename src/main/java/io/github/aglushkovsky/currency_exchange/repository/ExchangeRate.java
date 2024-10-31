package io.github.aglushkovsky.currency_exchange.repository;

public class ExchangeRate {
    private final String baseCurrency;
    private final String targetCurrency;
    private final double rate;

    public static ExchangeRate of(String baseCurrency, String targetCurrency, double rate) {
        return new ExchangeRate(baseCurrency, targetCurrency, rate);
    }

    private ExchangeRate(String baseCurrency, String targetCurrency, double rate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", targetCurrency='" + targetCurrency + '\'' +
                ", rate=" + rate +
                '}';
    }
}
