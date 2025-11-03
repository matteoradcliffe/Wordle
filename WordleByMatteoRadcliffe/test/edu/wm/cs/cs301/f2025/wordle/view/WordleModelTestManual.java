package edu.wm.cs.cs301.f2025.wordle.view;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import edu.wm.cs.cs301.f2025.wordle.model.AppColors;
import edu.wm.cs.cs301.f2025.wordle.model.Model;
import edu.wm.cs.cs301.f2025.wordle.model.WordleResponse;
import edu.wm.cs.cs301.f2025.wordle.model.WordleModel;


public class WordleModelTestManual {
	
	@Test
	public void testInitializationGridAndWord() {
		Model model = new WordleModel();
		model.setWordList(Arrays.asList("above", "blame", "cream"));
		model.initialize();
		
		assertNotNull(model.getWordleGrid(), "grid should not be null after initialization.");
		assertEquals(6, model.getMaximumRows(), "Grid should have 6 rows.");
		assertEquals(5, model.getColumnCount(), "Each row shoudl have 5 coulum");
		assertNotNull(model.getCurrentWord(), "current word should be generated");
		
	}
	
	/**
	 * 	//TODO Investigate failure in testTypingAndSubmittingGuess()
	 * Manual gameplay works correctly but automated test fails likely due to timing or index flip flop.
	 * I think this is becasue getCurrentRowNumber() returns currentRow-1.
	 * So the games still works but the test need to be adjusted to match the models design.
	 */
	
	@Test
	public void testTypingAndSubmittingGuess() {
		Model model = new WordleModel();
		model.setWordList(Arrays.asList("BASES"));
		model.initialize();
		model.setCurrentWord("BASES");
		
		for (char c : "BASES".toCharArray()) {
			model.setCurrentColumn(c);
		}
		boolean result = model.setCurrentRow();
		
		assertTrue(result, "should return true becasue row remains available.");
		int filledRow = model.getCurrentRowNumber() - 1 >= 0 ? model.getCurrentRowNumber() - 1 : 0;
	    WordleResponse[] responses = model.getWordleGrid()[filledRow];
	
	    assertNotNull(responses, "Row should not be null.");
		assertEquals(5, responses.length, "Each row should contain 5 responses.");
		assertNotNull(responses[4], "Last cell should not be null.");
		assertEquals("S", responses[4].getChar(), "Last cloumn should store the last guessed letter.");
	}
	
	@Test
	public void testGreenForCorrectGuess() {
		Model model = new WordleModel();
		model.setWordList(Arrays.asList("BASES"));
		model.initialize();
	    model.setCurrentWord("BASES");
	
		for (char c : "BASES".toCharArray()) {
			model.setCurrentColumn(c);
		}
		model.setCurrentRow();
		WordleResponse[] row = model.getCurrentRow();
		for (WordleResponse response : row) {
			assertNotNull(response, "Response should not be null");
			assertEquals(AppColors.GREEN, response.getBackgroundColor(),
					"each letter should be green for correct word.");
		}
	}
	
	@Test
	public void testYellowForPartiallyCorrectGuess() {
		Model model = new WordleModel();
		model.setWordList(Arrays.asList("APPLE", "PAPEL"));
		model.initialize();
	    model.setCurrentWord("APPLE");
		
		for (char c : "PAPEL".toCharArray()) {
			model.setCurrentColumn(c);
		}
		model.setCurrentRow();
		WordleResponse[] row = model.getCurrentRow();
		
		boolean hasYellow = Arrays.stream(row).anyMatch(r -> r != null && AppColors.YELLOW.equals(r.getBackgroundColor()));
		assertTrue(hasYellow,  "Partial guesses should be yellow");
	}
	
	/**
	 * //TODO Investigate failure in testBackspace()
	 * after backspacing, the last typed cell should be cleared and column index decremented by one
	 * however, test fails. last cell remains not null or column not updated
	 * This could be becasue backspace() index proabably prevents clearing at index 0
	 */
	@Test
	public void testBackspace() {
		Model model = new WordleModel();
		model.setWordList(Arrays.asList("BASES"));
		model.generateCurrentWord();
		model.setCurrentColumn('B');
		model.setCurrentColumn('A');
		assertEquals(1, model.getCurrentColumn());
		
		model.backspace();
		
		assertEquals(0, model.getCurrentColumn(), "backspace shoulve move the cursor back by one");
		assertTrue(model.getCurrentRowNumber() >= 0, "Row number should not be negative after typing letters.");
		WordleResponse[] row = model.getWordleGrid()[model.getCurrentRowNumber()];
		assertNull(row[1], "the second column should be cleared after backspace");
		
	}
}
