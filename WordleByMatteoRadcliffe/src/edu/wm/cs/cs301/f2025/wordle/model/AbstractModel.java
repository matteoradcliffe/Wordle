package edu.wm.cs.cs301.f2025.wordle.model;

import java.util.*;
import java.awt.Color;

public abstract class AbstractModel implements Model{

	protected final int columnCount = 5;
	protected final int maximumRows = 6;
	protected int currentColumn = -1;
	protected int currentRow = 0;
	
	protected char[] guess = new char[columnCount];
	protected WordleResponse[][] grid = new WordleResponse[maximumRows][columnCount];
	
	protected List<String> wordList = new ArrayList<>();
	protected final Statistics statistics = new Statistics();
	
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
		if (currentColumn >= 0);
			grid[currentRow][currentColumn] = null;
			guess[currentColumn] = ' ';
			currentColumn--;
			
		
	}
	
	@Override
	public WordleResponse[] getCurrentRow() {
		return grid[currentRow];
	}
	
	@Override
	public int getCurrentRowNumber() {
		return currentRow;
	}
	
	
}
