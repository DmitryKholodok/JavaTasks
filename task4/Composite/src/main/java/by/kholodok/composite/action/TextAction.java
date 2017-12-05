package by.kholodok.composite.action;

import by.kholodok.composite.entity.Component;
import by.kholodok.composite.entity.TextContainer;

/**
 * Created by dmitrykholodok on 11/16/17
 */
public interface TextAction {
    void printSentencesByLexemCountInAscOrder(Component component);
    Component deleteLexemsByParams(Component component, int lexemLenght, char startChar);
    int countSentencesWithTheSameWords(Component component);
}
