package by.shubelko.composite.parser;

import by.shubelko.composite.entity.TextComponent;

public interface CustomParser {
    void parse(TextComponent textComponent, String data);
}
