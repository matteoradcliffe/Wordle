package edu.wm.cs.cs301.f2025.wordle.model;

import java.awt.Color;

/**
 * The ColorResponse class represents the ColorResponse component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class ColorResponse {
	
	private final Color backgroundColor, foregroundColor;

	public ColorResponse(Color backgroundColor, Color foregroundColor) {
		this.backgroundColor = backgroundColor;
		this.foregroundColor = foregroundColor;
	}

    /**
     * getBackgroundColor method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

    /**
     * getForegroundColor method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public Color getForegroundColor() {
		return foregroundColor;
	}

}