package edu.wm.cs.cs301.f2025.wordle.view;

import edu.wm.cs.cs301.f2025.wordle.model.WordleModel;
import edu.wm.cs.cs301.f2025.wordle.model.WordleResponse;
import edu.wm.cs.cs301.f2025.wordle.model.AppColors;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


public class WordleProject3Tests {

	private WordleModel model;
	
	@BeforeEach
    public void setUp() {
		model = new WordleModel();
		model.setWordList(Arrays.asList("APPLE", "GRAPE", "BERRY", "BLURB", "HELLO"));
		model.initialize();
	}

	@Test
    public void testBug1ThreadSynchronization() {
		String word = model.getCurrentWord();
		assertNotNull(word, "current word should not be null after initialization.");
		assertTrue(word.length() == 5, "current word must have 5 letters");
		assertNotNull(model.getWordleGrid(), "no more intentional fail");
	}
	
	@Test
    public void testBug2Coloring() {
		model.setWordList(Arrays.asList("APPLE"));
		model.generateCurrentWord();
		

		for (char c : "PAPAL".toCharArray()) {
	        model.setCurrentColumn(c);
	    }
		model.setCurrentRow();
		WordleResponse[] responses = model.getCurrentRow();
		int yellows = 0;
		for (WordleResponse r : responses) {
			if (r != null && r.getChar() == 'P' && r.getBackgroundColor().equals(AppColors.YELLOW)) {
				yellows++;
			}
		}
		assertTrue(yellows > 1, "intentionl fail: code marks Ls yellow instead of one");
	}
	
	@Test
    public void testBug3MustGuessRealWords() {
		List<String> words = Arrays.asList("APPLE", "GRAPE", "BERRY");
		model.setWordList(words);
		model.initialize();
		
		String fakeGuess = "ABCDE";
		boolean isValid = words.contains(fakeGuess.toUpperCase());
		assertFalse(isValid, "ABCDE should not be recognized as a valid guess");
		assertThrows(IllegalArgumentException.class, () -> model.setCurrentRow(), 
	             "intentional fail: model accepts guesess not in list");
	}
	
	@Test
    public void testBug4Backspace() {
		model.setCurrentColumn('A');
		assertEquals(0, model.getCurrentColumn(), "after typing one letter current column should be 0");
		model.backspace();
		assertTrue(model.getCurrentColumn() >=0, "current column should never go below 0 after backspacing");
		
		model.setCurrentColumn('A');
		model.backspace();
		assertEquals(-1, model.getCurrentColumn(), "backspace bug: currentColumn goes below 0 (intentional fail)");
	}
    
	@Test
    public void testBug5KeyboardColors() {
		model.setWordList(Arrays.asList("GRAPE"));
		model.generateCurrentWord();
		
		model.setCurrentColumn('G');
        model.setCurrentColumn('R');
        model.setCurrentColumn('A');
        model.setCurrentColumn('P');
        model.setCurrentColumn('E');
        
        try {
            WordleResponse[] row = model.getCurrentRow();
            for (WordleResponse tile : row) {
                if (tile != null) {
                    assertNotNull(tile.getBackgroundColor(),
                        "each tile should have a visible background color");
                }
            }
            fail("intentional fail: keyboard color readability bug still present");
        } catch (Exception e) {
            fail("intentional fail: keyboard color bug cause exception " + e.getClass().getSimpleName());
        }
        
	}
	
}
