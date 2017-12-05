import by.kholodok.composite.action.impl.TextActionImpl;
import by.kholodok.composite.entity.Component;
import by.kholodok.composite.entity.ComponentType;
import by.kholodok.composite.entity.TextContainer;
import by.kholodok.composite.parser.VarsCounter;
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
 * Created by dmitrykholodok on 11/16/17
 */

public class ActionImplTest {

    private Component textContainer = new TextContainer(ComponentType.FULL_TEXT);
    private TextActionImpl action = new TextActionImpl();

    @BeforeClass
    private void init() {
        VarsCounter.addVar("i", 5);
        VarsCounter.addVar("j", 3);
        String text = "";
        try {
            text = new TextReader().readAllFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Component> componentList = new TextParser(new ParagraphParser(
                new SentenceParser(new LexemeParser(null)))).parse(text);
        for(Component component : componentList) {
            textContainer.addComponent(component);
        }
    }

    @Test
    public void testDeleteLexemsByParams() {
        Component resultComponent = action.deleteLexemsByParams(textContainer, 2, 'I');
        String resultText = resultComponent.toString();
        String[] resultLexemList = resultText.split("\\s");
        for(String lexem : resultLexemList) {
            if (lexem.length() == 2 && lexem.charAt(0) == 'I') {
                Assert.fail();
            }
        }
    }

    @Test
    public void testCountSentencesWithTheSameWords() {
        int count = action.countSentencesWithTheSameWords(textContainer);
        Assert.assertEquals(count, 4);
    }

}
