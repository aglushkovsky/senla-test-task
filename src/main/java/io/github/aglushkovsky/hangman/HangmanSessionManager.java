package io.github.aglushkovsky.hangman;

import io.github.aglushkovsky.hangman.repository.WordRepository;
import io.github.aglushkovsky.hangman.session.HangmanSession;
import io.github.aglushkovsky.hangman.session.HiddenWord;

public class HangmanSessionManager {
    private final WordRepository wordRepository;

    public HangmanSessionManager(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public void startNewGame() {
        HangmanSession hangmanSession = createHangmanSession();
        hangmanSession.start();
    }

    private HangmanSession createHangmanSession() {
        HiddenWord hiddenWord = getHiddenWord();
        return new HangmanSession(hiddenWord);
    }

    private HiddenWord getHiddenWord() {
        String randomWordFromRepository = wordRepository.get();
        return new HiddenWord(randomWordFromRepository);
    }
}
