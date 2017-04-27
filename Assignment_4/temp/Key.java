/**
 * 
 * @author Robert O'Neill
 * 		   250052733
 * Assignment 4 - 
 *
 */
public class Key {
	// Variable Declarations
	private String word = "";
	private int type;
	
	/**
	 * Constructor method for the key.
	 * @param word
	 * @param type
	 */
	public Key(String word, int type) {
		this.word = word;
		this.type = type;
	}
	
	/**
	 * 
	 * @return - Returns the word in the key.
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * 
	 * @return - Returns the type in the key.
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * 
	 * @param k
	 * @return - Return 0 if this key is equal to key, -1 if this is smaller or 1 otherwise.
	 */
	public int compareTo(Key k) {
		int strCompare = word.toLowerCase().compareTo(k.getWord().toLowerCase());
		if ((strCompare == 0) && (type == k.getType())) {			
			return 0;
		}
		else if ((strCompare == 0) && (type < k.getType())) {
			return -1;
		}
		else if (strCompare < 0) {			
			return -1;
		}
		else { 
			return 1;
		}
	}	
}
