package by.shubelko.composite.parser.impl;

import by.shubelko.composite.entity.Symbol;
import by.shubelko.composite.entity.TextComponent;
import by.shubelko.composite.entity.TextComponentType;
import by.shubelko.composite.entity.TextComposite;
import by.shubelko.composite.parser.CustomParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordPunctuationParser implements CustomParser {
    private static final String WORD_REGEX = "[\\wа-яА-ЯёЁ]+";
    private static final String WORD_OR_PUNCTUATION_REGEX = "([\\wа-яА-ЯёЁ]+)|([\\p{Punct}…])";
    private final CustomParser parser = new LetterParser();

    @Override
    public void parse(TextComponent component, String data) {
        Pattern pattern = Pattern.compile(WORD_OR_PUNCTUATION_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String group = matcher.group();
            Pattern wordPattern = Pattern.compile(WORD_REGEX);
            Matcher wordMatcher = wordPattern.matcher(group);

            if (wordMatcher.matches()) {
                TextComponent wordComponent = new TextComposite(TextComponentType.WORD);
                component.addComponent(wordComponent);
                parser.parse(wordComponent, group);
            } else {
                TextComponent punctuationComponent = new Symbol(TextComponentType.PUNCTUATION, group.charAt(0));
                component.addComponent(punctuationComponent);
            }
        }
    }
}
