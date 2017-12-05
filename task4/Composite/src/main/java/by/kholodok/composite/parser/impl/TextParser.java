package by.kholodok.composite.parser.impl;

import by.kholodok.composite.entity.Component;
import by.kholodok.composite.entity.ComponentType;
import by.kholodok.composite.entity.TextContainer;
import by.kholodok.composite.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrykholodok on 11/15/17
 */

public class TextParser extends AbstractTextParser {

    private static final Logger LOGGER = LogManager.getLogger(TextParser.class);

    private static final String DIVIDER_BY_TABULATION = "\t";

    public TextParser(AbstractTextParser nextParser) {
        super(nextParser);
    }

    @Override
    public List<Component> parse(String str) {
        LOGGER.log(Level.DEBUG, "TextParser : started the work with '" + str + "'");
        List<Component> paragraphComponentList = new ArrayList<>();
        for(String paragraph : str.split(DIVIDER_BY_TABULATION)) {
            if (!paragraph.isEmpty()) {
                LOGGER.log(Level.INFO, "TextParser : got a new paragraph '" + paragraph + "'");
                Component component = new TextContainer(ComponentType.PARAGRAPH);
                nextParser.parse(paragraph)
                        .stream()
                        .forEach(sentence -> component.addComponent(sentence));
                paragraphComponentList.add(component);
            }
        }
        LOGGER.log(Level.DEBUG, "TextParser : finished with '" + str + "'");
        return paragraphComponentList;
    }
}
