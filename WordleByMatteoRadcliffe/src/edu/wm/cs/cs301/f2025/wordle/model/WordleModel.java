package edu.wm.cs.cs301.f2025.wordle.model;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import edu.wm.cs.cs301.f2025.wordle.controller.ReadWordsRunnable;

/**
 * The WordleModel class represents the WordleModel component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class WordleModel {
	
	private char[] currentWord, guess;
	
	private final int columnCount, maximumRows;
	private int currentColumn, currentRow;

	/** Field representing wordList. */
	private List<String> wordList;
	
	private final Random random;
	
	private final Statistics statistics;
	
	/** Field representing wordleGrid. */
	private WordleResponse[][] wordleGrid;
	
	private boolean wordsLoaded = false;
	
	public WordleModel() {
		this.currentColumn = -1;
		this.currentRow = 0;
		this.columnCount = 5;
		this.maximumRows = 6;
		// Construct a new object—initialize and configure it close to creation for readability.
		this.random = new Random();
		
		createWordList();
		
		this.wordleGrid = initializeWordleGrid();
		this.guess = new char[columnCount];
		// Construct a new object—initialize and configure it close to creation for readability.
		this.statistics = new Statistics();
	}
	
	/**
     * createWordList method performs its core logic or handles UI actions as defined.
     */
	private void createWordList() {
		// Construct a new object—initialize and configure it close to creation for readability.
		ReadWordsRunnable runnable = new ReadWordsRunnable(this);
		// Construct a new object—initialize and configure it close to creation for readability.
		new Thread(runnable).start();
	}
	
	
	
	/**
     * initialize method performs its core logic or handles UI actions as defined.
     */
	public void initialize() {
		this.wordleGrid = initializeWordleGrid();
		this.currentColumn = -1;
		this.currentRow = 0;
		generateCurrentWord();
		this.guess = new char[columnCount];
	}

	/**
     * generateCurrentWord method performs its core logic or handles UI actions as defined.
     */
	public void generateCurrentWord() {
		if (wordList == null || wordList.isEmpty()) {
			return;
		}
		
		String word = wordList.get(getRandomIndex());
		this.currentWord = word.toUpperCase().toCharArray();
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
	public String getCurrentWord() {
		// Construct a new object—initialize and configure it close to creation for readability.
		return new String(currentWord);
	}

	/**
     * getRandomIndex method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private int getRandomIndex() {
		int size = wordList.size();
		return random.nextInt(size);
	}
	
	/**
     * initializeWordleGrid method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private WordleResponse[][] initializeWordleGrid() {
		WordleResponse[][] wordleGrid = new WordleResponse[maximumRows][columnCount];

		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int row = 0; row < wordleGrid.length; row++) {
			// Loop over a known range/collection; watch indices and ensure side effects are intentional.
			for (int column = 0; column < wordleGrid[row].length; column++) {
				wordleGrid[row][column] = null;
			}
		}

		return wordleGrid;
	}
	
	/**
     * setWordList method performs its core logic or handles UI actions as defined.
     * @param wordList parameter description
     */
	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
		wordsLoaded = true;
	}
	
	/**
     * setCurrentColumn method performs its core logic or handles UI actions as defined.
     * @param c parameter description
     */
	public void setCurrentColumn(char c) {
		currentColumn++;
		currentColumn = Math.min(currentColumn, (columnCount - 1));
		guess[currentColumn] = c;
		// Construct a new object—initialize and configure it close to creation for readability.
		wordleGrid[currentRow][currentColumn] = new WordleResponse(c,
				Color.WHITE, Color.BLACK);
	}
	
	/**
     * backspace method performs its core logic or handles UI actions as defined.
     */
	public void backspace() {
		if (currentColumn >= 0) {
	        wordleGrid[currentRow][currentColumn] = null;
	        guess[currentColumn] = ' ';
	        currentColumn--;
	    }
	}
	
	/**
     * getCurrentRow method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public WordleResponse[] getCurrentRow() {
		return wordleGrid[getCurrentRowNumber()];
	}
	
	/**
     * getCurrentRowNumber method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public int getCurrentRowNumber() {
		return currentRow - 1;
	}
	
	/**
     * setCurrentRow method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public boolean setCurrentRow() {		
		String guessWord = new String(guess).toUpperCase();
		
		if (wordList == null || currentWord == null) {
			throw new IllegalStateException("word list or current word not initilize");
			
		}
		if (guessWord.length() != columnCount) {
	        throw new IllegalArgumentException("Incomplete guess: " + guessWord);
	    }

	    if (!wordList.contains(guessWord)) {
	        throw new IllegalArgumentException("Guess not in word list: " + guessWord);
	    }
		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int column = 0; column < guess.length; column++) {
			Color backgroundColor = AppColors.GRAY;
			Color foregroundColor = Color.WHITE;
			// Decision point: branch based on this condition—explain why it matters for state flow.
			if (guess[column] == currentWord[column]) {
				backgroundColor = AppColors.GREEN;
			} else if (contains(currentWord, guess, column)) {
				backgroundColor = AppColors.YELLOW;
			}
			
			// Construct a new object—initialize and configure it close to creation for readability.
			wordleGrid[currentRow][column] = new WordleResponse(guess[column],
					backgroundColor, foregroundColor);
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
		
		// count how many of that letter are in the current word
		for (char c : currentWord) {
			if (c == letter) countInWord++;
		}
		// this'll count how many of that letter have already been guessed correctly or marked yellow earlier
		for (int i = 0; i < column; i++) {
	        if (guess[i] == letter &&
	           (wordleGrid[currentRow][i] != null &&
	            (wordleGrid[currentRow][i].getBackgroundColor().equals(AppColors.GREEN) ||
	             wordleGrid[currentRow][i].getBackgroundColor().equals(AppColors.YELLOW)))) {
	            alreadyMatched++;
	        }
	    }

	    return alreadyMatched < countInWord;
	}

	/**
     * getWordleGrid method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public WordleResponse[][] getWordleGrid() {
		return wordleGrid;
	}
	
	/**
     * getMaximumRows method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public int getMaximumRows() {
		return maximumRows;
	}

	/**
     * getColumnCount method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public int getColumnCount() {
		return columnCount;
	}

	/**
     * getCurrentColumn method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public int getCurrentColumn() {
		return currentColumn;
	}

	/**
     * getTotalWordCount method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public int getTotalWordCount() {
		return wordList.size();
	}

	/**
     * getStatistics method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public Statistics getStatistics() {
		return statistics;
	}

}
