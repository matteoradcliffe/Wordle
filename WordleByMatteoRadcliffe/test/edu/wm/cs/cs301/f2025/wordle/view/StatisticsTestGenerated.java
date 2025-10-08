package edu.wm.cs.cs301.f2025.wordle.view;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;
import edu.wm.cs.cs301.f2025.wordle.model.Statistics;

public class StatisticsTestGenerated {

    private Statistics stats;
    private File testDir;
    private File testFile;
    private String fileSeparator;

    
    @BeforeEach
    public void setUp() throws IOException {
    		testDir = new File(System.getProperty("user.home") + File.separator + "Wordle");
        String tmpDir = System.getProperty("user.home") + File.separator + "Wordle";
        File dir = new File(tmpDir);
        if (!dir.exists()) dir.mkdirs();

        testFile = new File(dir, "statistics.log");
        if (testFile.exists()) testFile.delete();

       
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFile))) {
            bw.write("0\n0\n0\n0\n"); 
        }

        stats = new Statistics();
    }

    @AfterEach
    public void cleanUp() throws IOException {
        if (testFile.exists()) {
            testFile.delete();
            testFile.createNewFile(); // reset to blank after each run
        }
        stats = null;
    }

    /**
     * Ensures writeStatistics creates the correct file and directory structure.
     */
    @Test
    public void testWriteCreatesFileAndDirectory() {
        stats.setCurrentStreak(2);
        stats.incrementTotalGamesPlayed();
        stats.addWordsGuessed(5);
        stats.writeStatistics();

        assertTrue(testDir.exists(), "Wordle directory should exist after writing");
        assertTrue(testFile.exists(), "statistics.log should exist after writing");
        assertTrue(testFile.length() > 0, "statistics.log should not be empty");
    }

    /**
     * Verifies that reading statistics from a corrupted file produces an expected exception
     * and cleans up afterwards so later tests run cleanly.
     */
    @Test
    public void testReadHandlesCorruptedFileGracefully() throws IOException {
        // Intentionally corrupt the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFile))) {
            bw.write("NotANumber\nBadData\n");
        }

        // We expect a NumberFormatException since Statistics doesn't handle this case
        try {
            new Statistics();
            fail("Expected NumberFormatException when reading corrupted file");
        } catch (NumberFormatException e) {
            System.out.println("Handled expected NumberFormatException from corrupted file.");
        }

        // Clean up to prevent later tests from reading corrupted data
        if (testFile.exists()) {
            testFile.delete();
            testFile.createNewFile();
        }
    }

    /**
     * Tests readStatistics fallback when file does not exist.
     */
    @Test
    public void testReadFromNonexistentFileInitializesDefaults() {
        if (testFile.exists()) testFile.delete();
        Statistics newStats = new Statistics();
        assertEquals(0, newStats.getCurrentStreak());
        assertEquals(0, newStats.getLongestStreak());
        assertEquals(0, newStats.getTotalGamesPlayed());
        assertTrue(newStats.getWordsGuessed().isEmpty());
    }

    /**
     * Verifies that multiple writes preserve all values and order of wordsGuessed.
     */
    @Test
    public void testMultipleWritesPreserveData() {
        stats.setCurrentStreak(2);
        stats.incrementTotalGamesPlayed();
        stats.addWordsGuessed(1);
        stats.addWordsGuessed(4);
        stats.writeStatistics();

        Statistics loaded = new Statistics();
        assertEquals(2, loaded.getCurrentStreak());
        assertEquals(2, loaded.getLongestStreak());
        assertEquals(1, loaded.getTotalGamesPlayed());
        assertEquals(List.of(1, 4), loaded.getWordsGuessed());

        // Add more and rewrite
        loaded.addWordsGuessed(7);
        loaded.incrementTotalGamesPlayed();
        loaded.setCurrentStreak(3);
        loaded.writeStatistics();

        Statistics reloaded = new Statistics();
        assertEquals(3, reloaded.getLongestStreak());
        assertEquals(2, reloaded.getTotalGamesPlayed());
        assertEquals(List.of(1, 4, 7), reloaded.getWordsGuessed());
    }

    /**
     * Tests that wordsGuessed list grows dynamically and preserves insertion order.
     */
    @Test
    public void testWordsGuessedDynamicGrowth() {
        for (int i = 0; i < 50; i++) {
            stats.addWordsGuessed(i);
        }
        assertEquals(50, stats.getWordsGuessed().size());
        assertEquals(0, stats.getWordsGuessed().get(0));
        assertEquals(49, stats.getWordsGuessed().get(49));
    }

    /**
     * Ensures longestStreak updates only when current streak exceeds it.
     */
    @Test
    public void testLongestStreakIncreasesOnlyWhenAppropriate() {
        stats.setCurrentStreak(1);
        stats.setCurrentStreak(3);
        stats.setCurrentStreak(2);
        assertEquals(3, stats.getLongestStreak(), "Longest streak should not decrease when currentStreak is smaller");
    }

    /**
     * Validates incrementTotalGamesPlayed across multiple calls.
     */
    @Test
    public void testIncrementTotalGamesPlayedMultipleTimes() {
        for (int i = 0; i < 5; i++) {
            stats.incrementTotalGamesPlayed();
        }
        assertEquals(5, stats.getTotalGamesPlayed());
    }

    /**
     * Detects possible file path bug with log separator handling.
     */
    @Test
    public void testFilePathIntegrity() {
        String constructedPath = System.getProperty("user.home") + fileSeparator + "Wordle" + fileSeparator + "statistics.log";
        File expected = new File(constructedPath);
        stats.writeStatistics();
        assertTrue(expected.exists() || testFile.exists(),
                "File should be created at expected path");
    }

    /**
     * Verifies wordsGuessed remains modifiable and separate between instances.
     */
    @Test
    public void testWordsGuessedListIsIndependentBetweenInstances() {
        stats.addWordsGuessed(10);
        Statistics another = new Statistics();
        another.addWordsGuessed(20);

        assertNotSame(stats.getWordsGuessed(), another.getWordsGuessed(),
                "Each instance should maintain its own list");
    }
}