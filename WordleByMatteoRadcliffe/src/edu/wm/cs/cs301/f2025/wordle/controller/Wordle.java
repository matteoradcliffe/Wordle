package edu.wm.cs.cs301.f2025.wordle.controller;

import javax.swing.SwingUtilities;

import edu.wm.cs.cs301.f2025.wordle.model.WordleModel;
import edu.wm.cs.cs301.f2025.wordle.view.WordleFrame;

/**
 * The Wordle class represents the Wordle component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class Wordle implements Runnable {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Wordle());
	}

	@Override
    /**
     * run method performs its core logic or handles UI actions as defined.
     */
	public void run() {
		new WordleFrame(new WordleModel());
	}

}