package edu.wm.cs.cs301.f2025.wordle.model.rules;

import edu.wm.cs.cs301.f2025.wordle.model.Model;

public interface AcceptanceRule {

	boolean isAcceptableGuess(Model model);
}
