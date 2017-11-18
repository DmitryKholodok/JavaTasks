package by.kholodok.task1.creator.impl;

import by.kholodok.task1.collection.QuadrCollection;
import by.kholodok.task1.creator.Creator;
import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.parser.AbstractParser;
import by.kholodok.task1.validator.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class QuadrCreator implements Creator {

    private final static Logger LOGGER = LogManager.getLogger(QuadrCreator.class);
    private final Validator validator;
    private final AbstractParser parser;

    public QuadrCreator(Validator validator, AbstractParser parser) {
        this.validator = validator;
        this.parser = parser;
    }

    @Override
    public List<Entity> create(List<String> lines) {
        List<Entity> quadrList = new ArrayList<>();
        for (String line : lines) {
            Entity entity = create(line);
            if (entity != null) {
                LOGGER.log(Level.INFO, "Was created a new entity - " + entity.toString());
                quadrList.add(entity);
                //((Quadrilateral)entity).addObserver(new PerimeterObserver());
                //((Quadrilateral)entity).addObserver(new AreaObserver());

            }
        }
        return quadrList;
    }

    @Override
    public Entity create(String str) {
        if (validator.isValid(str)) {
            Entity entity = parser.parse(str);
            QuadrCollection.getInstance().addQuadr((Quadrilateral)entity);
            return entity;
        }
        return null;
    }

}
