package io.github.aglushkovsky.password_generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 12;
    private static final String UPPER_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final List<String> ALPHABETS = new ArrayList<>(List.of(
            UPPER_LETTERS, LOWER_LETTERS, DIGITS, SPECIAL_CHARACTERS
    ));
    private static final Random randomizer = new Random();

    public String generate(int length) {
        validateLength(length);
        StringBuilder result = new StringBuilder();
        addRequiredCharacters(result);
        addRemainingCharacters(length, result);
        return shuffle(result);
    }

    private void validateLength(int length) {
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            throw new IllegalArgumentException("Password length must be between " + MIN_LENGTH + " and " + MAX_LENGTH);
        }
    }

    private void addRequiredCharacters(StringBuilder result) {
        for (String alphabet : ALPHABETS) {
            result.append(getRandomChar(alphabet));
        }
    }

    private void addRemainingCharacters(int length, StringBuilder result) {
        for (int i = ALPHABETS.size(); i < length; i++) {
            int randomIndex = randomizer.nextInt(ALPHABETS.size());
            String randomAlphabet = ALPHABETS.get(randomIndex);
            result.append(getRandomChar(randomAlphabet));
        }
    }

    private String shuffle(StringBuilder result) {
        for (int i = 0; i < result.length(); i++) {
            int randomIndex = randomizer.nextInt(i + 1);
            char temp = result.charAt(i);
            result.setCharAt(i, result.charAt(randomIndex));
            result.setCharAt(randomIndex, temp);
        }
        return result.toString();
    }

    private char getRandomChar(String alphabet) {
        int randomIndex = randomizer.nextInt(alphabet.length());
        return alphabet.charAt(randomIndex);
    }

    public int getMinLength() {
        return MIN_LENGTH;
    }

    public int getMaxLength() {
        return MAX_LENGTH;
    }
}
