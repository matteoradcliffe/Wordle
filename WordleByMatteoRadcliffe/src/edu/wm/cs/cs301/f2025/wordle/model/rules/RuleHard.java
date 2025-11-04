package edu.wm.cs.cs301.f2025.wordle.model.rules;

import edu.wm.cs.cs301.f2025.wordle.model.Model;
import edu.wm.cs.cs301.f2025.wordle.model.WordleResponse;
import java.util.HashSet;
import java.util.Set;
import edu.wm.cs.cs301.f2025.wordle.model.AppColors;
import java.util.*;

public class RuleHard implements AcceptanceRule {

	private final AcceptanceRule innerRule;

    public RuleHard(AcceptanceRule innerRule) {
        this.innerRule = innerRule;
    }

    @Override
    public boolean isAcceptableGuess(Model model) {
        
        if (!innerRule.isAcceptableGuess(model)) {
            return false;
        }
        
        WordleResponse[][] grid = model.getWordleGrid();
        int rowsDone = model.getCurrentRowNumber();

        Set<Character> yellowLetters = new HashSet<>();
        Set<Character> grayLetters = new HashSet<>();
        Map<Integer, Character> greenPositions = new HashMap<>();

        for (int r = 0; r < rowsDone; r++) {
            WordleResponse[] row = grid[r];
            if (row == null) continue;
            for (int c = 0; c < row.length; c++) {
                WordleResponse tile = row[c];
                if (tile == null) continue;
                char letter = Character.toUpperCase(tile.getChar());
                if (AppColors.GREEN.equals(tile.getBackgroundColor())) {
                    greenPositions.put(c, letter);
                } else if (AppColors.YELLOW.equals(tile.getBackgroundColor())) {
                    yellowLetters.add(letter);
                } else if (AppColors.GRAY.equals(tile.getBackgroundColor())) {
                    grayLetters.add(letter);
                }
            }
        }
        char[] guess = model.getGuess();
        
        for (Map.Entry<Integer, Character> entry : greenPositions.entrySet()) {
            int col = entry.getKey();
            char required = entry.getValue();
            if (col >= guess.length || guess[col] != required) {
                return false;
            }
        }
        
        for (char g : guess) {
            if (grayLetters.contains(g)) {
                return false;
            }
        }
        
        for (char y : yellowLetters) {
            boolean foundElsewhere = false;
            for (int c = 0; c < guess.length; c++) {
                if (guess[c] == y && (!greenPositions.containsKey(c))) {
                    foundElsewhere = true;
                    break;
                }
            }
            if (!foundElsewhere) {
                return false;
            }
        }

      
        return true;
    }
}
