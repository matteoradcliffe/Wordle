package edu.wm.cs.cs301.f2025.wordle.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.f2025.wordle.controller.KeyboardButtonAction;
import edu.wm.cs.cs301.f2025.wordle.model.AppColors;
import edu.wm.cs.cs301.f2025.wordle.model.Model;

/**
 * The KeyboardPanel class represents the KeyboardPanel component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class KeyboardPanel {
	
	private JLabel totalLabel;

	private int buttonIndex, buttonCount;

	private final JButton[] buttons;

	private final JPanel panel;

	private final KeyboardButtonAction action;

	private final Model model;

	public KeyboardPanel(WordleFrame view, Model model) {
		this.model = model;
		this.buttonIndex = 0;
		this.buttonCount = firstRow().length + secondRow().length
				+ thirdRow().length;
		this.buttons = new JButton[buttonCount];
		// Construct a new object—initialize and configure it close to creation for readability.
		this.action = new KeyboardButtonAction(view, model);
		this.panel = createMainPanel();
	}

	/**
     * createMainPanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createMainPanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new GridLayout(0, 1, 0, 0));
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));

		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createQPanel());
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createAPanel());
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createZPanel());
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createTotalPanel());

		return panel;
	}

	/**
     * createQPanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createQPanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new FlowLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Font textfont = AppFonts.getTextFont();

		String[] letters = firstRow();

		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int index = 0; index < letters.length; index++) {
			// Construct a new object—initialize and configure it close to creation for readability.
			JButton button = new JButton(letters[index]);
			setKeyBinding(button, letters[index]);
			button.addActionListener(action);
			// Configure a property—group related setters so defaults are easy to audit.
			button.setFont(textfont);
			buttons[buttonIndex++] = button;
			// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
			panel.add(button);
		}

		return panel;
	}

	/**
     * firstRow method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private String[] firstRow() {
		String[] letters = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
				"Backspace" };
		return letters;
	}

	/**
     * createAPanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createAPanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new FlowLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Font textfont = AppFonts.getTextFont();

		String[] letters = secondRow();

		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int index = 0; index < letters.length; index++) {
			// Construct a new object—initialize and configure it close to creation for readability.
			JButton button = new JButton(letters[index]);
			setKeyBinding(button, letters[index]);
			button.addActionListener(action);
			// Configure a property—group related setters so defaults are easy to audit.
			button.setFont(textfont);
			buttons[buttonIndex++] = button;
			// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
			panel.add(button);
		}

		return panel;
	}

	/**
     * secondRow method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private String[] secondRow() {
		String[] letters = { "A", "S", "D", "F", "G", "H", "J", "K", "L",
				"Enter" };
		return letters;
	}

	/**
     * createZPanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createZPanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new FlowLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Font textfont = AppFonts.getTextFont();

		String[] letters = thirdRow();

		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int index = 0; index < letters.length; index++) {
			// Construct a new object—initialize and configure it close to creation for readability.
			JButton button = new JButton(letters[index]);
			setKeyBinding(button, letters[index]);
			button.addActionListener(action);
			// Configure a property—group related setters so defaults are easy to audit.
			button.setFont(textfont);
			buttons[buttonIndex++] = button;
			// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
			panel.add(button);
		}

		return panel;
	}

	/**
     * thirdRow method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private String[] thirdRow() {
		String[] letters = { "Z", "X", "C", "V", "B", "N", "M" };
		return letters;
	}

	/**
     * setKeyBinding method performs its core logic or handles UI actions as defined.
     * @param button parameter description
     * @param text parameter description
     */
	private void setKeyBinding(JButton button, String text) {
		InputMap inputMap = button.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		// Decision point: branch based on this condition—explain why it matters for state flow.
		if (text.equalsIgnoreCase("Backspace")) {
			// Insert/update in a map; consider overwrite behavior for existing keys.
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0),
					"action");
		} else {
			// Insert/update in a map; consider overwrite behavior for existing keys.
			inputMap.put(KeyStroke.getKeyStroke(text.toUpperCase()), "action");
		}
		ActionMap actionMap = button.getActionMap();
		// Insert/update in a map; consider overwrite behavior for existing keys.
		actionMap.put("action", action);
	}

	/**
     * createTotalPanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createTotalPanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new FlowLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Font footerFont = AppFonts.getFooterFont();

		String text = String.format("%,d", model.getTotalWordCount()) +" possible " + model.getColumnCount() + "-letter words!";
		// Construct a new object—initialize and configure it close to creation for readability.
		totalLabel = new JLabel(text);
	    totalLabel.setFont(footerFont);
	    panel.add(totalLabel);

		return panel;
	}
	
	private int colorRank(Color c) {
	    if (c == null) return 0;
	    if (AppColors.GREEN.equals(c))  return 3;
	    if (AppColors.YELLOW.equals(c)) return 2;
	    if (AppColors.GRAY.equals(c))   return 1;
	    return 0;
	}
	
	private Color bestTextOn(Color bg) {
	    if (bg == null) return Color.BLACK;
	    double r = bg.getRed() / 255.0;
	    double g = bg.getGreen() / 255.0;
	    double b = bg.getBlue() / 255.0;
	    double luminance = 0.2126 * r + 0.7152 * g + 0.0722 * b;
	    return luminance < 0.5 ? Color.WHITE : Color.BLACK;
	}

	public void setColor(String letter, Color backgroundColor,
			Color foregroundColor) {
		for (JButton button : buttons) {
	        if (button.getActionCommand().equals(letter)) {
	            Color currentBg = button.getBackground();
	            // only upgrade if the new color is stronger
	            if (colorRank(backgroundColor) >= colorRank(currentBg)) {
	                button.setOpaque(true);
	                button.setContentAreaFilled(true);
	                button.setFocusPainted(false);
	                button.setBackground(backgroundColor);
	                button.setForeground(bestTextOn(backgroundColor));
	                button.repaint();
	            }
	            break;
	        }
	    }
	}

	/**
     * resetDefaultColors method performs its core logic or handles UI actions as defined.
     */
	public void resetDefaultColors() {
		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (JButton button : buttons) {
			// Configure a property—group related setters so defaults are easy to audit.
			button.setBackground(null);
			// Configure a property—group related setters so defaults are easy to audit.
			button.setForeground(null);
		}
	}

	/**
     * getPanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public JPanel getPanel() {
		return panel;
	}
	
	public void updateTotalLabel() {
	    String text = String.format("%,d", model.getTotalWordCount());
	    if (model.getClass().getSimpleName().equals("AbsurdleModel")) {
	        text += " possible " + model.getColumnCount() + "-letter words Left!";
	    } else {
	        text += " possible " + model.getColumnCount() + "-Letter words!";
	    }
	    totalLabel.setText(text);
	    totalLabel.repaint();
	    
	}

}