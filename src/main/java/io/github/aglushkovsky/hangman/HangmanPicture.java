package io.github.aglushkovsky.hangman;

import java.util.ArrayList;
import java.util.List;

public class HangmanPicture {
    private final List<String> pictures = new ArrayList<>();

    public HangmanPicture() {
        pictures.add(
                " -----\n" +
                " |   |\n" +
                " |\n" +
                " |\n" +
                " |\n" +
                " |\n" +
                "_|_\n"
        );
        pictures.add(
                " -----\n" +
                " |   |\n" +
                " |   O\n" +
                " |\n" +
                " |\n" +
                " |\n" +
                "_|_\n"
        );
        pictures.add(
                " -----\n" +
                " |   |\n" +
                " |   O\n" +
                " |   |\n" +
                " |\n" +
                " |\n" +
                "_|_\n"
        );
        pictures.add(
                " -----\n" +
                " |   |\n" +
                " |   O\n" +
                " |  /|\n" +
                " |\n" +
                " |\n" +
                "_|_\n"
        );
        pictures.add(
                " -----\n" +
                " |   |\n" +
                " |   O\n" +
                " |  /|\\\n" +
                " |\n" +
                " |\n" +
                "_|_\n"
        );
        pictures.add(
                " -----\n" +
                " |   |\n" +
                " |   O\n" +
                " |  /|\\\n" +
                " |  /\n" +
                " |\n" +
                "_|_\n"
        );
        pictures.add(
                " -----\n" +
                " |   |\n" +
                " |   O\n" +
                " |  /|\\\n" +
                " |  / \\\n" +
                " |\n" +
                "_|_\n"
        );
    }

    public String get(int attemptNumber) {
        return pictures.get(attemptNumber);
    }
}
