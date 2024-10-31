package io.github.aglushkovsky.password_generator;

import io.github.aglushkovsky.common.Launcher;

public class PasswordGeneratorMain {
    public static void main(String[] args) {
        PasswordGeneratorMenu passwordGeneratorMenu = new PasswordGeneratorMenu();
        Launcher launcher = createMenu(passwordGeneratorMenu);
        launcher.start();
    }

    private static Launcher createMenu(PasswordGeneratorMenu passwordGeneratorMenu) {
        return Launcher.builder()
                .addAction("Сгенерировать пароль", passwordGeneratorMenu::start)
                .build();
    }
}
