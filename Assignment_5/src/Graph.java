import java.util.Iterator;
import java.util.ArrayList;
/**
 * 
 * @author Robert O'Neill
 *		   250052733
 *		   Assignment 5 - Graph
 */
public class Graph implements GraphADT{
	private int n = 0;
	ArrayList<Node> nodeList;
	Edge[][] adjacencyMatrix;
	/**
	 * Constructor for the Graph class, this will create a graph with the supplied number of Node.
	 * 
	 * @param n - The number of nodes to create the graph with.
	 */
	Graph(int n) {
		this.n = n;
		nodeList  = new ArrayList<Node>();		
		adjacencyMatrix = new Edge[n][n];
		// populate the node list and initialize the edge matrix.
		for (int i = 0; i < n; i++) {
			Node newNode = new Node(i);			
			nodeList.add(newNode);
			for (int j = 0; j < n; j++){
				adjacencyMatrix[i][j] = null;
				
			}
		}		
	}
	
	/**
	 * Gets the Node corresponding with the provided name, if the node is not in the graph, throw
	 * a Graph Exception.
	 * 
	 * @param name - the name of the node to find.
	 * @return - Returns the node with the supplied name. 
	 */
	public Node getNode(int name) throws GraphException {
		// check if the name is outside the list.
		if (name > (nodeList.size() - 1)) {
			throw new GraphException("Error: Node not present.");
		}		
		return nodeList.get(name);
	}

	/**
	 * Inserts an edge with supplied end points and edge type into the graph.
	 * 
	 * @param u - First end point. 
	 * @param v - Second end point.
	 * @param edgeType - Type of edge.
	 */
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException {	
		if (u.getName() > (nodeList.size() - 1) || v.getName() > (nodeList.size() - 1)) {
			throw new GraphException("Error: Atleast one node not present.");
		}
		else {
			Edge newEdge = new Edge(u, v, edgeType);
			adjacencyMatrix[u.getName()][v.getName()] = newEdge;
			adjacencyMatrix[v.getName()][u.getName()] = newEdge;
		}
	}

	/**
	 * Gets an Iterator containing all the incident edges of the supplied Node.
	 * 
	 * @param u - The Node to get the incident edges for.
	 * @return - Returns an Iterator with all the incident edges of Node u. 
	 */
	public Iterator<Edge> incidentEdges(Node u) throws GraphException {
		ArrayList<Edge> list = new ArrayList<Edge>();
		if (u.getName() > (nodeList.size() - 1)) {
			throw new GraphException("Error: Node not present.");
		}		
		else {		
			// gets all the incident edges if node u.
			for (int i = 0; i < n; i++){
				if (adjacencyMatrix[u.getName()][i] != null) {
					list.add(adjacencyMatrix[u.getName()][i]);
				}
			}
		}		
		return list.iterator();
	}

	/**
	 * Gets the edge that connects the two provided Nodes.
	 * 
	 * @param u - First end point. 
	 * @param v - Second end point.
	 * @return - Returns the edge that connects the two provided nodes.
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		if (u.getName() > (nodeList.size() - 1) || v.getName() > (nodeList.size() - 1)) {
			throw new GraphException("Error: Atleast one node not present.");
		}
		else return adjacencyMatrix[u.getName()][v.getName()];
		
	}

	/**
	 * Tells if two nodes are adjacent to each other, and returns a true/false statement.
	 * 
	 * @param u - First end point.
	 * @param v - Second end point.
	 * @return - Returns true/false if the provided nodes are adjacent.
	 */
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		if (u.getName() > (nodeList.size() - 1) || v.getName() > (nodeList.size() - 1)) {
			throw new GraphException("Error: Atleast one node not present.");
		}
		if (adjacencyMatrix[u.getName()][v.getName()] != null) {
			return true;
		}
		else {
			return false;
		}
	}	
}
