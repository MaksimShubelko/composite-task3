package by.shubelko.composite.parser.impl;

import by.shubelko.composite.entity.TextComponent;
import by.shubelko.composite.entity.TextComponentType;
import by.shubelko.composite.entity.TextComposite;
import by.shubelko.composite.parser.CustomParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements CustomParser {
    private static final String SENTENCE_REGEX = "(\\p{Upper}|[А-ЯЁ]).+?(\\.|\\!|\\?|…)(\\s|$)";
    private final CustomParser parser = new LexemeParser();

    @Override
    public void parse(TextComponent component, String data) {

        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String sentence = matcher.group();
            TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            component.addComponent(sentenceComponent);
            parser.parse(sentenceComponent, sentence);
        }

    }

}
