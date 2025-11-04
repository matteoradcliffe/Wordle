package edu.wm.cs.cs301.f2025.wordle.model.rules;

import edu.wm.cs.cs301.f2025.wordle.model.Model;

public class RuleBasic implements AcceptanceRule {
	@Override
    public boolean isAcceptableGuess(Model model) {
        
        char[] guess = model.getGuess(); 
        for (char c : guess) {
            if (c == '\0' || c == ' ') {
                return false; 
            }
        }
        return true;
    }

}
