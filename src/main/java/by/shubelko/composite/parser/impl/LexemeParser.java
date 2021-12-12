package by.shubelko.composite.parser.impl;

import by.shubelko.composite.entity.TextComponent;
import by.shubelko.composite.entity.TextComponentType;
import by.shubelko.composite.entity.TextComposite;
import by.shubelko.composite.parser.CustomParser;

public class LexemeParser implements CustomParser {
    private static final String LEXEME_SPLITTER_REGEX = "\\s";
    private final CustomParser parser = new WordPunctuationParser();

    @Override
    public void parse(TextComponent component, String data) {
        String[] lexemes = data.split(LEXEME_SPLITTER_REGEX);

        for (String lexeme : lexemes) {
            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            component.addComponent(lexemeComponent);
            parser.parse(lexemeComponent, lexeme);
        }
    }
}
