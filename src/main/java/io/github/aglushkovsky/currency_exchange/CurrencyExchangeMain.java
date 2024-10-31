package io.github.aglushkovsky.currency_exchange;

import io.github.aglushkovsky.common.Launcher;

public class CurrencyExchangeMain {
    public static void main(String[] args) {
        CurrencyExchangeMenu currencyExchangeMenu = new CurrencyExchangeMenu();
        Launcher launcher = createMenu(currencyExchangeMenu);
        launcher.start();
    }

    private static Launcher createMenu(CurrencyExchangeMenu currencyExchangeMenu) {
        return Launcher.builder()
                .addAction("Выполнить конвертацию валюты", currencyExchangeMenu::start)
                .build();
    }
}
