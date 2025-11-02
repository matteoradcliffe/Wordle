package edu.wm.cs.cs301.f2025.wordle.model;

import java.util.*;

public abstract class AbstractModel implements Model{

	protected final int columnCount = 5;
	protected final int maximumRows = 6;
	protected int currentColumn = -1;
	protected int curentRow = 0;
	
	protected char[] guess = new char[columnCount];
	protected WordleResponse[][] grid = new WordleResponse[maximumRows][columnCount];
	
	protected List<String> wordlist = new ArrayList<>();
	protected final Statistics statistics = new Statistics();
}
