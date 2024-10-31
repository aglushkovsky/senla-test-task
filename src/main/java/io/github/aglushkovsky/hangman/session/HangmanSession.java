package io.github.aglushkovsky.hangman.session;

import io.github.aglushkovsky.hangman.HangmanPicture;
import io.github.aglushkovsky.common.dialog.Dialog;
import io.github.aglushkovsky.common.dialog.ConsoleDialog;
import io.github.aglushkovsky.hangman.RuLetterValidator;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public class HangmanSession {
    private enum Result {
        WIN, LOSE
    }

    private static final int MAX_ATTEMPTS = 6;
    private static final String SESSION_START_MESSAGE = "Игра началась!";
    private static final String WIN_MESSAGE = "Вы выиграли!";
    private static final String LOSE_MESSAGE = "Вы проиграли!";
    private static final String LETTER_ALREADY_ENTERED_MESSAGE = "Вы уже вводили эту букву, её нет в загаданном слове!";
    private static final String NO_SUCH_LETTER_MESSAGE = "Такой буквы нет в загаданном слове";

    private final HiddenWord hiddenWord;
    private final HangmanPicture hangmanPicture;
    private final Set<String> wrongLetters = new HashSet<>();
    private final Dialog dialog;
    private boolean isSessionOn;
    private int leftAttempts = MAX_ATTEMPTS;

    public HangmanSession(HiddenWord hiddenWord) {
        this.hiddenWord = hiddenWord;
        this.hangmanPicture = new HangmanPicture();
        this.dialog = new ConsoleDialog(new RuLetterValidator());
    }

    public void start() {
        isSessionOn = true;
        displayStartMessage();
        mainLoop();
    }

    private void displayStartMessage() {
        System.out.println(SESSION_START_MESSAGE);
        System.out.println(hiddenWord.getMask());
        displayHangmanPicture();
    }

    private void mainLoop() {
        while (isSessionOn) {
            String letter = dialog.getInput();
            Optional<String> mask = getWordMaskWithRevealedLetter(letter);
            if (mask.isEmpty()) {
                continue;
            }
            System.out.println(mask.get());
            displayLeftAttempts();
            if (hiddenWord.isGuessed()) {
                endSession(Result.WIN);
            }
        }
    }

    private Optional<String> getWordMaskWithRevealedLetter(String letter) {
        try {
            return Optional.of(hiddenWord.revealLetter(letter));
        } catch (NoSuchElementException e) {
            System.out.println(NO_SUCH_LETTER_MESSAGE);
            handleWrongLetter(letter);
            return Optional.empty();
        }
    }

    private void endSession(Result result) {
        if (isWin(result)) {
            displayWinMessage();
        } else if (isLose(result)) {
            displayLoseMessage();
        }
        isSessionOn = false;
    }

    private boolean isWin(Result result) {
        return result == Result.WIN;
    }

    private void displayWinMessage() {
        System.out.println(WIN_MESSAGE);
    }

    private boolean isLose(Result result) {
        return result == Result.LOSE;
    }

    private void displayLoseMessage() {
        System.out.println(LOSE_MESSAGE);
        displayWrongLetters();
        String revealedWord = hiddenWord.reveal();
        System.out.println("Загаданное слово: " + revealedWord);
    }

    private void handleWrongLetter(String letter) {
        letter = letter.toUpperCase();
        if (!wrongLetters.contains(letter)) {
            wrongLetters.add(letter);
        } else {
            System.out.println(LETTER_ALREADY_ENTERED_MESSAGE);
            displayErrorMessage();
            return;
        }

        updateLeftAttempts();
    }

    private void updateLeftAttempts() {
        --leftAttempts;
        if (leftAttempts == 0) {
            endSession(Result.LOSE);
        } else {
            displayErrorMessage();
        }
        displayHangmanPicture();
    }

    private void displayErrorMessage() {
        displayLeftAttempts();
        displayWrongLetters();
        displayHiddenWordMask();
    }

    private void displayHangmanPicture() {
        String picture = hangmanPicture.get(MAX_ATTEMPTS - leftAttempts);
        System.out.println(picture);
    }

    private void displayWrongLetters() {
        System.out.println("Ошибки: " + getStringOfWrongLetters());
    }

    private void displayHiddenWordMask() {
        System.out.println(hiddenWord.getMask());
    }

    private String getStringOfWrongLetters() {
        StringBuilder letters = new StringBuilder();
        for (String letter : wrongLetters) {
            letters.append(letter).append(" ");
        }
        return letters.toString();
    }

    private void displayLeftAttempts() {
        System.out.println("Осталось попыток: " + leftAttempts);
    }
}
