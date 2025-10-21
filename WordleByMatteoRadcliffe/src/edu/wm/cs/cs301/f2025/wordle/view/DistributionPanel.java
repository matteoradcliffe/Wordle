package edu.wm.cs.cs301.f2025.wordle.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import edu.wm.cs.cs301.f2025.wordle.model.AppColors;
import edu.wm.cs.cs301.f2025.wordle.model.Model;

/**
 * The DistributionPanel class represents the DistributionPanel component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */

public class DistributionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	/** Field representing percentages. */
	private double[] percentages;
	
	/** Field representing counts. */
	private int[] counts;
	/** Field representing lastValue. */
	private int lastValue;
	
	private final Model model;

	public DistributionPanel(WordleFrame view, Model model) {
		this.model = model;
		calculatePercentages();
		// Construct a new object—initialize and configure it close to creation for readability.
		this.setPreferredSize(new Dimension(500, 200));
	}
	
	/**
     * calculatePercentages method performs its core logic or handles UI actions as defined.
     */
	private void calculatePercentages() {
		this.counts = new int[model.getMaximumRows()];
		
		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int value : model.getStatistics().getWordsGuessed()) {
			counts[value]++;
			lastValue = value;
		}
		
		int maxCount = 0;
		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int index = 0; index < model.getMaximumRows(); index++) {
			maxCount = Math.max(maxCount, counts[index]);
		}
		
		this.percentages = new double[model.getMaximumRows()];
		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int index = 0; index < model.getMaximumRows(); index++) {
			percentages[index] = (double) counts[index] / maxCount;
		}
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
	
		Font textFont = AppFonts.getTextFont();
		// Configure a property—group related setters so defaults are easy to audit.
		g2d.setFont(textFont);
		FontMetrics metrics = g2d.getFontMetrics(textFont);
		
		int margin = metrics.getHeight() / 3;
		int x = 20;
		int x1 = x + 20;
		int y = 20;
		int y1 = getWidth() - 30;
		int y2 = 20;
		int difference = y1 - y - y2;

		// Loop over a known range/collection; watch indices and ensure side effects are intentional.
		for (int index = 0; index < model.getMaximumRows(); index++) {
			String text = Integer.toString(index + 1);
			// Configure a property—group related setters so defaults are easy to audit.
			g2d.setColor(Color.BLACK);
			g2d.drawString(text, x, y + 2);

			// Decision point: branch based on this condition—explain why it matters for state flow.
			if (index == lastValue
					&& model.getStatistics().getCurrentStreak() > 0) {
				// Configure a property—group related setters so defaults are easy to audit.
				g2d.setColor(AppColors.GREEN);
			} else {
				// Configure a property—group related setters so defaults are easy to audit.
				g2d.setColor(AppColors.GRAY);
			}
			
			int pixelWidth = (int) (Math.round(percentages[index] * difference)
					+ y2);
			g2d.fillRect(x1, y - metrics.getHeight() + margin, pixelWidth,
					metrics.getHeight());
			// Configure a property—group related setters so defaults are easy to audit.
			g2d.setColor(Color.WHITE);
			text = String.format("%,d", counts[index]);
			int textWidth = metrics.stringWidth(text);
			g2d.drawString(Integer.toString(counts[index]),
					x1 + pixelWidth - textWidth - 6, y + 2);
			
			y += metrics.getHeight() + margin;
		}
		
	}

}