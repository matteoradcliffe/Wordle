package edu.wm.cs.cs301.f2025.wordle.controller;

import javax.swing.SwingUtilities;

import edu.wm.cs.cs301.f2025.wordle.model.Model;
import edu.wm.cs.cs301.f2025.wordle.model.WordleModel;
import edu.wm.cs.cs301.f2025.wordle.model.AbsurdleModel;
import edu.wm.cs.cs301.f2025.wordle.view.WordleFrame;
import edu.wm.cs.cs301.f2025.wordle.model.AbstractModel;
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
        boolean hard = false;
        boolean wordsOnly = false;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s":
                    if (i + 1 < args.length) {
                        strat = args[++i].trim().toLowerCase();
                    }
                    break;
                case "-h":
                    hard = true;
                    break;
                case "-wo":
                    wordsOnly = true;
                    break;
                default:
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
        edu.wm.cs.cs301.f2025.wordle.model.rules.AcceptanceRule chain = new edu.wm.cs.cs301.f2025.wordle.model.rules.RuleBasic();
        
        if (wordsOnly) {
            chain = new edu.wm.cs.cs301.f2025.wordle.model.rules.RuleLegitimateWordsOnly(chain);
        }
        if (hard) {
            chain = new edu.wm.cs.cs301.f2025.wordle.model.rules.RuleHard(chain);
        }
        
        if (selectedModel instanceof AbstractModel) {
            ((AbstractModel) selectedModel).setAcceptanceRule(chain);
        }
        selectedModel.setWordList(java.util.Arrays.asList("APPLE", "GRAPE", "LEMON", "BERRY", "MANGO"));
        selectedModel.initialize();
        
 
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