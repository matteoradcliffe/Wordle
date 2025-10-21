package edu.wm.cs.cs301.f2025.wordle.model;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


public class AbsurdleModel implements Model {

    private List<String> fullDictionary = new ArrayList<>();
    private List<String> currentPossible = new ArrayList<>();
    private int currentRow = 0;
    private int currentColumn = -1;
    private final int columnCount = 5;
    private final int maximumRows = 6;
    private char[] guess = new char[columnCount];
    private WordleResponse[][] grid = new WordleResponse[maximumRows][columnCount];
    private Statistics stats = new Statistics();

    @Override
    public void initialize() {

        // 1. reset all game state variables
        // 2. current possible dict
        // 3. reset row/colum counters
    }

    @Override
    public void generateCurrentWord() {
        // Absurdle never commits to one word.
        // Keep current possible as all valid candidates initially.
    }

    @Override
    public void setCurrentWord(String word) {
        // Used only for testing  may set current possible = [word].
    }

    @Override
    public String getCurrentWord() {
        // If only one possible word remains, return it.
        // if not/Otherwise, return placeholder or null.
        return null;
    }

    @Override
    public void setWordList(List<String> wordList) {
        // full dict and filtered wordList
        // current possible anf full dict copy
        this.fullDictionary = wordList;
        this.currentPossible = new ArrayList<>(wordList);
    }

    @Override
    public void setCurrentColumn(char c) {
        // 1. Increment currentColumn
        // 2. store character in guess array
        // 3. Fill grid[currentRow][currentColumn]
        currentColumn++;
        currentColumn = Math.min(currentColumn, columnCount - 1);
        guess[currentColumn] = c;
        grid[currentRow][currentColumn] = new WordleResponse(Character.toUpperCase(c), Color.WHITE, Color.BLACK);
    }

    @Override
    public void backspace() {
        // If currentColumn >= 0, clear last char and move back.
        if (currentColumn >= 0) {
            guess[currentColumn] = '\0';
            grid[currentRow][currentColumn] = null;
            currentColumn--;
        }
    }

    @Override
    public WordleResponse[] getCurrentRow() {
        // return the current rows responses
        return grid[Math.max(0, currentRow)];
    }

    @Override
    public int getCurrentRowNumber() {
        // return currentRow index
        return currentRow;
    }

    @Override
    public boolean setCurrentRow() {
        // 1. current possible by feedback pattern for this guess
        // 2. choose the largest 
        // 3. Update grid with that pattern
        // 4. Update currentPossible
        // 5. Increment currentRow, reset currentColumn
        return false;
    }

    @Override
    public WordleResponse[][] getWordleGrid() {
        //return game grid
        return grid;
    }

    @Override
    public int getMaximumRows() {
        return maximumRows;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public int getCurrentColumn() {
        return currentColumn;
    }

    @Override
    public int getTotalWordCount() {
        return fullDictionary.size();
    }

    @Override
    public Statistics getStatistics() {
        return stats;
    }

    @Override
    public void incrementTotalGamesPlayed() {
        //stats.incrementTotalGamesPlayed();
    }

    @Override
    public void addWordsGuessed(int rowNumber) {
        //stats.addWordsGuessed(rowNumber);
    }

    @Override
    public void incrementCurrentStreak() {
        // stats.incrementCurrentStreak();
    }

    @Override
    public void resetCurrentStreak() {
        // stats.resetCurrentStreak();
    }
}