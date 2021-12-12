package by.shubelko.composite.reader;

import by.shubelko.composite.reader.impl.TextReaderImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextReaderTest {
    private TextReader reader;

    @BeforeMethod
    public void initialize () {
        reader = new TextReaderImpl();
    }

    @Test
    public void testReadPositive() {
        String pathToFile = "data/text.txt";
        String actual = reader.read(pathToFile);
        String expected = "    It has survived - not only (five) text centuries, but also the leap into electronic\n" +
                "typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига)\n" +
                "with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and\n" +
                "more recently with desktop survived publishing software like Aldus PageMaker Faclon9 including\n" +
                "versions of Lorem Ipsum!\n" +
                "    I hava text file that contains some data. All paragraphs start with four spaces. My aim is to split this text into paragraphs в гидромагнитосфере планеты Земля.\n" +
                "    It is a long a!=b established fact that a reader will be distracted by the readable\n" +
                "content of a page when looking at its layout. The point of using Ipsum is that it has a\n" +
                "more-or-less normal distribution ob.toString(a?b:c) text, as opposed to using (Content here),\n" +
                "content here's, making it look to PARAGRAPHS like readable English?\n" +
                "    It is a established fact that a \"TO\" reader will be of a page when looking at its layout…\n" +
                "    Bye бандерлоги.\n";

        assertEquals(actual, expected);
    }

    @Test
    public void testReadEmpty() {
        String pathToFile = "data/emptyTextFile.txt";
        String actual = reader.read(pathToFile);
        String expected = "";

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testReadException() {
        String pathToFile = "invalid";
        String text = reader.read(pathToFile);
        assertEquals(text, "");
    }

}
