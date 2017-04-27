import java.util.ArrayList;

/**
 * 
 * @author Robert O'Neill
 * 		   250052733	
 * 		   CS 2210 - Assignment 2 - Dictionary class that creates an array hash table where
 * 									each element is a linked list. It stores the configuration and score for 
 * 									different boards.
 */
public class Dictionary implements DictionaryADT {
	
	// variable declarations
	private final static int CONSTANT = 10; // the coefficient for the hash function.

	private ArrayList<ListNode<ConfigData>> dict; 	
	private int tableSize;
	private int coefficient = 6921;
	
	/**
	 * This constructor will build a new dictionary of the provided size.
	 * @param size - The size of the dictionary to be created.
	 */
	public Dictionary (int size) {
		dict = new ArrayList<>();
		// initialize the arrayList.
		for (int i = 0; i < size; i++){
			dict.add(null);
		}
		tableSize = size;
	}
	
	public Dictionary (int size, int coefficient) {
		dict = new ArrayList<>();
		// initialize the arrayList.
		for (int i = 0; i < size; i++){
			dict.add(null);
		}
		tableSize = size;
		this.coefficient = coefficient;
	}
	
	/**
	 * Constructor to insert an item into the hash table.
	 * @param pair - The data to be inserted into the table, of type ConfigData.
	 * @return = Returns 1 if there was a collision or 0 otherwise.
	 */
	public int insert(ConfigData pair) throws DictionaryException {
		// insert into the dictionary
		int returnCode = 0;
		
		// check to see if it is already in the table.
		if (this.find(pair.getConfig()) != -1) { throw new DictionaryException("Error: Dictionary Exception");}
		
		int hashCode = makeKey(coefficient, pair.getConfig());
		ListNode<ConfigData> head = dict.get(hashCode);
		ListNode<ConfigData> newItem = new ListNode<ConfigData>(pair);
		
		// check if the key is present, if it is return 1, else return 0
		if (head == null) 
				returnCode = 0; 
		else 
				returnCode = 1; 		
		
		// add the new item to the table.
		newItem.setNext(head);
		dict.set(hashCode, newItem);
		
		return returnCode;
	}
	
	/**
	 *  Removes the provided board configuration from the table if it is present.
	 *  @param config - The board configuration to remove from the table.
	 */
	public void remove(String config) throws DictionaryException {
		int hashCode = makeKey(coefficient, config);
		ListNode<ConfigData> head = dict.get(hashCode);
		ListNode<ConfigData> prev = null;
		
		while (head != null){
			// if the item is found
			if (head.getcurrent().getConfig().equals(config)){
				break;
			}
			// if it is not found, continue looking
			prev = head;
			head = head.getNext();
		}
		
		// if item was not there, throw an exception.
		if (head == null) {
			throw new DictionaryException("Error: Dictionary Exception");
		}
		
		// otherwise remove the item.
		if (prev == null){
			dict.set(hashCode, head.getNext());
		}
		else {
			prev.setNext(head.getNext());
		}
		
	}
	
	/**
	 * Tries to find the provided configuration in the table.
	 * @param config - The board configuration that is to be found.
	 * @return - Returns the score associated with the configuration, or -1 if not found.
	 */
	public int find(String config) {
		int hashCode = makeKey(coefficient, config);
		ListNode<ConfigData> head = dict.get(hashCode);
		
		// goes through all entries until it finds a match or it is not there.
		// returns the score if a match is found, -1 otherwise.
		while (head != null){
			if (head.getcurrent().getConfig().equals(config)) {
				return head.getcurrent().getScore();
			}
			head = head.getNext();
				
		}
		return -1;
	}
	
	/**
	 * 
	 * @param coefficient - The coefficient to be used in the hash function
	 * @param str - The board configuration to be added to the hash table. 
	 * @return - Returns the newly created hashCode.
	 */
	private int makeKey(int coefficient, String str) {
		int key = ((int) str.charAt(str.length()-1)) % tableSize;
		// loop to create key
		for (int i = (str.length()-2); i >= 0; i--) {
			key = (key * coefficient + ((int) str.charAt(i))) % tableSize;
		}		
		return key;
	}
	
	// to tell how many empty spaces are left.
	public int getEmpty(){
		int empty = 0;
		for (int i = 0; i < tableSize; i++){
			if (dict.get(i) == null) {
				empty++;
			}
		}
		return empty;
	}

}
