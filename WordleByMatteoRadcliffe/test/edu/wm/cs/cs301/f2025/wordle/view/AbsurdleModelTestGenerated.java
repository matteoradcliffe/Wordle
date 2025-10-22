package edu.wm.cs.cs301.f2025.wordle.view;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

import edu.wm.cs.cs301.f2025.wordle.model.Model;       
import edu.wm.cs.cs301.f2025.wordle.model.AbsurdleModel;  
import edu.wm.cs.cs301.f2025.wordle.model.WordleResponse; 
import edu.wm.cs.cs301.f2025.wordle.model.AppColors;

/**
 * Auto-generated style test suite for AbsurdleModel (PPP stage).
 * These ensure structure and method compatibility; failures are expected.
 */

public class AbsurdleModelTestGenerated {
	
	private Model model;

    @BeforeEach
    public void setup() {
        model = new AbsurdleModel();
        model.setWordList(Arrays.asList(
            "APPLE", "APPLY", "PAPAL", "ALLEY", "LAPEL", "ZEBRA"
        ));
        model.generateCurrentWord();
    }

    @Test
    public void testHandlesDuplicateLettersGracefully() {
        for (char c : "PAPAL".toCharArray()) model.setCurrentColumn(c);
        boolean ok = model.setCurrentRow();
        assertTrue(ok, "Model should accept guess with duplicate letters.");
        WordleResponse[] row = model.getCurrentRow();
        assertEquals(5, row.length, "Each guess row should have 5 letters.");
    }

    @Test
    public void testStatisticsIntegrationMethodsExist() {
        model.incrementTotalGamesPlayed();
        model.addWordsGuessed(3);
        model.incrementCurrentStreak();
        model.resetCurrentStreak();

        assertNotNull(model.getStatistics(), "Statistics object should not be null.");
    }

    @Test
    public void testWordCountReflectsWordListSize() {
        int expected = 6; // list has 6 entries
        assertEquals(expected, model.getTotalWordCount(),
                "Total word count should match word list size.");
    }

    @Test
    public void testInitializationResetsState() {
        model.initialize();
        assertEquals(0, model.getCurrentRowNumber(),
                "After initialize(), current row number should reset to 0.");
        assertEquals(-1, model.getCurrentColumn(),
                "After initialize(), current column should reset to -1.");
    }

    @Test
    public void testMaxRowsAndColumns() {
        assertEquals(6, model.getMaximumRows(), "Absurdle should default to 6 rows.");
        assertEquals(5, model.getColumnCount(), "Absurdle should default to 5 columns.");
    }

}
