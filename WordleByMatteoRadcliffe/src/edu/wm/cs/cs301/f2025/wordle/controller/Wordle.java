package edu.wm.cs.cs301.f2025.wordle.controller;

import javax.swing.SwingUtilities;

import edu.wm.cs.cs301.f2025.wordle.model.WordleModel;
import edu.wm.cs.cs301.f2025.wordle.view.WordleFrame;

public class Wordle implements Runnable {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Wordle());
	}

	@Override
	public void run() {
		new WordleFrame(new WordleModel());
	}

}
