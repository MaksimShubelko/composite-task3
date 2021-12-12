package by.shubelko.composite.reader.impl;

import by.shubelko.composite.exception.CompositeException;
import by.shubelko.composite.reader.TextReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {
    static Logger logger = LogManager.getLogger();

    @Override
    public String read(String pathToFile) {
        String text = "";
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            classLoader.getResourceAsStream(pathToFile);
            URL fileUrl = classLoader.getResource(pathToFile);
            if (fileUrl == null) {
                logger.log(Level.ERROR, "File " + fileUrl + " does not exsist or is not available.");
                throw new CompositeException("File " + fileUrl + " does not exsist or is not available.");
            }
            logger.log(Level.INFO,"Start reading");

            Path path = Paths.get(fileUrl.toURI());
            text = Files.readString(path);
        } catch (IOException | CompositeException | URISyntaxException e) {
            logger.log(Level.ERROR, "IO Exception during working with file " + pathToFile);
        }
        logger.log(Level.INFO,"Finish reading");

        return text;
    }
}
