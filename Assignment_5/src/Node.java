/**
 * 
 * @author Robert O'Neill
 *		   250052733
 *		   Assignment 5 - Node
 */
public class Node {
	private boolean mark = false;
	private int name = -1;
	
	/**
	 * This is the constructor for the class and it creates a node with the given name. 
	 * The name of a node is an integer value between 0 and n-1, where n is the number of 
	 * nodes in the graph.
	 * 
	 * @param name - The given name to create a node for.
	 */
	Node(int name) {
		this.name = name;
	}

	/**
	 * Sets a true/false marker for the node. True if it has been visited,
	 * false otherwise.
	 * 
	 * @param mark - True/False value to mark the node with.
	 */
	public void setMark(boolean mark) {
		this.mark = mark;
	}
	
	/**
	 * Gets the value of the true/false marker of the current node.
	 * 
	 * @return - Returns the value of the mark of the current node.
	 */
	public boolean getMark() {
		return mark;
	}
	
	/**
	 * Gets the name of the current node.
	 * 
	 * @return - Returns the name of the current node.
	 */
	public int getName() {
		return name;
	}
}
