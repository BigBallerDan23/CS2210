/**
 * 
 * @author Robert O'Neill
 *		   250052733
 *		   Assignment 5 - Graph Exception. 
 */
public class GraphException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Generalized graph exception error.
	 */
	public GraphException() {
		super("Error: Graph Exception.");
	}
	
	/**
	 * Graph exception error with a provided message.
	 * 
	 * @param message - The message supplied with the error.
	 */
	public GraphException(String message) {
		super(message);
	}
}
