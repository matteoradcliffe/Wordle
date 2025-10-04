package edu.wm.cs.cs301.f2025.wordle.model;

import java.awt.Color;

/**
 * The WordleResponse class represents the WordleResponse component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class WordleResponse {
	
	private final char c;
	
	private final ColorResponse colorResponse;

	public WordleResponse(char c, Color backgroundColor, Color foregroundColor) {
		this.c = c;
		// Construct a new object—initialize and configure it close to creation for readability.
		this.colorResponse = new ColorResponse(backgroundColor, foregroundColor);
	}

    /**
     * getChar method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public char getChar() {
		return c;
	}

    /**
     * getBackgroundColor method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public Color getBackgroundColor() {
		return colorResponse.getBackgroundColor();
	}

    /**
     * getForegroundColor method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public Color getForegroundColor() {
		return colorResponse.getForegroundColor();
	}
	
}