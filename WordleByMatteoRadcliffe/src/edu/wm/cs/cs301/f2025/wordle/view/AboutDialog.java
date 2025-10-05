package edu.wm.cs.cs301.f2025.wordle.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 * The AboutDialog class represents the AboutDialog component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class AboutDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private final CancelAction cancelAction;

	public AboutDialog(WordleFrame view) {
		super(view.getFrame(), "About", true);
		// Construct a new object—initialize and configure it close to creation for readability.
		this.cancelAction = new CancelAction();
		
		add(createMainPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(view.getFrame());
		setVisible(true);
	}
	
	/**
     * createMainPanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createMainPanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new GridBagLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		Font titleFont = AppFonts.getTitleFont();
		Font textFont = AppFonts.getTextFont();
		
		// Construct a new object—initialize and configure it close to creation for readability.
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// Construct a new object—initialize and configure it close to creation for readability.
		gbc.insets = new Insets(0, 5, 5, 30);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		// Construct a new object—initialize and configure it close to creation for readability.
		JLabel label = new JLabel("About Wordle");
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(titleFont);
		// Configure a property—group related setters so defaults are easy to audit.
		label.setHorizontalAlignment(JLabel.CENTER);
		Color backgroundColor = label.getBackground();
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label, gbc);
		
		gbc.gridy++;
		String text = "Wordle was created by software engineer "
				+ "and former Reddit employee, Josh Wardle, and "
				+ "was launched in October 2021.";
		// Construct a new object—initialize and configure it close to creation for readability.
		JTextArea textArea = new JTextArea(4, 25);
		// Configure a property—group related setters so defaults are easy to audit.
		textArea.setBackground(backgroundColor);
		// Configure a property—group related setters so defaults are easy to audit.
		textArea.setEditable(false);
		// Configure a property—group related setters so defaults are easy to audit.
		textArea.setFont(textFont);
		// Configure a property—group related setters so defaults are easy to audit.
		textArea.setLineWrap(true);
		// Configure a property—group related setters so defaults are easy to audit.
		textArea.setText(text);
		// Configure a property—group related setters so defaults are easy to audit.
		textArea.setWrapStyleWord(true);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(textArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy++;
		// Construct a new object—initialize and configure it close to creation for readability.
		label = new JLabel("Author:");
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(textFont);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label, gbc);
		
		gbc.gridx++;
		// Construct a new object—initialize and configure it close to creation for readability.
		label = new JLabel("Gilbert G. Le Blanc");
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(textFont);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		// Construct a new object—initialize and configure it close to creation for readability.
		label = new JLabel("Date Created:");
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(textFont);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label, gbc);
		
		gbc.gridx++;
		// Construct a new object—initialize and configure it close to creation for readability.
		label = new JLabel("31 March 2022");
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(textFont);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		// Construct a new object—initialize and configure it close to creation for readability.
		label = new JLabel("Version:");
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(textFont);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label, gbc);
		
		gbc.gridx++;
		// Construct a new object—initialize and configure it close to creation for readability.
		label = new JLabel("1.0");
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(textFont);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label, gbc);
		
		return panel;
	}
	/**
     * createButtonPanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createButtonPanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new FlowLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		// Insert/update in a map; consider overwrite behavior for existing keys.
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		// Insert/update in a map; consider overwrite behavior for existing keys.
		actionMap.put("cancelAction", cancelAction);
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JButton button = new JButton("Cancel");
		button.addActionListener(cancelAction);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(button);
		
		return panel;
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		/**
     * actionPerformed method performs its core logic or handles UI actions as defined.
     * @param event parameter description
     */
		public void actionPerformed(ActionEvent event) {
			dispose();
		}
		
	}

}