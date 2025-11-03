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
	
		// determine mode
        String strat = "random";
        for (int i = 0; i < args.length -1; i++) {
        		if("-s".equals(args[i])) {
        			strat = args[i+1].trim().toLowerCase();
        		}
        }

        // chose model
        Model selectedModel;
        switch (strat) {
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
		// construct a new object and it close to creation
		new WordleFrame(model);
	}

}