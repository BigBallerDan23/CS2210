/**
 * 
 * @author Robert O'Neill
 * 		   250052733
 * Assignment 4 - 
 *
 */
public class Record {
	// Variable Declarations
	private String data = "";
	private Key key;
	
	/**
	 * Constructor method for Record.
	 * @param k
	 * @param data
	 */	
	public Record(Key k, String data){		
		this.key = k;
		this.data = data;
	}
	
	/**
	 * 
	 * @return - Returns the key in the Record.
	 */
	public Key getKey(){ 
		return key;
	}
	
	/**
	 * 
	 * @return - Returns the data in the Record.
	 */
	public String getData() {
		return data;
	}
}
