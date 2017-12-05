package by.kholodok.composite.parser;

import by.kholodok.composite.entity.Component;

import java.util.List;

/**
 * Created by dmitrykholodok on 11/15/17
 */

public abstract class AbstractTextParser {

    protected AbstractTextParser nextParser;

    public AbstractTextParser(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract List<Component> parse(String str);

}
