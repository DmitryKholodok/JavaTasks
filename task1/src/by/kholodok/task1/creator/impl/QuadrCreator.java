package by.kholodok.task1.creator.impl;

import by.kholodok.task1.creator.Creator;
import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.parser.Parser;
import by.kholodok.task1.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class QuadrCreator implements Creator {

    private Validator validator;
    private Parser parser;

    public QuadrCreator(Validator validator, Parser parser) {
        this.validator = validator;
        this.parser = parser;
    }

    @Override
    public List<Entity> create(List<String> lines) {
        List<Entity> quadrList = new ArrayList<>();
        for (String line : lines) {
            Entity entity = create(line);
            if (entity != null) {
                quadrList.add(entity);
            }
        }
        return quadrList;
    }

    @Override
    public Entity create(String str) {
        if (validator.isValid(str)) {
            return parser.parse(str);
        }
        return null;
    }

}
