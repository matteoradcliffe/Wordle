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
		String word = wordList.get(getRandomIndex());
		this.currentWord = word.toUpperCase().toCharArray();
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
		wordleGrid[currentRow][currentColumn] = null;
		guess[currentColumn] = ' ';
		this.currentColumn--;
		this.currentColumn = Math.max(currentColumn, 0);
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
		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int index = 0; index < currentWord.length; index++) {
			// Decision point: branch based on this condition—explain why it matters for state flow.
			if (index != column && guess[column] == currentWord[index]) {
				return true;
			}
		}
		
		return false;
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