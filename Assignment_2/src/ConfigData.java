/**
 * 
 * @author Robert O'Neill
 * 		   250052733
 * 		   CS 2210 - Assignment 2 - The ConfigData class, this is for the 
 * 					 configuration and the associated score.	
 */
public class ConfigData {
	
	// variable declarations
	private String config;
	private int score;
	
	/**
	 * Constructor that sets the configuration and score with the data provided.
	 * @param config - the configuration of the board
	 * @param score - the score associated with this configuration.
	 */
	public ConfigData(String config, int score){
		this.config = config;
		this.score = score;
	}
	
	/**
	 * This will get the board configuration.
	 * @return - returns the board configuration
	 */
	public String getConfig(){
		return config;
	}
	
	/**
	 *  This will get the score for the current element.
	 * @return - returns the score.
	 */
	public int getScore(){
		return score;
	}
}
