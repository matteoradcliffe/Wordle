package edu.wm.cs.cs301.f2025.wordle.model;

import java.util.*;

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
	
}
