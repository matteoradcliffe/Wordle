package edu.wm.cs.cs301.f2025.wordle.model;
import edu.wm.cs.cs301.f2025.wordle.model.rules.AcceptanceRule;

import java.util.*;
import java.awt.Color;

public abstract class AbstractModel implements Model{
	
	protected AcceptanceRule rule;

	protected final int columnCount = 5;
	protected final int maximumRows = 6;
	protected int currentColumn = -1;
	protected int currentRow = 0;
	
	protected char[] guess = new char[columnCount];
	protected WordleResponse[][] grid = new WordleResponse[maximumRows][columnCount];
	
	protected List<String> wordList = new ArrayList<>();
	protected final Statistics statistics = new Statistics();
	
	@Override
	public char[] getGuess() {
	    // return a copy so rules can't mutate internal state
	    return java.util.Arrays.copyOf(guess, guess.length);
	}
	
	@Override
	public java.util.List<String> getWordList() {
	    return wordList;
	}
	
	protected void clearCurrentRowInput() {
	    if (currentColumn < 0) return;
	    for (int c = 0; c <= currentColumn; c++) {
	        grid[currentRow][c] = null;
	        guess[c] = ' ';
	    }
	    currentColumn = -1;
	}
	
	protected boolean enforceRulesBeforeSubmit() {
	    if (rule == null) return true;
	    boolean ok = rule.isAcceptableGuess(this);
	    if (!ok) {
	        clearCurrentRowInput();   // reject & allow retyping in same row
	    }
	    return ok;
	}
	
	@Override
	public void initialize() {
		grid = new WordleResponse[maximumRows][columnCount];
		currentColumn = -1;
		currentRow = 0;
		guess = new char[columnCount];
		generateCurrentWord();
	}
	
	@Override
	public void setWordList(List<String> list) {
		wordList = list;
	}
	
	
	@Override
	public void setCurrentColumn(char c) {
		currentColumn++;
		currentColumn = Math.min(currentColumn, columnCount-1);
		char up = Character.toUpperCase(c);
		guess[currentColumn] = up;
		grid[currentRow][currentColumn] = new WordleResponse(up, Color.WHITE, Color.BLACK);
	}
	
	@Override
	public void backspace() {
		if (currentColumn >= 0) {
			grid[currentRow][currentColumn] = null;
			guess[currentColumn] = ' ';
			currentColumn--;
		}
		
	}
	
	@Override
	public WordleResponse[] getCurrentRow() {
		return grid[currentRow];
	}
	
	@Override
	public int getCurrentRowNumber() {
		return currentRow;
	}
	
	public void setAcceptanceRule(AcceptanceRule rule) {
        this.rule = rule;
    }
	
	public AcceptanceRule getAcceptanceRule() {
        return rule;
    }
	
	@Override
    public abstract void generateCurrentWord();
    @Override
    public abstract void setCurrentWord(String word);
    @Override
    public abstract String getCurrentWord();
    @Override
    public abstract boolean setCurrentRow();
    @Override public WordleResponse[][] getWordleGrid() { return grid; }
    @Override public int getMaximumRows() { return maximumRows; }
    @Override public int getColumnCount() { return columnCount; }
    @Override public void incrementTotalGamesPlayed() { statistics.incrementTotalGamesPlayed(); }
    @Override public void addWordsGuessed(int rowNumber) { statistics.addWordsGuessed(rowNumber); }
    @Override public void incrementCurrentStreak() { statistics.setCurrentStreak(statistics.getCurrentStreak() + 1); }
    @Override public void resetCurrentStreak() { statistics.setCurrentStreak(0); }
    @Override public int getCurrentColumn() { return currentColumn; }
    @Override public int getTotalWordCount() { return wordList.size(); }
    @Override public Statistics getStatistics() { return statistics; }
    
	
	
}
