package by.shubelko.composite.entity;

import java.util.List;

public class Symbol implements TextComponent {
    private final TextComponentType textComponentType;
    private final char symbol;

    public Symbol(TextComponentType textComponentType, char symbol) {
        this.textComponentType = textComponentType;
        this.symbol = symbol;
    }

    @Override
    public boolean addComponent(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeText(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextComponentType getType() {
        return textComponentType;
    }

    @Override
    public TextComponent getComponentByIndex(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return this.symbol == symbol.symbol
                && textComponentType == symbol.textComponentType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + symbol;
        result = prime * result + (textComponentType == null ? 0 : textComponentType.hashCode());
        return result;
    }
}