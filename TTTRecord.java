/**
 * Class stores the score, level and config data for a TTTRecord object
 * 
 * @author Michael Booth
 */
public class TTTRecord {

	private String configRecord; // Config data
	private int scoreRecord; // Score data
	private int levelRecord; // Level data

	/**
	 * Constructor creates a TTTRecord object with specified values
	 */
	public TTTRecord(String config, int score, int level) {
		configRecord = config;
		scoreRecord = score;
		levelRecord = level;
	}

	/*
	 * Getter method for config data
	 */
	public String getConfiguration() {
		return configRecord;
	}

	/*
	 * Getter method for score data
	 */
	public int getScore() {
		return scoreRecord;
	}

	/*
	 * Getter method for level data
	 */
	public int getLevel() {
		return levelRecord;
	}
}
