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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmitrykholodok on 11/15/17
 */

public class ParagraphParser extends AbstractTextParser {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String DIVIDER_BY_SENTENCE = "([^\\u002E!?]+[!?]?[\\u002E]{0,3})";

    public ParagraphParser(AbstractTextParser nextParser) {
        super(nextParser);
    }

    @Override
    public List<Component> parse(String str) {
        LOGGER.log(Level.DEBUG, "ParagraphParser : started the work with '" + str + "'");
        List<Component> sentenceComponentResult = new ArrayList<>();
        Matcher matcher = Pattern.compile(DIVIDER_BY_SENTENCE).matcher(str);
        while (matcher.find()) {
            String sentence = matcher.group();
            LOGGER.log(Level.INFO, "ParagraphParser : got a new sentence '" + sentence + "'");
            Component component = new TextContainer(ComponentType.SENTENCE);
            nextParser.parse(sentence)
                    .stream()
                    .forEach(lexeme -> component.addComponent(lexeme));
            sentenceComponentResult.add(component);
        }
        LOGGER.log(Level.DEBUG, "ParagraphParser : finished with '" + str + "'");
        return sentenceComponentResult;
    }

}
