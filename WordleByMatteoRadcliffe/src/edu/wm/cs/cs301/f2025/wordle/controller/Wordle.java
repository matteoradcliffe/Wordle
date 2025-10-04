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
		// Construct a new object—initialize and configure it close to creation for readability.
		SwingUtilities.invokeLater(new Wordle());
	}

	@Override
    /**
     * run method performs its core logic or handles UI actions as defined.
     */
	public void run() {
		// Construct a new object—initialize and configure it close to creation for readability.
		new WordleFrame(new WordleModel());
	}

}