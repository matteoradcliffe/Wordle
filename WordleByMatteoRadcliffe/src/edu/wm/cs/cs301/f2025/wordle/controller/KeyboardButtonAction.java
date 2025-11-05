package edu.wm.cs.cs301.f2025.wordle.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import edu.wm.cs.cs301.f2025.wordle.model.AppColors;
import edu.wm.cs.cs301.f2025.wordle.model.Model;
import edu.wm.cs.cs301.f2025.wordle.model.WordleResponse;
import edu.wm.cs.cs301.f2025.wordle.view.StatisticsDialog;
import edu.wm.cs.cs301.f2025.wordle.view.WordleFrame;
import edu.wm.cs.cs301.f2025.wordle.view.KeyboardPanel;

/**
 * The KeyboardButtonAction class represents the KeyboardButtonAction component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class KeyboardButtonAction extends AbstractAction {

	
	private final WordleFrame view;
	
	private final Model model;
	

	public KeyboardButtonAction(WordleFrame view, Model model) {
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
	    
	    char[] guess = model.getGuess();
	    boolean hasEmpty = false;
	    for (char c : guess) {
	        if (c == '\0' || c == ' ') {
	            hasEmpty = true;
	            break;
	        }
	    }
	    if (hasEmpty) {
	        System.out.println("Cannot submit: incomplete guess");
	        return;
	    }

	    boolean moreRows = model.setCurrentRow();
	    WordleResponse[] row = model.getCurrentRow();
	    if (row == null) return;

	    int greens = updateKeyboardColors(row);

	   
	    if (greens == model.getColumnCount()) {
	        handleWin();
	    } else if (!moreRows) {
	        handleLoss();
	    } else {
	        view.repaintWordleGridPanel();
	        view.getKeyboardPanel().updateTotalLabel();
	    }
	}

	
	private int updateKeyboardColors(WordleResponse[] row) {
	    int greens = 0;
	    if (row == null) return greens;
	    
	    for (WordleResponse r : row) {
	    		if (r == null) continue;
	        view.setColor(String.valueOf(r.getChar()), r.getBackgroundColor(), r.getForegroundColor());
	        if (AppColors.GREEN.equals(r.getBackgroundColor())) greens++;
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