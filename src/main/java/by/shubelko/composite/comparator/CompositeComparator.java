package by.shubelko.composite.comparator;

import by.shubelko.composite.entity.TextComponent;

import java.util.Comparator;

public interface CompositeComparator extends Comparator<TextComponent> {
    int compare(TextComponent o1, TextComponent o2);
}
