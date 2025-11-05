package edu.wm.cs.cs301.f2025.wordle.model;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import edu.wm.cs.cs301.f2025.wordle.controller.ReadWordsRunnable;


/**
 * The WordleModel class represents the WordleModel component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class WordleModel extends AbstractModel {
	private char[] currentWord;
	private final Random random = new Random();
	
	
	/**
     * generateCurrentWord method performs its core logic or handles UI actions as defined.
     */
	@Override
	public void generateCurrentWord() {
		if (wordList == null || wordList.isEmpty()) {
			return;
		}
		String word = wordList.get(random.nextInt(wordList.size()));
		currentWord = word.toUpperCase().toCharArray();
	}
	
	
	/**
	 * Sets the current target word manually.
	 * This method is more for testing purposes to make
	 * behavior deterministic
	 * It does not alter the word list or any color logic.
	 *
	 * @param word the word to use as the current target
	 * @throws IllegalArgumentException if the word is not in the word list
	 */
	@Override
	public void setCurrentWord(String word) {
	    if (wordList == null || !wordList.contains(word.toUpperCase())) {
	        throw new IllegalArgumentException("word must exist in the currnt word list: " + word);
	    }
	    currentWord = word.toUpperCase().toCharArray();
	}

	
	/**
     * getCurrentWord method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	@Override
	public String getCurrentWord() {
		// Construct a new object—initialize and configure it close to creation for readability.
		return new String(currentWord);
	}


	/**
     * setCurrentRow method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	@Override
	public boolean setCurrentRow() {
	    
	    if (!enforceRulesBeforeSubmit()) {
	        return true;
	    }

	    String guessWord = new String(guess).toUpperCase();

	   
	    if (wordList == null || currentWord == null) {
	        throw new IllegalStateException("Word list or current word not initialized");
	    }

	    if (guessWord.length() != columnCount) {
	        throw new IllegalArgumentException("Incomplete guess: " + guessWord);
	    }

	    boolean inList = wordList.stream().anyMatch(w -> w != null && w.equalsIgnoreCase(guessWord));
	    if (!inList) {
	        throw new IllegalArgumentException("Guess not in word list: " + guessWord);
	    }

	    
	    boolean allCorrect = true;
	    for (int column = 0; column < guess.length; column++) {
	        Color backgroundColor = AppColors.GRAY;
	        Color foregroundColor = Color.WHITE;

	        if (guess[column] == currentWord[column]) {
	            backgroundColor = AppColors.GREEN;
	        } else if (contains(currentWord, guess, column)) {
	            backgroundColor = AppColors.YELLOW;
	            allCorrect = false;
	        } else {
	            allCorrect = false;
	        }

	        grid[currentRow][column] = new WordleResponse(guess[column], backgroundColor, foregroundColor);
	    }

	    
	    if (allCorrect) {
	        System.out.println(" word gussed correctly: " + guessWord);
	        
	        return false;
	    }

	    
	    currentColumn = -1;
	    currentRow++;
	    guess = new char[columnCount];

	    
	    return currentRow < maximumRows;
	}
	
	
	/**
     * contains method performs its core logic or handles UI actions as defined.
     * @param currentWord parameter description
     * @param guess parameter description
     * @param column parameter description
     * @return result of the operation
     */
	private boolean contains(char[] currentWord, char[] guess, int column) {
		char letter = guess[column];
		int countInWord = 0;
		int alreadyMatched = 0;
		
		
		for (char c : currentWord) {
			if (c == letter) countInWord++;
		}
		
		for (int i = 0; i < column; i++) {
	        if (guess[i] == letter &&
	           (grid[currentRow][i] != null &&
	            (grid[currentRow][i].getBackgroundColor().equals(AppColors.GREEN) ||
	             grid[currentRow][i].getBackgroundColor().equals(AppColors.YELLOW)))) {
	            alreadyMatched++;
	        }
	    }

	    return alreadyMatched < countInWord;
	}
}