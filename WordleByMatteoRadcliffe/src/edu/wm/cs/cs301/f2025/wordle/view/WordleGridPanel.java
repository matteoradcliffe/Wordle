package edu.wm.cs.cs301.f2025.wordle.view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import edu.wm.cs.cs301.f2025.wordle.model.AppColors;
import edu.wm.cs.cs301.f2025.wordle.model.Model;
import edu.wm.cs.cs301.f2025.wordle.model.WordleResponse;

/**
 * The WordleGridPanel class represents the WordleGridPanel component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class WordleGridPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final int topMargin, leftMargin, letterWidth;

	private final Insets insets;

	private final Rectangle[][] grid;

	private final Model model;

	public WordleGridPanel(WordleFrame view, Model model, int width) {
		this.model = model;
		this.topMargin = 0;
		this.letterWidth = 64;
		// Construct a new object—initialize and configure it close to creation for readability.
		this.insets = new Insets(0, 6, 6, 6);

		int wordWidth = (letterWidth + insets.right) * model.getColumnCount();
		this.leftMargin = (width - wordWidth) / 2;
		int height = (letterWidth + insets.bottom) * model.getMaximumRows()
				+ 2 * topMargin;
		// Construct a new object—initialize and configure it close to creation for readability.
		this.setPreferredSize(new Dimension(width, height));

		this.grid = calculateRectangles();
	}

	/**
     * calculateRectangles method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */
	private Rectangle[][] calculateRectangles() {
		Rectangle[][] grid = new Rectangle[model.getMaximumRows()][model
				.getColumnCount()];

		int x = leftMargin;
		int y = topMargin;

		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int row = 0; row < model.getMaximumRows(); row++) {
			// Loop over a known range/collection; watch indices and ensure side effects are intentional.
			for (int column = 0; column < model.getColumnCount(); column++) {
				// Construct a new object—initialize and configure it close to creation for readability.
				grid[row][column] = new Rectangle(x, y, letterWidth,
						letterWidth);
				x += letterWidth + insets.right;
			}
			x = leftMargin;
			y += letterWidth + insets.bottom;
		}

		return grid;
	}

	@Override
	/**
     * paintComponent method performs its core logic or handles UI actions as defined.
     * @param g parameter description
     */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		// Configure a property—group related setters so defaults are easy to audit.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// Configure a property—group related setters so defaults are easy to audit.
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		Font titleFont = AppFonts.getTitleFont();
		WordleResponse[][] wordleGrid = model.getWordleGrid();
		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int row = 0; row < grid.length; row++) {
			// Loop over a known range/collection; watch indices and ensure side effects are intentional.
			for (int column = 0; column < grid[row].length; column++) {
				Rectangle r = grid[row][column];
				WordleResponse wordleResponse = wordleGrid[row][column];
				drawOutline(g2d, r);
				drawWordleResponse(g2d, wordleResponse, r, titleFont);
			}
		}
	}

	/**
     * drawOutline method performs its core logic or handles UI actions as defined.
     * @param g2d parameter description
     * @param r parameter description
     */
	private void drawOutline(Graphics2D g2d, Rectangle r) {
		int x = r.x + 1;
		int y = r.y + 1;
		int width = r.width - 2;
		int height = r.height - 2;
		// Configure a property—group related setters so defaults are easy to audit.
		g2d.setColor(AppColors.OUTLINE);
		// Construct a new object—initialize and configure it close to creation for readability.
		g2d.setStroke(new BasicStroke(3f));
		g2d.drawLine(x, y, x + width, y);
		g2d.drawLine(x, y + height, x + width, y + height);
		g2d.drawLine(x, y, x, y + height);
		g2d.drawLine(x + width, y, x + width, y + height);
	}

	private void drawWordleResponse(Graphics2D g2d,
			WordleResponse wordleResponse, Rectangle r, Font titleFont) {
		// Decision point: branch based on this condition—explain why it matters for state flow.
		if (wordleResponse != null) {
			// Configure a property—group related setters so defaults are easy to audit.
			g2d.setColor(wordleResponse.getBackgroundColor());
			g2d.fillRect(r.x, r.y, r.width, r.height);
			// Configure a property—group related setters so defaults are easy to audit.
			g2d.setColor(wordleResponse.getForegroundColor());
			drawCenteredString(g2d,
					Character.toString(wordleResponse.getChar()), r, titleFont);
		}
	}


	private void drawCenteredString(Graphics2D g2d, String text, Rectangle rect,
			Font font) {
		FontMetrics metrics = g2d.getFontMetrics(font);
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2)
				+ metrics.getAscent();

		// Configure a property—group related setters so defaults are easy to audit.
		g2d.setFont(font);
		g2d.drawString(text, x, y);
	}

}