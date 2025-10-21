package edu.wm.cs.cs301.f2025.wordle.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wm.cs.cs301.f2025.wordle.model.Model;

/**
 * The ReadWordsRunnable class represents the ReadWordsRunnable component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class ReadWordsRunnable implements Runnable {

	private final static Logger LOGGER =
			Logger.getLogger(ReadWordsRunnable.class.getName());

	private final Model model;

	public ReadWordsRunnable(Model model) {
		// Configure a property—group related setters so defaults are easy to audit.
		LOGGER.setLevel(Level.INFO);

		try {
			// Construct a new object—initialize and configure it close to creation for readability.
			FileHandler fileTxt = new FileHandler("./logging.txt");
			LOGGER.addHandler(fileTxt);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.model = model;
	}

	@Override
	/**
     * run method performs its core logic or handles UI actions as defined.
     */
	public void run() {
		List<String> wordlist;

		try {
			wordlist = createWordList();
			LOGGER.info("Created word list of " + wordlist.size() + " words.");
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			wordlist = new ArrayList<>();
		}

		// Configure a property—group related setters so defaults are easy to audit.
		model.setWordList(wordlist);
		model.generateCurrentWord();
	}

	/**
     * deliverInputStream method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private InputStream deliverInputStream() {
		String text = "/resources/usa.txt";
		// Original code
		/*
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream stream = loader.getResourceAsStream(text);
		*/
		// https://stackoverflow.com/questions/68314700/why-java-cannot-read-same-resource-file-when-module-info-is-added
		
		InputStream stream = Wordle.class.getResourceAsStream(text);
		
		// Decision point: branch based on this condition—explain why it matters for state flow.
		if (null == stream) {
			System.out.println("Failed to open stream with " + text);
			System.exit(0);
		}
		else 
			System.out.println("Successfully opened inputstream for " + text);
		
		return stream;
	}
	/**
     * createWordList method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private List<String> createWordList() throws IOException {
		int minimum = model.getColumnCount();

		List<String> wordlist = new ArrayList<>();

		
		InputStream stream = deliverInputStream();

		// Construct a new object—initialize and configure it close to creation for readability.
		BufferedReader reader = new BufferedReader(
				// Construct a new object—initialize and configure it close to creation for readability.
				new InputStreamReader(stream));
		String line = reader.readLine();
		// Conditional loop: exits when the condition flips—guard against infinite loops.
		while (line != null) {
			line = line.trim();
			// Decision point: branch based on this condition—explain why it matters for state flow.
			if (line.length() == minimum) {
				// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
				wordlist.add(line);
			}
			line = reader.readLine();
		}
		reader.close();

		return wordlist;
	}
	
}