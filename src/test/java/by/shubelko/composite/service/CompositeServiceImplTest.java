package by.shubelko.composite.service;

import by.shubelko.composite.entity.TextComponent;
import by.shubelko.composite.entity.TextComponentType;
import by.shubelko.composite.entity.TextComposite;
import by.shubelko.composite.exception.CompositeException;
import by.shubelko.composite.parser.CustomParser;
import by.shubelko.composite.parser.impl.ParagraphParser;
import by.shubelko.composite.service.impl.CompositeServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompositeServiceImplTest {
    private final String textAsString = "    Two. Two.\n    Wonderful.\n    Three. Three. Three.\n";
    private final String textAsStringRemove = "    Two two.\n    Wonderful.\n    Three three three.\n";
    private final CompositeService service = new CompositeServiceImpl();
    private final TextComponent text = new TextComposite(TextComponentType.TEXT);
    private final CustomParser parser = new ParagraphParser();

    @Test
    public void testSortParagraphsByNumberOfSentences() throws CompositeException {
        parser.parse(text, textAsString);
        List<TextComponent> actual = service.sortParagraphsByNumberOfSentences(text);
        List<TextComponent> expected = new ArrayList<>();
        List<TextComponent> textComponents = text.getComponents();
        expected.add(textComponents.get(1));
        expected.add(textComponents.get(0));
        expected.add(textComponents.get(2));

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CompositeException.class)
    public void testSortParagraphsByNumberOfSentencesException() throws CompositeException {
        parser.parse(text, textAsString);
        List<TextComponent> textComponents = text.getComponents();
        service.sortParagraphsByNumberOfSentences(textComponents.get(0));
    }

    @Test
    public void testFindSentencesWithLongestWorld() throws CompositeException {
        parser.parse(text, textAsString);
        List<TextComponent> actual = service.findSentencesWithLongestWorld(text);
        List<TextComponent> expected = new ArrayList<TextComponent>();
        List<TextComponent> paragraphs = text.getComponents();
        TextComponent paragraph = paragraphs.get(1);
        List<TextComponent> sentences = paragraph.getComponents();
        expected.add(sentences.get(0));

        Assert.assertEquals(actual, expected);
    }

   @Test(expectedExceptions = CompositeException.class)
    public void testFindSentencesWithLongestWorldExceptionType() throws CompositeException {
        parser.parse(text, textAsString);
        List<TextComponent> paragraphs = text.getComponents();

        service.findSentencesWithLongestWorld(paragraphs.get(0));
    }

    @Test
    public void testRemoveSentencesWithWordsLessThan() throws CompositeException {
        parser.parse(text, textAsStringRemove);
        TextComponent expected = new TextComposite(TextComponentType.TEXT);
        parser.parse(expected, textAsStringRemove);
        List<TextComponent> paragraphs = expected.getComponents();
        TextComponent actual = new TextComposite(TextComponentType.TEXT);
        parser.parse(actual, textAsStringRemove);
        service.removeSentencesWithWordsLessThan(actual, 1);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindAndCountSameWords() throws CompositeException {
        parser.parse(text, textAsString);
        Map<String, Integer> actual = service.findAndCountSameWords(text);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("two", 2);
        expected.put("three", 3);

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CompositeException.class)
    public void testFindAndCountSameWordsException() throws CompositeException {
        parser.parse(text, textAsString);
        TextComponent paragraph = text.getComponentByIndex(0);

        service.findAndCountSameWords(paragraph);
    }

    @Test
    public void testCountConsonant() throws CompositeException {
        parser.parse(text, textAsString);
        long actual = service.calculateCountConsonant(text);
        long expected = 19L;

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CompositeException.class)
    public void testCountConsonantException() throws CompositeException {
        parser.parse(text, textAsString);
        TextComponent paragraph = text.getComponentByIndex(0);
        service.calculateCountConsonant(paragraph);
    }

    @Test
    public void testCountVowel() throws CompositeException {
        parser.parse(text, textAsString);
        long actual = service.calculateCountVowel(text);
        long expected = 11L;

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CompositeException.class)
    public void testCountVowelException() throws CompositeException {
        parser.parse(text, textAsString);
        TextComponent paragraph = text.getComponentByIndex(0);

        service.calculateCountVowel(paragraph);
    }

}
