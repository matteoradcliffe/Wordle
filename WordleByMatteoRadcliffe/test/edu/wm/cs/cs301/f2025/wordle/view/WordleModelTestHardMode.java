package edu.wm.cs.cs301.f2025.wordle.view;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

import edu.wm.cs.cs301.f2025.wordle.model.*;
import edu.wm.cs.cs301.f2025.wordle.model.rules.*;

public class WordleModelTestHardMode {
	private WordleModel model;

    @BeforeEach
    public void setup() {
        model = new WordleModel();
        model.setWordList(Arrays.asList("APPLE", "ALIEN", "ANGEL", "BERRY", "CANDY"));
        model.setCurrentWord("APPLE");
        model.initialize();
        
        AcceptanceRule rule = new RuleHard(new RuleBasic());
        model.setAcceptanceRule(rule);
    }
    
    @Test
    public void testGreenLetterMustStayInSamePosition() {
        for (char c : "APPLE".toCharArray()) model.setCurrentColumn(c);
        model.setCurrentRow();

      
        for (char c : "BPPLE".toCharArray()) model.setCurrentColumn(c);
        boolean accepted = model.setCurrentRow();

        assertTrue(accepted, "setCurrentRow returns true to keep row active");
        WordleResponse[] row = model.getCurrentRow();
        assertNull(row[0], "guess should be rejecte because A not fixed at pos 0");
    }

    @Test
    public void testGrayLetterRejected() {
        model.setCurrentWord("APPLE");
        model.setWordList(Arrays.asList("APPLE", "ALIEN", "ANGEL"));
        for (char c : "ALIEN".toCharArray()) model.setCurrentColumn(c);
        model.setCurrentRow();

        for (char c : "LIONN".toCharArray()) model.setCurrentColumn(c);
        boolean accepted = model.setCurrentRow();
        assertTrue(accepted, "should allow retypng same row but not advance");
        assertNull(model.getCurrentRow()[0], "gray letter guess should be cleared");
    }

    @Test
    public void testYellowLetterMustAppearElsewhere() {
        for (char c : "PAPAL".toCharArray()) model.setCurrentColumn(c);
        model.setCurrentRow();


        for (char c : "ANGEL".toCharArray()) model.setCurrentColumn(c);
        boolean accepted = model.setCurrentRow();
        assertTrue(accepted);
        assertNull(model.getCurrentRow()[0], "guess mising yellow letter should be rejected");
    }
}
