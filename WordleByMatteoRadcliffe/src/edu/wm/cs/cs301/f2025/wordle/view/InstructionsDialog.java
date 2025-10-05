package edu.wm.cs.cs301.f2025.wordle.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

/**
 * The InstructionsDialog class represents the InstructionsDialog component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class InstructionsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final CancelAction cancelAction;
	
	/** Field representing editorPane. */
	private JEditorPane editorPane;
	
	public InstructionsDialog(WordleFrame view) {
		super(view.getFrame(), "Instructions", true);
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
		JPanel panel = new JPanel(new BorderLayout());
		// Configure a property—group related setters so defaults are easy to audit.
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		URL url = InstructionsDialog.class.getResource("/resources/instructions.htm");
		
		// Construct a new object—initialize and configure it close to creation for readability.
		editorPane = new JEditorPane();
		// Configure a property—group related setters so defaults are easy to audit.
		editorPane.setEditable(false);
		// Configure a property—group related setters so defaults are easy to audit.
		editorPane.setContentType("text/html");
		try {
			// Configure a property—group related setters so defaults are easy to audit.
			editorPane.setPage(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Construct a new object—initialize and configure it close to creation for readability.
		JScrollPane scrollPane = new JScrollPane(editorPane);
		// Construct a new object—initialize and configure it close to creation for readability.
		scrollPane.setPreferredSize(new Dimension(600, 480));
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		panel.add(scrollPane, BorderLayout.CENTER);
		
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