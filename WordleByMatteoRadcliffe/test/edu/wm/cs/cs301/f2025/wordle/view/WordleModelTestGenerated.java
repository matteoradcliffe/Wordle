package edu.wm.cs.cs301.f2025.wordle.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import edu.wm.cs.cs301.f2025.wordle.model.AppColors;
import edu.wm.cs.cs301.f2025.wordle.model.WordleResponse;
import edu.wm.cs.cs301.f2025.wordle.model.WordleModel;


public class WordleModelTestGenerated {
	private WordleModel model;

    @BeforeEach
    public void setUp() {
        model = new WordleModel();
        model.setWordList(Arrays.asList("APPLE", "GRAPE", "BERRY"));
        model.initialize();
    }

    /** 
     * Ensures that generateCurrentWord() picks a word from the provided list.
     */
    @Test
    public void testGenerateCurrentWordChoosesFromList() {
        String current = model.getCurrentWord();
        assertTrue(Arrays.asList("APPLE", "GRAPE", "BERRY").contains(current),
                "Generated word must come from the defined list.");
    }

    /**
     * Verifies that grid dimensions remain consistent after initialization.
     */
    @Test
    public void testGridDimensions() {
        WordleResponse[][] grid = model.getWordleGrid();
        assertEquals(6, grid.length, "Grid should contain 6 rows.");
        assertEquals(5, grid[0].length, "Each row should contain 5 columns.");
    }

    /**
     * Ensures that calling backspace() on an empty row does not throw.
     */
    @Test
    public void testBackspaceOnEmptyRowDoesNotThrow() {
        assertDoesNotThrow(() -> model.backspace(),
                "Backspace on an empty grid should not crash the game.");
    }

    /**
     * Simulates a valid full-row guess submission and verifies state changes.
     */
    @Test
    public void testSubmitRowAdvancesRowCounter() {
        for (char c : "APPLE".toCharArray()) {
            model.setCurrentColumn(c);
        }
        boolean result = model.setCurrentRow();
        assertTrue(result, "Submitting a row should advance to the next row if under max.");
        assertEquals(1, model.getCurrentRowNumber(), "After one guess, row index should increase.");
    }

    /**
     * Checks that all letters turn GREEN when the correct word is guessed.
     */
    @Test
    public void testAllGreenForCorrectGuess() {
        String secret = model.getCurrentWord();
        for (char c : secret.toCharArray()) {
            model.setCurrentColumn(c);
        }
        model.setCurrentRow();
        WordleResponse[] responses = model.getCurrentRow();
        for (WordleResponse cell : responses) {
            assertEquals(AppColors.GREEN, cell.getBackgroundColor(),
                    "Correct word should yield all green cells.");
        }
    }

    /**
     * Ensures that letters not present in the secret word turn GRAY.
     */
    @Test
    public void testGrayForIncorrectLetters() {
        model.setWordList(Arrays.asList("APPLE"));
        model.generateCurrentWord();
        for (char c : "ZZZZZ".toCharArray()) {
            model.setCurrentColumn(c);
        }
        model.setCurrentRow();
        WordleResponse[] row = model.getCurrentRow();

        boolean allGray = Arrays.stream(row)
                .allMatch(r -> AppColors.GRAY.equals(r.getBackgroundColor()));
        assertTrue(allGray, "All letters not in word should be gray.");
    }

    /**
     * Validates that yellow feedback occurs for misplaced letters.
     */
    @Test
    public void testYellowFeedbackForMisplacedLetters() {
        model.setWordList(Arrays.asList("APPLE"));
        model.generateCurrentWord();
        for (char c : "PAPLE".toCharArray()) {
            model.setCurrentColumn(c);
        }
        model.setCurrentRow();
        WordleResponse[] row = model.getCurrentRow();
        boolean hasYellow = Arrays.stream(row)
                .anyMatch(r -> AppColors.YELLOW.equals(r.getBackgroundColor()));
        assertTrue(hasYellow, "Misplaced correct letters should be yellow.");
    }

    /**
     * Ensures that an empty word list triggers an error when generating a word.
     */
    @Test
    public void testGenerateWordWithEmptyListThrows() {
        WordleModel emptyModel = new WordleModel();
        emptyModel.setWordList(Collections.emptyList());
        assertThrows(Exception.class, emptyModel::generateCurrentWord,
                "Generating from an empty list should throw.");
    }

    /**
     * Checks that getTotalWordCount() matches list size.
     */
    @Test
    public void testGetTotalWordCountMatchesListSize() {
        assertEquals(3, model.getTotalWordCount(),
                "Word count should equal the size of the current word list.");
    }

    /**
     * //TODO: Investigate bug — backspace resets column index incorrectly after full row submission.
     * This test currently fails because currentColumn is not reset to -1 after submission.
     */
    @Test
    public void testColumnIndexResetsAfterSubmission() {
        for (char c : "APPLE".toCharArray()) {
            model.setCurrentColumn(c);
        }
        model.setCurrentRow();
        assertEquals(-1, model.getCurrentColumn(),
                "After row submission, currentColumn should reset to -1.");
    }
}

