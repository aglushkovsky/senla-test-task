package io.github.aglushkovsky.hangman.session;

import java.util.NoSuchElementException;

public class HiddenWord {
    private static final String PLACEHOLDER = "*";

    private final String text;
    private final StringBuilder mask;

    public HiddenWord(String text) {
        this.text = text.toUpperCase();
        this.mask = createWordMask();
    }

    private StringBuilder createWordMask() {
        String mask = PLACEHOLDER.repeat(text.length());
        return new StringBuilder(mask);
    }

    public String revealLetter(String letter) {
        letter = letter.toUpperCase();
        if (!text.contains(letter)) {
            throw new NoSuchElementException("The letter %s is not in the hidden word".formatted(letter));
        }

        if (isGuessed() || isLetterAlreadyRevealed(letter)) {
            return mask.toString();
        }

        for (int i = 0; i < text.length(); i++) {
            char ch = letter.charAt(0);
            if (text.charAt(i) == ch) {
                mask.setCharAt(i, ch);
            }
        }

        return mask.toString();
    }

    private boolean isLetterAlreadyRevealed(String letter) {
        return mask.toString().contains(letter);
    }

    public boolean isGuessed() {
        return !mask.toString().contains(PLACEHOLDER);
    }

    public String reveal() {
        return text;
    }

    public String getMask() {
        return mask.toString();
    }
}
