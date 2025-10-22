package edu.wm.cs.cs301.f2025.wordle.controller;

import javax.swing.SwingUtilities;

import edu.wm.cs.cs301.f2025.wordle.model.Model;
import edu.wm.cs.cs301.f2025.wordle.model.WordleModel;
import edu.wm.cs.cs301.f2025.wordle.model.AbsurdleModel;
import edu.wm.cs.cs301.f2025.wordle.view.WordleFrame;
/**
 * The Wordle class represents the Wordle component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class Wordle implements Runnable {
	
	 /** selected model for the game */
    private final Model model;
    
	public static void main(String[] args) {
	
		// Determine mode
        String mode = (args != null && args.length > 0)? args[0].trim().toLowerCase(): "random";

        // Choose model
        Model selectedModel;
        switch (mode) {
            case "absurdle":
                selectedModel = new AbsurdleModel();
                System.out.println("Launching Absurdle mode...");
                break;
            case "random":
            default:
                selectedModel = new WordleModel();
                System.out.println("Launching standard Wordle mode...");
                break;
        }
 
        SwingUtilities.invokeLater(new Wordle(selectedModel));
	}
	
	/** Constructor that accepts chosen model */
    public Wordle(Model model) {
        this.model = model;
    }

	@Override
    /**
     * run method performs its core logic or handles UI actions as defined.
     */
	public void run() {
		// Construct a new object—initialize and configure it close to creation for readability.
		new WordleFrame(model);
	}

}