package edu.wm.cs.cs301.f2025.wordle.view;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import edu.wm.cs.cs301.f2025.wordle.model.Statistics;
import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTestManual {
	private Statistics stats;
    private File testFile;
    
    @BeforeEach
    public void setUp() throws IOException {
    		String tmp = System.getProperty("user.home") + System.getProperty("file.separator") + "Wordle";
    		testFile = new File(tmp, "statistics.log");
    		if (testFile.exists()) {
    			testFile.delete();
    		}
    		stats = new Statistics();
    }
    
    @AfterEach
    public void tearDown() {
    		if (testFile.exists()) {
    			testFile.delete();
    		}
    }
    
    @Test
    public void testInitialVals() {
    		assertEquals(0, stats.getCurrentStreak(), "Default current streak should start at 0");
    		assertEquals(0, stats.getLongestStreak(), "Default longest streak should be 0");
    		assertEquals(0, stats.getTotalGamesPlayed(), "default total games should start at 0");
        assertTrue(stats.getWordsGuessed().isEmpty(), "no words guessed yet");
    }
    
    @Test
    public void testStreakUpdatesProperly() {
        stats.setCurrentStreak(1);
        assertEquals(1, stats.getCurrentStreak());
        assertEquals(1, stats.getLongestStreak());

        stats.setCurrentStreak(3);
        assertEquals(3, stats.getLongestStreak(), "Longest strek should increase when higher streak is set");
        stats.setCurrentStreak(2);
        assertEquals(3, stats.getLongestStreak(), "Longest streak should not decrease");
    }
    
    @Test
    public void testIncrementAndAddWordsGuessed() {
        stats.incrementTotalGamesPlayed();
        stats.incrementTotalGamesPlayed();
        assertEquals(2, stats.getTotalGamesPlayed(), "Total games should be the increments");
        stats.addWordsGuessed(3);
        stats.addWordsGuessed(5);
        assertEquals(Arrays.asList(3, 5), stats.getWordsGuessed(), "Should store guessed word counts");
    }
    
    @Test
    public void testWriteAndReadBackStatistics() {
        stats.setCurrentStreak(4);
        stats.incrementTotalGamesPlayed();
        stats.addWordsGuessed(6);
        stats.writeStatistics();
        
        Statistics reloaded = new Statistics();
        assertEquals(4, reloaded.getCurrentStreak());
        assertEquals(4, reloaded.getLongestStreak());
        assertEquals(1, reloaded.getTotalGamesPlayed());
        assertEquals(List.of(6), reloaded.getWordsGuessed());
        
    }
    
    @Test
    public void testSimulatedPlayerSession() {
        
        stats.setCurrentStreak(1);
        stats.incrementTotalGamesPlayed();
        stats.addWordsGuessed(4);

        stats.setCurrentStreak(2);
        stats.incrementTotalGamesPlayed();
        stats.addWordsGuessed(3);

        stats.setCurrentStreak(0);
        stats.incrementTotalGamesPlayed();

        stats.setCurrentStreak(1);
        stats.incrementTotalGamesPlayed();
        stats.addWordsGuessed(5);

        assertEquals(4, stats.getTotalGamesPlayed());
        assertEquals(2, stats.getLongestStreak(), "Longest streak should be the max consecutive wins won");
        assertEquals(List.of(4, 3, 5), stats.getWordsGuessed());
    }

	
	
	
	
	

}
