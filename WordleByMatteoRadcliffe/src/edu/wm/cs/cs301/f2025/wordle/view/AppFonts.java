package edu.wm.cs.cs301.f2025.wordle.view;

import java.awt.Font;

/**
 * The AppFonts class represents the AppFonts component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class AppFonts {
	
	public static Font getTitleFont() {
		// Construct a new object—initialize and configure it close to creation for readability.
		return new Font("Dialog", Font.BOLD, 36);
	}
	
	public static Font getTextFont() {
		// Construct a new object—initialize and configure it close to creation for readability.
		return new Font("Dialog", Font.PLAIN, 16);
	}
	
	public static Font getFooterFont() {
		// Construct a new object—initialize and configure it close to creation for readability.
		return new Font("Dialog", Font.PLAIN, 12);
	}

}