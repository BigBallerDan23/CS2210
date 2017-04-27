/**
 * 
 * @author Robert O'Neill
 * 		   250052733	
 * 		   CS 2210 - Assignment 2 - An exception to be thrown if there was an error with the
 * 					 dictionary.
 */
public class DictionaryException extends Exception {
	/**
	 * This will make the exception with provided message
	 * @param message - this is the message to be printed out with the exception
	 */
	public DictionaryException(String message){
		super(message);
	}
	
	/**
	 * This will make the exception without printing a message.
	 */
	public DictionaryException(){}
}
