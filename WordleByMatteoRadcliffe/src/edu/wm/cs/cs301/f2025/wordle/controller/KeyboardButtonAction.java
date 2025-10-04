package edu.wm.cs.cs301.f2025.wordle.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import edu.wm.cs.cs301.f2025.wordle.model.AppColors;
import edu.wm.cs.cs301.f2025.wordle.model.WordleModel;
import edu.wm.cs.cs301.f2025.wordle.model.WordleResponse;
import edu.wm.cs.cs301.f2025.wordle.view.StatisticsDialog;
import edu.wm.cs.cs301.f2025.wordle.view.WordleFrame;

/**
 * The KeyboardButtonAction class represents the KeyboardButtonAction component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class KeyboardButtonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private final WordleFrame view;
	
	private final WordleModel model;

	public KeyboardButtonAction(WordleFrame view, WordleModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	/**
     * actionPerformed method performs its core logic or handles UI actions as defined.
     * @param event parameter description
     */
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		String text = button.getActionCommand();
		// Multi-branch decision: clearer than nested if/else for discrete cases.
		switch (text) {
		// Handle a specific enum/value; prefer break unless fallthrough is intentional and documented.
		case "Enter":
			// Decision point: branch based on this condition—explain why it matters for state flow.
			if (model.getCurrentColumn() >= (model.getColumnCount() - 1)) {
				// Configure a property—group related setters so defaults are easy to audit.
				boolean moreRows = model.setCurrentRow();
				WordleResponse[] currentRow = model.getCurrentRow();
				int greenCount = 0;
				// Loop over a known range/collection; watch indices and ensure side effects are intentional.
				for (WordleResponse wordleResponse : currentRow) {
					// Configure a property—group related setters so defaults are easy to audit.
					view.setColor(Character.toString(wordleResponse.getChar()),
							wordleResponse.getBackgroundColor(), 
							wordleResponse.getForegroundColor());
					// Decision point: branch based on this condition—explain why it matters for state flow.
					if (wordleResponse.getBackgroundColor().equals(AppColors.GREEN)) {
						greenCount++;
					} 
				}
				
				// Decision point: branch based on this condition—explain why it matters for state flow.
				if (greenCount >= model.getColumnCount()) {
					view.repaintWordleGridPanel();
					model.getStatistics().incrementTotalGamesPlayed();
					int currentRowNumber = model.getCurrentRowNumber();
					model.getStatistics().addWordsGuessed(currentRowNumber);
					int currentStreak = model.getStatistics().getCurrentStreak();
					// Configure a property—group related setters so defaults are easy to audit.
					model.getStatistics().setCurrentStreak(++currentStreak);
					// Construct a new object—initialize and configure it close to creation for readability.
					new StatisticsDialog(view, model);
				} else if (!moreRows) {
					view.repaintWordleGridPanel();
					model.getStatistics().incrementTotalGamesPlayed();
					// Configure a property—group related setters so defaults are easy to audit.
					model.getStatistics().setCurrentStreak(0);
					// Construct a new object—initialize and configure it close to creation for readability.
					new StatisticsDialog(view, model);
				} else {
					view.repaintWordleGridPanel();
				}
			}
			break;
		// Handle a specific enum/value; prefer break unless fallthrough is intentional and documented.
		case "Backspace":
			model.backspace();
			view.repaintWordleGridPanel();
			break;
		// Default branch: ensures robustness if new values appear.
		default:
			// Configure a property—group related setters so defaults are easy to audit.
			model.setCurrentColumn(text.charAt(0));
			view.repaintWordleGridPanel();
			break;
		}
		
	}

}
