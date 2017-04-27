/**
 * 
 * @author Robert O'Neill
 *		   250052733
 *		   Assignment 5 - Map Exception
 */
public class MapException extends Exception {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Generalized map exception error.
	 */
	public MapException() {
		super("Error: Map Exception.");
	}
	
	/**
	 * Map exception error with a provided message.
	 * 
	 * @param message - The message supplied with the error.
	 */
	public MapException(String message) {
		super(message);
	}
}
