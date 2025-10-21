package edu.wm.cs.cs301.f2025.wordle.model;

import java.util.List;

public interface Model {

	/**
	 * initialize method performs its core logic or handles UI actions as defined.
	 */
	void initialize();

	/**
	 * generateCurrentWord method performs its core logic or handles UI actions as defined.
	 */
	void generateCurrentWord();

	/**
	 * Sets the current target word manually.
	 * This method is more for testing purposes to make
	 * behavior deterministic
	 * It does not alter the word list or any color logic.
	 *
	 * @param word the word to use as the current target
	 * @throws IllegalArgumentException if the word is not in the word list
	 */

	void setCurrentWord(String word);

	/**
	 * getCurrentWord method performs its core logic or handles UI actions as defined.
	 * @return result of the operation
	 */
	String getCurrentWord();

	/**
	 * setWordList method performs its core logic or handles UI actions as defined.
	 * @param wordList parameter description
	 */
	void setWordList(List<String> wordList);

	/**
	 * setCurrentColumn method performs its core logic or handles UI actions as defined.
	 * @param c parameter description
	 */
	void setCurrentColumn(char c);

	/**
	 * backspace method performs its core logic or handles UI actions as defined.
	 */
	void backspace();

	/**
	 * getCurrentRow method performs its core logic or handles UI actions as defined.
	 * @return result of the operation
	 */
	WordleResponse[] getCurrentRow();

	/**
	 * getCurrentRowNumber method performs its core logic or handles UI actions as defined.
	 * @return result of the operation
	 */
	int getCurrentRowNumber();

	/**
	 * setCurrentRow method performs its core logic or handles UI actions as defined.
	 * @return result of the operation
	 */
	boolean setCurrentRow();

	/**
	 * getWordleGrid method performs its core logic or handles UI actions as defined.
	 * @return result of the operation
	 */
	WordleResponse[][] getWordleGrid();

	/**
	 * getMaximumRows method performs its core logic or handles UI actions as defined.
	 * @return result of the operation
	 */
	int getMaximumRows();

	/**
	 * getColumnCount method performs its core logic or handles UI actions as defined.
	 * @return result of the operation
	 */
	int getColumnCount();

	/**
	 * getCurrentColumn method performs its core logic or handles UI actions as defined.
	 * @return result of the operation
	 */
	int getCurrentColumn();

	/**
	 * getTotalWordCount method performs its core logic or handles UI actions as defined.
	 * @return result of the operation
	 */
	int getTotalWordCount();

	/**
	 * getStatistics method performs its core logic or handles UI actions as defined.
	 * @return result of the operation
	 */
	Statistics getStatistics();

	//Law of delimeter helpers
	void incrementTotalGamesPlayed();

	void addWordsGuessed(int rowNumber);

	void incrementCurrentStreak();

	void resetCurrentStreak();

}