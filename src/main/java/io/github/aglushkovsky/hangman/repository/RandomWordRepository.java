package io.github.aglushkovsky.hangman.repository;

import java.util.ArrayList;
import java.util.List;

public class RandomWordRepository implements WordRepository {
    private static final List<String> WORDS = new ArrayList<>(List.of(
            "путешествие",
            "музыка",
            "искусство",
            "архитектура",
            "вулкан",
            "океан",
            "библиотека",
            "романтика",
            "растение",
            "скульптура"
    ));

    @Override
    public String get() {
        return WORDS.get(getRandomIndex());
    }

    private int getRandomIndex() {
        return (int) (Math.random() * WORDS.size());
    }
}
