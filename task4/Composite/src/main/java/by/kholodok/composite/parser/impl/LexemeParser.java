package by.kholodok.composite.parser.impl;


import by.kholodok.composite.entity.*;
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

public class LexemeParser extends AbstractTextParser {

    private static final Logger LOGGER = LogManager.getLogger(TextParser.class);

    private static final String WORD_REGEX = "([a-zA-Z\\(\\)\\'\\-]+)";

    private static final String VAR_WITH_OPERATION_REGEX =
            "([\\-]{2}[a-zA-Z]+|[\\+]{2}[a-zA-Z]+|[a-zA-Z]+[\\-]{2}|[a-zA-Z]+[\\-]{2})";

    private static final String EXPRESSION_REGEX = "([ij0-9\\(\\)\\+\\-\\*\\/])+";

    private static final String INTERMEDIATE_PUNCT_MARK_REGEX = "([\\,-])";
    private static final String TERMINAL_PUNCT_MARK_REGEX = "[.!?]{1}[.]?[.]?";

    private static final String DIVIDER_BY_INTERM_PUNCT_MARK_REGEX = "([a-zA-Z\\)\\(']*)([\\,-])";
    private static final String DIVIDER_BY_TERMINAL_PUNCT_MARK_REGEX = "([a-zA-Z]*)([.!?]{1}[.]?[.]?)";


    public LexemeParser(AbstractTextParser nextParser) {
        super(nextParser);
    }

    @Override
    public List<Component> parse(String str) {
        LOGGER.log(Level.DEBUG, "LexemeParser : started the work with '" + str + "'");
        List<Component> leafs = defineLeafsInStr(str.trim());
        LOGGER.log(Level.DEBUG, "LexemeParser : finished with '" + str + "'");
        return leafs;
    }

    private List<Component> defineLeafsInStr(String str) {
        List<Component> leafComponentResultList = new ArrayList<>();
        if (str.matches(WORD_REGEX) && !str.matches(VAR_WITH_OPERATION_REGEX)) {
            leafComponentResultList.add(createLeafComponent(ComponentType.WORD, str));
        } else if (str.matches(INTERMEDIATE_PUNCT_MARK_REGEX)) {
            leafComponentResultList.add(createLeafComponent(ComponentType.INTERMEDIATE_PUNCTUATION_MARK, str));
        } else if (str.matches(EXPRESSION_REGEX)) {
            leafComponentResultList.add(createLeafComponent(ComponentType.EXPRESSION, str));
        } else if (str.matches(TERMINAL_PUNCT_MARK_REGEX)) {
            leafComponentResultList.add(createLeafComponent(ComponentType.TERMINAL_PUNCTUATION_MARK, str));
        } else {
            List<String> lexemList = divideLexemIntoLeafs(str);
            lexemList
                    .stream()
                    .forEach(leaf -> parse(leaf).stream().forEach(comp -> leafComponentResultList.add(comp)));
        }
        return leafComponentResultList;
    }

    private Component createLeafComponent(ComponentType componentType, String leafValue) {
        LOGGER.log(Level.INFO, "LexemeParser : got a new " + componentType.toString() + " '" + leafValue + "'");
        return new ContainerLeaf(componentType, leafValue);
    }

    private List<String> divideLexemIntoLeafs(String str) {
        List<String> leafList;
        if (str.matches(DIVIDER_BY_INTERM_PUNCT_MARK_REGEX)) {
            leafList = divideStringByPattern(str, DIVIDER_BY_INTERM_PUNCT_MARK_REGEX);
        } else {
            leafList = divideStringByPattern(str, DIVIDER_BY_TERMINAL_PUNCT_MARK_REGEX);
        }
        return leafList;
    }

    private List<String> divideStringByPattern(String str, String regex) {
        final int WORD_GROUP_NUMBER = 1;
        final int PUNCT_MARK_GROUP_NUMBER = 2;
        List<String> leafList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()) {
            leafList.add(matcher.group(WORD_GROUP_NUMBER));
            leafList.add(matcher.group(PUNCT_MARK_GROUP_NUMBER));
        }
        return leafList;
    }

}
