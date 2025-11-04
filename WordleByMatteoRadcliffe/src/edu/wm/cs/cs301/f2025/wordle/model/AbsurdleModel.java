package edu.wm.cs.cs301.f2025.wordle.model;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;


public class AbsurdleModel extends AbstractModel{
    private List<String> currentPossible = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(AbsurdleModel.class.getName());
    
    @Override
    public void generateCurrentWord() {
        // Absurdle never commits to one word.
        // Keep current possible as all valid candidates initially.
    		currentPossible = new ArrayList<>(wordList);
    }

    @Override
    public void setCurrentWord(String word) {
        // Used only for testing may set current possible = [word].
    		currentPossible = new ArrayList<>();
    		currentPossible.add(word.toUpperCase());
    }

    @Override
    public String getCurrentWord() {
    		return currentPossible.size() == 1 ? currentPossible.get(0) : null;
        // If only one possible word remains, return it.
        // if not/Otherwise, return placeholder or null.
    }

    @Override
    public boolean setCurrentRow() {
    	
    		if (!enforceRulesBeforeSubmit()) {
    			return true;
    		}
    		String guessedWord = new String(guess).toUpperCase();
    		
    		Map<String, List<String>> buckets = new HashMap<>();
    		for (String c : currentPossible) {
    			String pattern = doPattern(guessedWord, c);
    			buckets.computeIfAbsent(pattern, h -> new ArrayList<>()).add(c);
    		}
    		
    		int maxSize = 0;
    		String chosen = null;
    		for (var entry : buckets.entrySet()) {
    			int size = entry.getValue().size();
    			if (size > maxSize) {
    				maxSize = size;
    				chosen = entry.getKey();
    			}
    		
    		}
    		
    		if (chosen == null) {
    		    chosen = "BBBBB";
    		}

    			
    		currentPossible = buckets.get(chosen);

    	        
    		for (int i = 0; i < columnCount; i++) {
    			char g = guessedWord.charAt(i);
    	         Color bg = AppColors.GRAY;
    	         if (chosen.charAt(i) == 'G') bg = AppColors.GREEN;
    	         else if (chosen.charAt(i) == 'Y') bg = AppColors.YELLOW;
    	         grid[currentRow][i] = new WordleResponse(g, bg, Color.WHITE);
    	            
    	    }
    	    currentRow++;
    	    currentColumn = -1;
    	    guess = new char[columnCount];
    	    LOG.info(String.format("Guess: %-10s | Pattern: %-5s | Remaining: %d words",guessedWord,(chosen == null ? "N/A" : chosen),currentPossible.size()));

    	    return currentRow < maximumRows;
   
    	}
    		
        // 1. current possible by feedback pattern for this guess
        // 2. choose the largest 
        // 3. Update grid with that pattern
        // 4. Update currentPossible
        // 5. Increment currentRow, reset currentColumn
        
    
    private String doPattern(String guess, String target) {
    		char[] pattern = new char[columnCount];
    		boolean[] used = new boolean[columnCount];

    		for (int i = 0; i < columnCount; i++) {
    			if (guess.charAt(i) == target.charAt(i)) {
    					pattern[i] = 'G'; 
    					used[i] = true;
    			}
    		}
    		for (int i = 0; i < columnCount; i++) {
    			if (pattern[i] == 'G') continue;
    			char g = guess.charAt(i);
    			boolean found = false;
    			for (int j = 0; j < columnCount; j++) {
    				if (!used[j] && target.charAt(j) == g) { 
    					used[j] = true; 
    					found = true; 
    					break; 
    				}
    			}
    			pattern[i] = found ? 'Y' : 'B';
    		}
    		return new String(pattern);
    	}
}

