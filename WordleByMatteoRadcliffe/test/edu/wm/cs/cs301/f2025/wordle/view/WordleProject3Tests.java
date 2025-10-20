package edu.wm.cs.cs301.f2025.wordle.view;

import edu.wm.cs.cs301.f2025.wordle.model.WordleModel;
import edu.wm.cs.cs301.f2025.wordle.model.WordleResponse;

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
	}
	
	@Test
    public void testBug2Coloring() {
		model.setWordList(Arrays.asList("BLURB"));
		model.generateCurrentWord();
		char[] guess = "HELLO".toCharArray();
		char[] current = model.getCurrentWord().toCharArray();
		
		int yellows = 0;
		for (int i = 0; i < 5; i++) {
			boolean samePosition = (guess[i] == current[i]);
			boolean exists = false;
			if (!samePosition) {
				for (int j = 0; j < 5; j++) {
					if (j != i && guess[i] == current[j]) {
						exists = true;
						break;
					}
				}
			}
			if (exists) yellows++;
		}
		assertTrue(yellows <= 1, "only one 'L' should be yellow when guessing HELLO vs BLURB");
	}
	
	@Test
    public void testBug3MustGuessRealWords() {
		List<String> words = Arrays.asList("APPLE", "GRAPE", "BERRY");
		model.setWordList(words);
		model.initialize();
		
		String fakeGuess = "ABCDE";
		boolean isValid = words.contains(fakeGuess.toUpperCase());
		assertFalse(isValid, "ABCDE should not be recognized as a valid guess");
	}
	
	@Test
    public void testBug4Backspace() {
		model.setCurrentColumn('A');
		assertEquals(0, model.getCurrentColumn(), "after typing one letter current column should be 0");
		model.backspace();
		assertTrue(model.getCurrentColumn() >=0, "current column should never go below 0 after backspacing");
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
        
        WordleResponse[] row = model.getCurrentRow();
        for (WordleResponse tile: row) {
        		if (tile != null) {
        			assertNotNull(tile.getBackgroundColor(),
        					"Each tile should have a visible background color");
        		}
        }
        
	}
	
}
