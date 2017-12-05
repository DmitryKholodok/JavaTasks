import by.kholodok.composite.entity.Component;
import by.kholodok.composite.entity.ComponentType;
import by.kholodok.composite.entity.ContainerLeaf;
import by.kholodok.composite.entity.TextContainer;
import by.kholodok.composite.parser.impl.LexemeParser;
import by.kholodok.composite.parser.impl.ParagraphParser;
import by.kholodok.composite.parser.impl.SentenceParser;
import by.kholodok.composite.parser.impl.TextParser;
import by.kholodok.composite.reader.TextReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrykholodok on 11/15/17
 */

public class ParserTest {

    private String testText;
    List<Component> expectedParagraphList;

    @BeforeClass
    public void init() {
        testText = "Sentence.";
        expectedParagraphList = new ArrayList<>();
        Component word = new ContainerLeaf(ComponentType.WORD, "Sentence");
        Component dot = new ContainerLeaf(ComponentType.TERMINAL_PUNCTUATION_MARK, ".");
        Component lexem = new TextContainer(ComponentType.LEXEME);
        lexem.addComponent(word);
        lexem.addComponent(dot);
        Component sentence = new TextContainer(ComponentType.SENTENCE);
        sentence.addComponent(lexem);
        Component paragraph = new TextContainer(ComponentType.PARAGRAPH);
        paragraph.addComponent(sentence);
        expectedParagraphList.add(paragraph);
    }

    @Test
    public void testParse() {
        List<Component> resultComponentList =
                new TextParser(new ParagraphParser(new SentenceParser(new LexemeParser(null)))).parse(testText);
        Assert.assertEquals(resultComponentList, expectedParagraphList);
    }


}
