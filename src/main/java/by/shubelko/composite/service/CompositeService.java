package by.shubelko.composite.service;

import by.shubelko.composite.entity.TextComponent;
import by.shubelko.composite.exception.CompositeException;

import java.util.List;
import java.util.Map;

public interface CompositeService {
    List<TextComponent> sortParagraphsByNumberOfSentences(TextComponent text) throws CompositeException;

    List<TextComponent> findSentencesWithLongestWorld(TextComponent text) throws CompositeException;

    void removeSentencesWithWordsLessThan(TextComponent text, int min) throws CompositeException;

    Map<String, Integer> findAndCountSameWords(TextComponent text) throws CompositeException;

    long calculateCountConsonant(TextComponent text) throws CompositeException;

    long calculateCountVowel(TextComponent text) throws CompositeException;
}
