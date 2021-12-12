package by.shubelko.composite.entity;

public enum TextComponentType {
    TEXT("", ""),
    PARAGRAPH("\t", "\n"),
    SENTENCE("", " "),
    LEXEME("", " "),
    WORD("", ""),
    LETTER("", ""),
    PUNCTUATION("", "");

    private final String prefix;
    private final String postfix;

    TextComponentType(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPostfix() {
        return postfix;
    }
    public String getPrefix() {
        return prefix;
    }

}