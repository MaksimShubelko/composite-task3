package by.shubelko.composite.entity;

import java.util.List;

public interface TextComponent {

    boolean addComponent(TextComponent textComponent);

    boolean removeText(TextComponent textComponent);

    List<TextComponent> getComponents();

    TextComponentType getType();
    TextComponent getComponentByIndex(int index);

    @Override
    String toString();
}
