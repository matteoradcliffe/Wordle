package edu.wm.cs.cs301.f2025.wordle.model;
import edu.wm.cs.cs301.f2025.wordle.model.rules.AcceptanceRule;

import java.util.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	        clearCurrentRowInput();   
	    }
	    return ok;
	}
	
	@Override
	public void initialize() {
	    if (wordList == null || wordList.isEmpty()) {
	        wordList = loadWordsFromFile("usa.txt");
	    }

	    grid = new WordleResponse[maximumRows][columnCount];
	    currentColumn = -1;
	    currentRow = 0;
	    guess = new char[columnCount];
	    generateCurrentWord();
	}
	
	private List<String> loadWordsFromFile(String filename) {
	    List<String> words = new ArrayList<>();
	    File file = new File("src/resources/" + filename);
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            line = line.trim().toUpperCase();
	            if (!line.isEmpty()) {
	                words.add(line);
	            }
	        }
	        System.out.println("sucessfully read " + words.size() + " words from " + filename);
	    } catch (Exception e) {
	        System.err.println("error loadin word list: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return words;
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
	
	public int getTotalGamesWon() {
	    return statistics.getTotalGamesWon();
	}

	public int getLastWin() {
	    return statistics.getLastWin();
	}

	public int[] calculateArrayOfWins() {
	    return statistics.calculateArrayOfWins(6);
	}
	@Override
	public void saveDataToFile() {
	    statistics.writeStatistics();
	}

	@Override
	public int getTotalGamesPlayed() {
	    return statistics.getTotalGamesPlayed();
	}

	@Override
	public int getCurrentStreak() {
	    return statistics.getCurrentStreak();
	}

	@Override
	public int getLongestStreak() {
	    return statistics.getLongestStreak();
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
    protected Statistics getStatistics() { return statistics; }
    
	
	
}
