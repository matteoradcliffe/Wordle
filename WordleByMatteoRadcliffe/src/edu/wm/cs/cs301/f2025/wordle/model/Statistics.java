package edu.wm.cs.cs301.f2025.wordle.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.*;
import java.util.logging.*;

/**
 * The Statistics class represents the Statistics component of the Wordle application.
 * It is responsible for handling its respective UI or logic functionality within the game.
 */
public class Statistics {
	
	
	private int currentStreak, longestStreak, totalGamesPlayed;
	
	/** Field representing wordsGuessed. */
	private List<Integer> wordsGuessed;
	
	private String path, log;
	

	private static final Logger LOG = Logger.getLogger(Statistics.class.getName());
	private final ExecutorService ioExecutor = Executors.newSingleThreadExecutor(r -> {
	    Thread t = new Thread(r, "statistics-io");
	    t.setDaemon(true);
	    return t;
	});
	private final CountDownLatch loadLatch = new CountDownLatch(1);
	private volatile boolean loaded = false;
	
	public Statistics() {
		this.wordsGuessed = new ArrayList<>();
		String fileSeparator = System.getProperty("file.separator");
		this.path = System.getProperty("user.home") + fileSeparator + "Wordle";
		this.log = fileSeparator + "statistics.log";
		ioExecutor.submit(this::readStatisticsAsync);
	}
	
	/**
     * readStatistics method performs its core logic or handles UI actions as defined.
     */
	private void readStatistics() {
		try {
			// Construct a new object—initialize and configure it close to creation for readability.
			BufferedReader br = new BufferedReader(new FileReader(path + log));
			this.currentStreak = Integer.valueOf(br.readLine().trim());
			this.longestStreak = Integer.valueOf(br.readLine().trim());
			this.totalGamesPlayed = Integer.valueOf(br.readLine().trim());
			int totalWordsGuessed = Integer.valueOf(br.readLine().trim());
			
			// Loop over a known range/collection; watch indices and ensure side effects are intentional.
			for (int index = 0; index < totalWordsGuessed; index++) {
				// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
				wordsGuessed.add(Integer.valueOf(br.readLine().trim()));
			}
			br.close();
		} catch (FileNotFoundException e) {
			this.currentStreak = 0;
			this.longestStreak = 0;
			this.totalGamesPlayed = 0;
		} catch (IOException e) {
			LOG.log(Level.WARNING, "Failed to read statistics file.", e);
		}
	}
	
	
	private void readStatisticsAsync() {
	    LOG.info(() -> "Starting async load of statistics from " + path + log);
	    try {
	        readStatistics(); 
	        loaded = true;
	        LOG.info("Statistics loaded successfully.");
	    } catch (Exception e) {
	        LOG.log(Level.WARNING, "Error loading statistics asynchronously", e);
	    } finally {
	        loadLatch.countDown(); 
	    }
	}
	
	
	/**
     * writeStatistics method performs its core logic or handles UI actions as defined.
     */
	public void writeStatistics() {
		try {
		    loadLatch.await(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		    Thread.currentThread().interrupt();
		}
		LOG.info("Writing statistics data to file...");
		try {
			// Construct a new object—initialize and configure it close to creation for readability.
			File file = new File(path);
			file.mkdir();
			// Construct a new object—initialize and configure it close to creation for readability.
			file = new File(path + log);
			file.createNewFile();

			// Construct a new object—initialize and configure it close to creation for readability.
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(Integer.toString(currentStreak));
			bw.write(System.lineSeparator());
			bw.write(Integer.toString(longestStreak));
			bw.write(System.lineSeparator());
			bw.write(Integer.toString(totalGamesPlayed));
			bw.write(System.lineSeparator());
			bw.write(Integer.toString(wordsGuessed.size()));
			bw.write(System.lineSeparator());
			
			// Loop over a known range/collection; watch indices and ensure side effects are intentional.
			for (Integer value : wordsGuessed) {
				bw.write(Integer.toString(value));
				bw.write(System.lineSeparator());
			}
			
			bw.flush();
			bw.close();
		} catch (IOException e) {
			LOG.log(Level.WARNING, "Failed to write statistics file.", e);
		}
		LOG.info("Statistics write completed successfully.");
	}
	
	

	/**
     * getCurrentStreak method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */

	public int getCurrentStreak() {
		return currentStreak;
	}
	/**
     * setCurrentStreak method performs its core logic or handles UI actions as defined.
     * @param currentStreak parameter description
     */

	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
		// Decision point: branch based on this condition—explain why it matters for state flow.
		if (currentStreak > longestStreak) {
			this.longestStreak = currentStreak;
		}
	}
	/**
     * getLongestStreak method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */

	public int getLongestStreak() {
		return longestStreak;
	}
	/**
     * getTotalGamesPlayed method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */

	public int getTotalGamesPlayed() {
		return totalGamesPlayed;
	}
	
	
	/**
     * incrementTotalGamesPlayed method performs its core logic or handles UI actions as defined.
     */

	public void incrementTotalGamesPlayed() {
		this.totalGamesPlayed++;
	}
	/**
     * getWordsGuessed method performs its core logic or handles UI actions as defined.
     * @return result of the operation
     */

	public List<Integer> getWordsGuessed() {
		return wordsGuessed;
	}
	
	
	/**
     * addWordsGuessed method performs its core logic or handles UI actions as defined.
     * @param wordCount parameter description
     */

	public void addWordsGuessed(int wordCount) {
		// Compose structure/UI: adding here establishes parent-child ownership and lifecycle.
		this.wordsGuessed.add(wordCount);
	}
	
	
	public void shutdown() {
	    try {
	        writeStatistics();
	        ioExecutor.shutdown();
	        ioExecutor.awaitTermination(3, TimeUnit.SECONDS);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}

}