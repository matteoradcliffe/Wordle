package edu.wm.cs.cs301.f2025.wordle.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.f2025.wordle.model.Model;
/**
 * The WordleFrame class represents the WordleFrame component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class WordleFrame {
	
	private final JFrame frame;
	
	private final KeyboardPanel keyboardPanel;
	
	private final Model model;
	
	private final WordleGridPanel wordleGridPanel;

	public WordleFrame(Model model) {
		this.model = model;
		// Construct a new object—initialize and configure it close to creation for readability.
		this.keyboardPanel = new KeyboardPanel(this, model);
		int width = keyboardPanel.getPanel().getPreferredSize().width;
		// Construct a new object—initialize and configure it close to creation for readability.
		this.wordleGridPanel = new WordleGridPanel(this, model, width);
		this.frame = createAndShowGUI();
	}

	/**
     * createAndShowGUI method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JFrame createAndShowGUI() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JFrame frame = new JFrame("Wordle");
		// Configure a property—group related setters so defaults are easy to audit.
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// Configure a property—group related setters so defaults are easy to audit.
		frame.setJMenuBar(createMenuBar());
		// Configure a property—group related setters so defaults are easy to audit.
		frame.setResizable(false);
		// Construct a new object—initialize and configure it close to creation for readability.
		frame.addWindowListener(new WindowAdapter() {
			@Override
			/**
     * windowClosing method performs its core logic or handles UI actions as defined.
     * @param event parameter description
     */
			 public void windowClosing(WindowEvent event) {
				shutdown();
			}
		});
		
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		frame.add(createTitlePanel(), BorderLayout.NORTH);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		frame.add(wordleGridPanel, BorderLayout.CENTER);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		frame.add(keyboardPanel.getPanel(), BorderLayout.SOUTH);
		
		frame.pack();
		// Configure a property—group related setters so defaults are easy to audit.
		frame.setLocationByPlatform(true);
		// Configure a property—group related setters so defaults are easy to audit.
		frame.setVisible(true);
		
		System.out.println("Frame size: " + frame.getSize());
		
		return frame;
	}
	
	/**
     * createMenuBar method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JMenuBar createMenuBar() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JMenuBar menuBar = new JMenuBar();
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JMenu helpMenu = new JMenu("Help");
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		menuBar.add(helpMenu);
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JMenuItem instructionsItem = new JMenuItem("Instructions...");
		// Construct a new object—initialize and configure it close to creation for readability.
		instructionsItem.addActionListener(event -> new InstructionsDialog(this));
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		helpMenu.add(instructionsItem);
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JMenuItem aboutItem = new JMenuItem("About...");
		// Construct a new object—initialize and configure it close to creation for readability.
		aboutItem.addActionListener(event -> new AboutDialog(this));
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		helpMenu.add(aboutItem);
		
		return menuBar;
	}
	
	/**
     * createTitlePanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createTitlePanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new FlowLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		// Insert/update in a map; consider overwrite behavior for existing keys.
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		// Construct a new object—initialize and configure it close to creation for readability.
		actionMap.put("cancelAction", new CancelAction());
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JLabel label = new JLabel("Wordle");
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(AppFonts.getTitleFont());
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label);
		
		return panel;
	}
	
	/**
     * shutdown method performs its core logic or handles UI actions as defined.
     */
	public void shutdown() {
		model.getStatistics().writeStatistics();
		frame.dispose();
		System.exit(0);
	}
	
	/**
     * resetDefaultColors method performs its core logic or handles UI actions as defined.
     */
	public void resetDefaultColors() {
		keyboardPanel.resetDefaultColors();
	}
	
	/**
     * setColor method performs its core logic or handles UI actions as defined.
     * @param letter parameter description
     * @param backgroundColor parameter description
     * @param foregroundColor parameter description
     */
	public void setColor(String letter, Color backgroundColor, Color foregroundColor) {
		// Configure a property—group related setters so defaults are easy to audit.
		keyboardPanel.setColor(letter, backgroundColor, foregroundColor);
	}
	
	/**
     * repaintWordleGridPanel method performs its core logic or handles UI actions as defined.
     */
	public void repaintWordleGridPanel() {
		wordleGridPanel.repaint();
	}

	/**
     * getFrame method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	public JFrame getFrame() {
		return frame;
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		/**
     * actionPerformed method performs its core logic or handles UI actions as defined.
     * @param event parameter description
     */
		public void actionPerformed(ActionEvent event) {
			shutdown();
		}
		
	}

}