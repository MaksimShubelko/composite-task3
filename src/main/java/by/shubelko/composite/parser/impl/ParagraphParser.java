package by.shubelko.composite.parser.impl;

import by.shubelko.composite.entity.TextComponent;
import by.shubelko.composite.entity.TextComponentType;
import by.shubelko.composite.entity.TextComposite;
import by.shubelko.composite.parser.CustomParser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParagraphParser implements CustomParser {
    private static final String PARAGRAPH_SPLITTER_REGEX = "(^|\\n)(\\t|\\s{4})";
    private final CustomParser parser = new SentenceParser();

    @Override
    public void parse(TextComponent textComponent, String source) {
        List<String> paragraphs = Stream.of(source.split(PARAGRAPH_SPLITTER_REGEX))
                .filter(p -> !p.isBlank())
                .collect(Collectors.toList());

        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            textComponent.addComponent(paragraphComponent);
            parser.parse(paragraphComponent, paragraph);
        }
    }
}
