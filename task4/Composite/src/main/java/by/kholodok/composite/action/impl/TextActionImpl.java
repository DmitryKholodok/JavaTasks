package by.kholodok.composite.action.impl;

import by.kholodok.composite.action.TextAction;
import by.kholodok.composite.entity.Component;
import by.kholodok.composite.entity.ComponentType;
import by.kholodok.composite.entity.TextContainer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by dmitrykholodok on 11/16/17
 */

public class TextActionImpl implements TextAction {

    private static final Logger LOGGER = LogManager.getLogger(TextActionImpl.class);

    @Override
    public void printSentencesByLexemCountInAscOrder(Component component) {
        LOGGER.log(Level.DEBUG, "printSentencesByLexemCountInAscOrder : started...");
        List<Component> sentenceList = component.receiveComponentList(ComponentType.PARAGRAPH);
        Map<Integer, Integer> sentenceIndexToLexemCountMap = createSentenceIndexToLexemCountMap(sentenceList);
        List<Map.Entry<Integer, Integer>> sortedListByLexemCount = sortMapByValue(sentenceIndexToLexemCountMap);
        for(int i = 0; i < sortedListByLexemCount.size(); i++) {
            int sentenceIndexInList = sortedListByLexemCount.get(i).getKey();
            LOGGER.log(Level.INFO, "printSentencesByLexemCountInAscOrder : got sentence " +
                    sentenceList.get(sentenceIndexInList));
            LOGGER.log(Level.INFO, "printSentencesByLexemCountInAscOrder : length = " +
                    sentenceIndexToLexemCountMap.get(sentenceIndexInList));
        }
        LOGGER.log(Level.DEBUG, "printSentencesByLexemCountInAscOrder : finished...");
    }

    @Override
    public Component deleteLexemsByParams(Component component, int lexemLenght, char startChar) {
        LOGGER.log(Level.DEBUG, "deleteLexems : started...");
        Component resultComponent = new TextContainer(ComponentType.FULL_TEXT);
        List<Component> lexemListForDeleting = new ArrayList<>();
        List<Component> lexemList = component.receiveComponentList(ComponentType.SENTENCE);
        for (Component lexem : lexemList) {
            String lexemString = lexem.toString().trim();
            if ((lexemString.charAt(0) == startChar) && (lexemString.length() == lexemLenght)) {
                LOGGER.log(Level.INFO, "deleteLexems : found lexem for deleting - " + lexemString);
                lexemListForDeleting.add(lexem);
            }
        }
        LOGGER.log(Level.INFO, "deleteLexems : lexem deleting...");
        lexemListForDeleting.stream().forEach(lexem -> lexemList.remove(lexem));
        lexemList.stream().forEach(lexem -> resultComponent.addComponent(lexem));
        LOGGER.log(Level.DEBUG, "deleteLexems : finished...");
        return resultComponent;
    }

    @Override
    public int countSentencesWithTheSameWords(Component component) {
        LOGGER.log(Level.DEBUG, "countSentencesWithTheSameWords : started...");
        boolean theSameWordsFound;
        int count = 0;
        List<Component> sentences = component.receiveComponentList(ComponentType.PARAGRAPH);
        for (Component sentence : sentences) {
            List<Component> words = sentence.receiveComponentList(ComponentType.WORD);
            theSameWordsFound = false;
            for(int i = 0; i < words.size() - 1 && !theSameWordsFound; i++) {
                for(int j = i + 1; j < words.size() && !theSameWordsFound; j++) {
                    if (words.get(i).equals(words.get(j))) {
                        LOGGER.log(Level.INFO, "countSentencesWithTheSameWords : got the same words - " + words.get(i));
                        count++;
                        theSameWordsFound = true;
                    }
                }
            }
        }
        LOGGER.log(Level.DEBUG, "countSentencesWithTheSameWords : finished...");
        return count;
    }


    private Map<Integer, Integer> createSentenceIndexToLexemCountMap(List<Component> componentList) {
        Map<Integer, Integer> sentenceIndexToLexemCountMap = new HashMap<>();
        int index = 0;
        for (Component component : componentList) {
            sentenceIndexToLexemCountMap.put(index++, calculateComponentCountInSentence(component, ComponentType.SENTENCE));
        }
        return sentenceIndexToLexemCountMap;
    }

    private int calculateComponentCountInSentence(Component component, ComponentType componentType) {
        List<Component> componentList = component.receiveComponentList(componentType);
        return componentList.size();
    }

    private <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> sortMapByValue(Map<K, V> sourceMap) {
        List<Map.Entry<K, V>> entryList = new ArrayList<>();
        Stream <Map.Entry<K, V>> listStream = sourceMap.entrySet().stream();
        listStream.sorted(Comparator.comparing(e -> e.getValue())).forEach(e->entryList.add(e));
        return entryList;
    }

}
