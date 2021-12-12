package by.shubelko.composite.parser.impl;

import by.shubelko.composite.entity.Symbol;
import by.shubelko.composite.entity.TextComponent;
import by.shubelko.composite.entity.TextComponentType;
import by.shubelko.composite.parser.CustomParser;

public class LetterParser implements CustomParser {
    @Override
    public void parse(TextComponent component, String data) {

        char[] letters = data.toCharArray();

        for (char letter : letters) {
            component.addComponent(new Symbol(TextComponentType.LETTER, letter));
        }
    }
}
