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
	public void actionPerformed(ActionEvent event) {
	    JButton button = (JButton) event.getSource();
	    String text = button.getActionCommand();

	    switch (text) {
	        case "Enter":
	            processEnter();
	            break;
	        case "Backspace":
	            model.backspace();
	            view.repaintWordleGridPanel();
	            break;
	        default:
	            model.setCurrentColumn(text.charAt(0));
	            view.repaintWordleGridPanel();
	            break;
	    }
	}

	private void processEnter() {
	    if (model.getCurrentColumn() < model.getColumnCount() - 1) return;

	    boolean moreRows = model.setCurrentRow();
	    WordleResponse[] row = model.getCurrentRow();
	    int greens = updateKeyboardColors(row);

	    if (greens == model.getColumnCount()) {
	        handleWin();
	    } else if (!moreRows) {
	        handleLoss();
	    } else {
	        view.repaintWordleGridPanel();
	    }
	}

	private int updateKeyboardColors(WordleResponse[] row) {
	    int greens = 0;
	    for (WordleResponse r : row) {
	        view.setColor(String.valueOf(r.getChar()), r.getBackgroundColor(), r.getForegroundColor());
	        if (r.getBackgroundColor().equals(AppColors.GREEN)) greens++;
	    }
	    return greens;
	}

	private void handleWin() {
		view.repaintWordleGridPanel();
	    model.incrementTotalGamesPlayed();
	    model.addWordsGuessed(model.getCurrentRowNumber());
	    model.incrementCurrentStreak();
	    new StatisticsDialog(view, model);
	}

	private void handleLoss() {
		view.repaintWordleGridPanel();
	    model.incrementTotalGamesPlayed();
	    model.resetCurrentStreak();
	    new StatisticsDialog(view, model);
	}
}