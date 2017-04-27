/**
 * 
 * @author Robert O'Neill
 *		   250052733
 *		   Assignment 5 - Edge
 */
public class Edge {
	private String label;
	private Node firstEndpoint, secondEndpoint;
	private int type;
	
	/**
	 * Constructor for the edge class, 
	 * 
	 * @param u - Edge end point one.
	 * @param v - Edge end point two.
	 * @param type - The type of edge it is going to be.
	 */
	Edge(Node u, Node v, int type) {
		this.label = "";
		firstEndpoint = u;
		secondEndpoint = v;
		this.type = type;
	}
	
	/**
	 * Gets the first end point of the edge.
	 * 
	 * @return - Returns the first end point of the edge.
	 */
	public Node firstEndpoint() {
		return firstEndpoint;
	}
	
	/**
	 * Gets the second end point of the edge.
	 * 
	 * @return - Returns the second end point of the edge.
	 */
	public Node secondEndpoint() {
		return secondEndpoint;
	}
	
	/**
	 * Gets the type of the current edge.
	 * 
	 * @return - Returns the type of the edge.
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Sets the label of the current edge to the word provided.
	 * 
	 * @param label - The word to set label to.
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * Gets the label of the current node.
	 * 
	 * @return - Returns the label of the current edge.
	 */
	public String getLabel() {
		return label;
	}
}
