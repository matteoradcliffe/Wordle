package edu.wm.cs.cs301.f2025.wordle.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.f2025.wordle.model.WordleModel;

/**
 * The StatisticsDialog class represents the StatisticsDialog component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class StatisticsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final ExitAction exitAction;
	
	private final NextAction nextAction;
	
	private final WordleFrame view;
	
	private final WordleModel model;

	public StatisticsDialog(WordleFrame view, WordleModel model) {
		super(view.getFrame(), "Statistics", true);
		this.view = view;
		this.model = model;
		// Construct a new object—initialize and configure it close to creation for readability.
		this.exitAction = new ExitAction();
		// Construct a new object—initialize and configure it close to creation for readability.
		this.nextAction = new NextAction();
		
		add(createMainPanel(), BorderLayout.NORTH);
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
		JPanel panel = new JPanel(new BorderLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createTopPanel(), BorderLayout.NORTH);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createBottomPanel(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
     * createTopPanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createTopPanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new BorderLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createTitlePanel(), BorderLayout.NORTH);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createSummaryPanel(), BorderLayout.SOUTH);
		
		return panel;
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
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JLabel label = new JLabel("Statistics");
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(AppFonts.getTitleFont());
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label);
		
		return panel;
	}
	
	/**
     * createBottomPanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createBottomPanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new BorderLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createSubtitlePanel(), BorderLayout.NORTH);
		// Construct a new object—initialize and configure it close to creation for readability.
		panel.add(new DistributionPanel(view, model), BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
     * createSubtitlePanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createSubtitlePanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new FlowLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JLabel label = new JLabel("Guess Distribution");
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(AppFonts.getTitleFont());
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label);
		
		return panel;
	}
	
	/**
     * createSummaryPanel method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private JPanel createSummaryPanel() {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel(new GridLayout(0, 4));
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		int totalGamesPlayed = model.getStatistics().getTotalGamesPlayed();
		int currentStreak = model.getStatistics().getCurrentStreak();
		int longestStreak = model.getStatistics().getLongestStreak();
		List<Integer> wordsGuessed = model.getStatistics().getWordsGuessed();
		int percent = (wordsGuessed.size() * 1000 + 5) / (totalGamesPlayed * 10);
		
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createStatisticsPanel(totalGamesPlayed, "Played", ""));
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createStatisticsPanel(percent, "Win %", ""));
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createStatisticsPanel(currentStreak, "Current", "Streak"));
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(createStatisticsPanel(longestStreak, "Longest", "Streak"));
		
		return panel;
	}
	
	/**
     * createStatisticsPanel method performs its core logic or handles UI actions as defined.
     * @param value parameter description
     * @param line1 parameter description
     * @param line2 parameter description
     * @return result of the operation
     */
	private JPanel createStatisticsPanel(int value, String line1, String line2) {
		// Construct a new object—initialize and configure it close to creation for readability.
		JPanel panel = new JPanel();
		// Construct a new object—initialize and configure it close to creation for readability.
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		Font textFont = AppFonts.getTextFont();
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JLabel valueLabel = new JLabel(String.format("%,d", value));
		// Configure a property—group related setters so defaults are easy to audit.
		valueLabel.setFont(AppFonts.getTitleFont());
		// Configure a property—group related setters so defaults are easy to audit.
		valueLabel.setAlignmentX(CENTER_ALIGNMENT);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(valueLabel);
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JLabel label = new JLabel(line1);
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(textFont);
		// Configure a property—group related setters so defaults are easy to audit.
		label.setAlignmentX(CENTER_ALIGNMENT);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label);
		
		// Construct a new object—initialize and configure it close to creation for readability.
		label = new JLabel(line2);
		// Configure a property—group related setters so defaults are easy to audit.
		label.setFont(textFont);
		// Configure a property—group related setters so defaults are easy to audit.
		label.setAlignmentX(CENTER_ALIGNMENT);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(label);
		
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
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exitAction");
		// Insert/update in a map; consider overwrite behavior for existing keys.
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "nextAction");
		ActionMap actionMap = panel.getActionMap();
		// Insert/update in a map; consider overwrite behavior for existing keys.
		actionMap.put("nextAction", nextAction);
		// Insert/update in a map; consider overwrite behavior for existing keys.
		actionMap.put("exitAction", exitAction);
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JButton nextButton = new JButton("Next Word");
		nextButton.addActionListener(nextAction);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(nextButton);
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JButton exitButton = new JButton("Exit Wordle");
		exitButton.addActionListener(exitAction);
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(exitButton);
		
		Dimension nextDimension = nextButton.getPreferredSize();
		Dimension exitDimension = exitButton.getPreferredSize();
		int maxWidth = Math.max(nextDimension.width, exitDimension.width) + 10;
		// Construct a new object—initialize and configure it close to creation for readability.
		nextButton.setPreferredSize(new Dimension(maxWidth, nextDimension.height));
		// Construct a new object—initialize and configure it close to creation for readability.
		exitButton.setPreferredSize(new Dimension(maxWidth, exitDimension.height));
		
		return panel;
	}
	
	private class ExitAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		/**
     * actionPerformed method performs its core logic or handles UI actions as defined.
     * @param event parameter description
     */
		public void actionPerformed(ActionEvent event) {
			dispose();
			view.shutdown();
		}
		
	}
	
	private class NextAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		/**
     * actionPerformed method performs its core logic or handles UI actions as defined.
     * @param event parameter description
     */
		public void actionPerformed(ActionEvent event) {
			dispose();
			model.initialize();
			view.repaintWordleGridPanel();
			view.resetDefaultColors();
		}
		
	}

}