package io.github.aglushkovsky.hangman;

import io.github.aglushkovsky.common.Launcher;
import io.github.aglushkovsky.hangman.repository.WordRepository;
import io.github.aglushkovsky.hangman.repository.RandomWordRepository;

public class HangmanMain {
    public static void main(String[] args) {
        WordRepository wordRepository = new RandomWordRepository();
        HangmanSessionManager sessionManager = new HangmanSessionManager(wordRepository);
        Launcher launcher = createLauncher(sessionManager);
        launcher.start();
    }

    private static Launcher createLauncher(HangmanSessionManager sessionManager) {
        return Launcher.builder()
                .addAction("Начать новую игру", sessionManager::startNewGame)
                .build();
    }
}
