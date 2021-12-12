package by.shubelko.composite.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private final TextComponentType textComponentType;
    private final List<TextComponent> textComponents = new ArrayList<>();

    public TextComposite(TextComponentType textComponentType) {
        this.textComponentType = textComponentType;
    }

    @Override
    public boolean addComponent(TextComponent textComponent) {
        return textComponents.add(textComponent);
    }

    @Override
    public boolean removeText(TextComponent textComponent) {
        return textComponents.remove(textComponent);
    }

    @Override
    public List<TextComponent> getComponents() {
        return new ArrayList<>(textComponents);
    }
    @Override
    public TextComponent getComponentByIndex(int index) {
        return textComponents.get(index);
    }

    @Override
    public TextComponentType getType() {
        return textComponentType;
    }

    @Override
    public String toString() {
        StringBuilder demonstrativeForm = new StringBuilder();
        demonstrativeForm.append(textComponentType.getPrefix());
        textComponents.forEach(c -> demonstrativeForm.append(c.toString()));
        demonstrativeForm.append(textComponentType.getPostfix());
        return demonstrativeForm.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        return textComponentType == that.textComponentType
                && textComponents.equals(that.textComponents);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (textComponents == null ? 0 : textComponents.hashCode());
        result = prime * result + (textComponentType == null ? 0 : textComponentType.hashCode());
        return result;
    }
}