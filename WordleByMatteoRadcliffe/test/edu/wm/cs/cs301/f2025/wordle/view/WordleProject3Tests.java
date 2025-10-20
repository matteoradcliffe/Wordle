package edu.wm.cs.cs301.f2025.wordle.view;

import edu.wm.cs.cs301.f2025.wordle.model.WordleModel;
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
	}
	
	@Test
    public void testBug3MustGuessRealWords() {
		
	}
	
	@Test
    public void testBug4Backspace() {
		
	}
    
	@Test
    public void testBug5KeyboardColors() {
		
	}
	
}
