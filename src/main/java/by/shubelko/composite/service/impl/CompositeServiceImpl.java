package by.shubelko.composite.service.impl;

import by.shubelko.composite.comparator.impl.SentenceAmountComparator;
import by.shubelko.composite.entity.TextComponent;
import by.shubelko.composite.entity.TextComponentType;
import by.shubelko.composite.exception.CompositeException;
import by.shubelko.composite.service.CompositeService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CompositeServiceImpl implements CompositeService {
    private static final Logger logger = LogManager.getLogger();
    private static final String VOWEL_REGEX = "(?iu)[aeiouyаеёионыэюя]";
    private static final String CONSONANT_REGEX = "(?iu)[a-zа-я&&[^aeiouyаеёионыэюя]]";

    @Override
    public List<TextComponent> sortParagraphsByNumberOfSentences(TextComponent text) throws CompositeException {
        if (text.getType() != TextComponentType.TEXT) {
            logger.log(Level.ERROR, "Unable type of TextComponent.");
            throw new CompositeException("Unable type of TextComponent.");
        }

        List<TextComponent> paragraphs;
        paragraphs = text.getComponents();
        paragraphs.sort(new SentenceAmountComparator());
        return paragraphs;
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWorld(TextComponent text) throws CompositeException {
        if (text.getType() != TextComponentType.TEXT) {
            logger.log(Level.ERROR, "Unable type of TextComponent.");
            throw new CompositeException("Unable type of TextComponent.");
        }

        List<TextComponent> allSentences = text.getComponents().stream()
                .flatMap(p -> p.getComponents().stream())
                .collect(Collectors.toList());

        OptionalInt maxLengthOptional = allSentences.stream()
                .flatMap(s -> s.getComponents().stream())
                .flatMap(l -> l.getComponents().stream())
                .filter(w -> w.getType() != TextComponentType.PUNCTUATION)
                .mapToInt(w -> w.getComponents().size())
                .max();

        maxLengthOptional.orElseThrow(() -> new CompositeException("Unable length of word."));
        int maxLength = maxLengthOptional.getAsInt();

        List<TextComponent> suitableSentences = allSentences.stream()
                .filter(s -> s.getComponents().stream()
                        .anyMatch(l -> l.getComponents().stream()
                                .filter(w -> w.getType() != TextComponentType.PUNCTUATION)
                                .anyMatch(w -> w.getComponents().size() == maxLength)))
                .peek(e -> logger.log(Level.INFO, "sentence to collect >> " + e))
                .collect(Collectors.toList());

        return suitableSentences;
    }

    @Override
    public void removeSentencesWithWordsLessThan(TextComponent text, int min) throws CompositeException {
        if (text.getType() != TextComponentType.TEXT) {
            logger.log(Level.ERROR, "Unable type of TextComponent.");
            throw new CompositeException("Unable type of TextComponent.");
        }

        text.getComponents().forEach(
                parag -> parag.getComponents().stream()
                        .filter(s -> s.getComponents().stream()
                                .filter(lex -> lex.getType() != TextComponentType.PUNCTUATION)
                                .count() < min)
                        .forEach(parag::removeText));
    }

    @Override
    public Map<String, Integer> findAndCountSameWords(TextComponent text) throws CompositeException {
        if (text.getType() != TextComponentType.TEXT) {
            logger.log(Level.ERROR, "Unable type of TextComponent.");
            throw new CompositeException("Unable type of TextComponent.");
        }

        Map<String, Integer> sameWords = text.getComponents().stream()
                .flatMap(p -> p.getComponents().stream())
                .flatMap(s -> s.getComponents().stream())
                .flatMap(lx -> lx.getComponents().stream())
                .filter(w -> w.getType() != TextComponentType.PUNCTUATION)
                .map(w -> w.toString().toLowerCase())
                .collect(Collectors.toMap(str -> str, i -> 1, Integer::sum));

        sameWords.values().removeIf(i -> i == 1);

        return sameWords;
    }

    @Override
    public long calculateCountConsonant(TextComponent text) throws CompositeException {
        if (text.getType() != TextComponentType.TEXT) {
            logger.log(Level.ERROR, "Unable type of TextComponent.");
            throw new CompositeException("Unable type of TextComponent.");
        }

        Pattern pattern = Pattern.compile(CONSONANT_REGEX);

        long counter = text.getComponents().stream()
                .flatMap(p -> p.getComponents().stream())
                .flatMap(s -> s.getComponents().stream())
                .flatMap(lx -> lx.getComponents().stream())
                .filter(w -> w.getType() != TextComponentType.PUNCTUATION)
                .flatMap(w -> w.getComponents().stream())
                .map(TextComponent::toString)
                .filter(l -> pattern.matcher(l).matches())
                .count();

        return counter;
    }

    @Override
    public long calculateCountVowel(TextComponent text) throws CompositeException {
        if (text.getType() != TextComponentType.TEXT) {
            logger.log(Level.ERROR, "Unable type of TextComponent.");
            throw new CompositeException("Unable type of TextComponent.");
        }

        Pattern pattern = Pattern.compile(VOWEL_REGEX);

        long counter = text.getComponents().stream()
                .flatMap(p -> p.getComponents().stream())
                .flatMap(s -> s.getComponents().stream())
                .flatMap(lx -> lx.getComponents().stream())
                .filter(w -> w.getType() != TextComponentType.PUNCTUATION)
                .flatMap(w -> w.getComponents().stream())
                .map(TextComponent::toString)
                .filter(l -> pattern.matcher(l).matches())
                .count();

        return counter;
    }
}
