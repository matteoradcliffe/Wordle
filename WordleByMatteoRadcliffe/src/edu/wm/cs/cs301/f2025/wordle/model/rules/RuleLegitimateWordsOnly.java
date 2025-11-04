package edu.wm.cs.cs301.f2025.wordle.model.rules;

import edu.wm.cs.cs301.f2025.wordle.model.Model;

public class RuleLegitimateWordsOnly implements AcceptanceRule {
	
	private final AcceptanceRule innerRule;

    public RuleLegitimateWordsOnly(AcceptanceRule innerRule) {
        this.innerRule = innerRule;
    }

    @Override
    public boolean isAcceptableGuess(Model model) {
       
        if (!innerRule.isAcceptableGuess(model)) {
            return false;
        }

        String guessWord = new String(model.getGuess()).toUpperCase();
        return model.getWordList().contains(guessWord);
    }

}
