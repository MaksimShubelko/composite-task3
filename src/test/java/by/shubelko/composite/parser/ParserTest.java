package by.shubelko.composite.parser;

import by.shubelko.composite.entity.TextComponent;
import by.shubelko.composite.entity.TextComponentType;
import by.shubelko.composite.entity.TextComposite;
import by.shubelko.composite.parser.impl.ParagraphParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParse() {

        String text = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!  \n"
                .concat("\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?  \n")
                .concat("\tIt is a established fact that a reader will be of a page when looking at its layout…  \n")
                .concat("\tBye бандерлоги.  \n");

        TextComponent component = new TextComposite(TextComponentType.TEXT);
        CustomParser parser = new ParagraphParser();
        parser.parse(component, text);

        String actual = component.toString();
        String expected = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged.  It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!  \n"
        .concat("\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout.  The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?  \n")
                .concat("\tIt is a established fact that a reader will be of a page when looking at its layout…  \n")
                        .concat("\tBye бандерлоги.  \n");

        assertEquals(actual, expected);
    }
}
