package by.shubelko.composite.comparator.impl;

import by.shubelko.composite.comparator.CompositeComparator;
import by.shubelko.composite.entity.TextComponent;

import java.util.List;

public class SentenceAmountComparator implements CompositeComparator {
    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        List<TextComponent> firstComponents = o1.getComponents();
        List<TextComponent> secondComponents = o2.getComponents();
        int resultComparing = 0;
        if (firstComponents.size() > secondComponents.size()) {
            resultComparing = 1;
        } else {
            if (firstComponents.size() < secondComponents.size()) {
                resultComparing = -1;
            }
        }
        return resultComparing;
    }
}
