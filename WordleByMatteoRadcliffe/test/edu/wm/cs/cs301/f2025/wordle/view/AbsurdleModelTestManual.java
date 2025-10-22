package edu.wm.cs.cs301.f2025.wordle.view;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

import edu.wm.cs.cs301.f2025.wordle.model.Model;         
import edu.wm.cs.cs301.f2025.wordle.model.AbsurdleModel;
import edu.wm.cs.cs301.f2025.wordle.model.WordleResponse;
import edu.wm.cs.cs301.f2025.wordle.model.AppColors;

public class AbsurdleModelTestManual {

	private Model model;

	
	
	
    @BeforeEach
    public void setup() {
        model = new AbsurdleModel();
        model.setWordList(Arrays.asList("CANDY", "COCOA", "COLON", "HELLO", "HOLLY", "HILLY"));
        model.generateCurrentWord();
    }

    
    @Test
    public void testAcceptsGuessInputFlow() {
        for (char c : "HELLO".toCharArray()) {
            model.setCurrentColumn(c);
        }

        boolean accepted = model.setCurrentRow();
        assertTrue(accepted, "absurdle should accept a full row of input.");

        WordleResponse[] responses = model.getCurrentRow();
        assertNotNull(responses, "responses should not be null even before logic is implemented.");
        assertEquals(5, responses.length, "each row should contain 5 WordleResponse objs.");
    }

    @Test
    public void testBackspaceRemovesLastLetter() {
        model.setCurrentColumn('A');
        model.setCurrentColumn('B');
        model.backspace();

        assertEquals(0, model.getCurrentColumn(), "After backspace, currentColumn should move back one position.");
    }

    @Test
    public void testPartitionSelectionConceptually() {
        
    	
        for (char c : "HELLO".toCharArray()) model.setCurrentColumn(c);
        boolean rowAccepted = model.setCurrentRow();
        assertTrue(rowAccepted, "row submission should stil return true.");
        WordleResponse[] row = model.getCurrentRow();

    
        long greens = Arrays.stream(row).filter(r -> AppColors.GREEN.equals(r.getBackgroundColor())).count();
        assertEquals(0, greens, " pattern should minimize greens at start (conceptually).");
    }

    @Test
    public void testGridDimensionsMatchModel() {
        WordleResponse[][] grid = model.getWordleGrid();
        assertEquals(model.getMaximumRows(), grid.length, "Grid should have correct number of rows.");
        assertEquals(model.getColumnCount(), grid[0].length, "Each row should have corect number of columns.");
    }
    
}
