import java.io.*;
import java.util.Iterator;
import java.util.Stack;
/**
 * 
 * @author Robert O'Neill
 *		   250052733
 *		   Assignment 5 - Road Map
 */
public class RoadMap {
	private int initialMoney = 0;
	private int startingNode = -1;
	private int destinationNode = -1;
	private int width = 0, length = 0;
	private Graph mapGraph;
	private int tollAmt = 0;
	private int gainAmt = 0;
	private String[][] map;
	private int money;
	private boolean done = false;
	private Stack<Node> stack = new Stack<Node>();
	
	/**
	 * Constructor for RoadMap class that builds a Graph from the contents of an input file. 
	 * 
	 * @param inputFile - The name of the file containing the details for creating the Map.
	 */
	RoadMap(String inputFile) throws MapException {
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			
			// start to process the map file,
			Integer.parseInt(in.readLine());
			startingNode = Integer.parseInt(in.readLine());
			destinationNode = Integer.parseInt(in.readLine());
			initialMoney = Integer.parseInt(in.readLine());
			width = Integer.parseInt(in.readLine());
			length = Integer.parseInt(in.readLine());
			tollAmt = Integer.parseInt(in.readLine());
			gainAmt = Integer.parseInt(in.readLine());
			money = initialMoney;
			
			// create the graph
			Graph graph = new Graph(width*length);
			
			// finish reading file and populate the graph
			String line = in.readLine();
			int node1 = 0, node2=0, row = 0;
			map = new String[2*length-1][2*width-1];
			
			while (line != null) {
				for (int i = 0; i < line.length(); ++i) {
					if (line.charAt(i) == '+') {
						map[row][i] = Integer.toString(node1);
						node1++;
					}
					else {
						map[row][i] = line.substring(i, i+1);
					}
				}
				line = in.readLine();
				row++;				
			}
			in.close();
			node1 = 0;
			for (int i = 0; i < (2*length - 1); ++i){
				for (int j = 0; j < (2*width-1); ++j){
					// populate the graph.
					switch(map[i][j]){
					case "T":
						// if it is even, then horizontal road
						if ((i % 2) == 0) {
							node1 = Integer.parseInt(map[i][j-1]);
							node2 = Integer.parseInt(map[i][j+1]);
							graph.insertEdge(graph.getNode(node1), graph.getNode(node2), 1);
						}
						// else vertical
						else {
							node1 = Integer.parseInt(map[i-1][j]);
							node2 = Integer.parseInt(map[i+1][j]);
							graph.insertEdge(graph.getNode(node1), graph.getNode(node2), 1);
						}
						break;
					case "F":
						if ((i % 2) == 0) {
							node1 = Integer.parseInt(map[i][j-1]);
							node2 = Integer.parseInt(map[i][j+1]);
							graph.insertEdge(graph.getNode(node1), graph.getNode(node2), 0);
						}
						// else vertical
						else {
							node1 = Integer.parseInt(map[i-1][j]);
							node2 = Integer.parseInt(map[i+1][j]);
							graph.insertEdge(graph.getNode(node1), graph.getNode(node2), 0);
						}
						break;
					case "C":
						if ((i % 2) == 0) {
							node1 = Integer.parseInt(map[i][j-1]);
							node2 = Integer.parseInt(map[i][j+1]);
							graph.insertEdge(graph.getNode(node1), graph.getNode(node2), -1);							
						}
						// else vertical
						else {
							node1 = Integer.parseInt(map[i-1][j]);
							node2 = Integer.parseInt(map[i+1][j]);
							graph.insertEdge(graph.getNode(node1), graph.getNode(node2), -1);
						}
						break;						
					}
				}
			}	
			mapGraph = graph;
		}
		catch (IOException e) {
			throw new MapException("Input File does not exist.");
		}
		catch (GraphException e) {
			System.out.println(e);
		}
		catch (Exception e) {
			System.out.println("Error: "+e);
		}
	}
	
	/**
	 * Gets the graph representing the road map.
	 * 
	 * @return - Returns the graph representing the road map.
	 */
	public Graph getGraph() {
		return mapGraph;
	}
	
	/**
	 * Gets the starting node of the road map.
	 * 
	 * @return - Returns the starting node of the map.
	 */
	public int getStartingNode(){
		return startingNode;
	}
	
	/**
	 * Gets the destination node of the road map.
	 * 
	 * @return - Returns the destination node of the road map.
	 */
	public int getDestinationNode() {
		return destinationNode;
	}
	
	/**
	 * Gets the initial amount of money available to pay tolls.
	 * 
	 * @return - Returns the initial amount of money available.
	 */
	public int getInitialMoney() {
		return initialMoney;
	}
	
	/**
	 * Gets a Iterator containing all the nodes along the path from the start node to the destination node, 
	 * if such a path exists.
	 * @param start - The starting point for the road map.
	 * @param destination - The final destination for the road map.
	 * @param initialMoney - The initial amount of money available to pay road tolls.
	 * @return - Returns an Iterator with the nodes on the path, otherwise returns null.
	 */
	public Iterator<Node> findPath(int start, int destination, int initialMoney) {
		// variable declarations
		Iterator<Edge> nodeEdges;
		Node curNode, nextNode = new Node(-2);
		Edge tempEdge, prev = null;
		int type = -2;		
		
		try {			
			curNode = mapGraph.getNode(start);
			stack.push(curNode);
			curNode.setMark(true);			
			// if its at destination, mark done and return iterator
			if (curNode.getName() == destination) {
				done = true;
				return stack.iterator();
			}
			// get incident edges.
			nodeEdges = mapGraph.incidentEdges(curNode);
			// while there are edges
			while (nodeEdges.hasNext()) {
				// if destination has been found, return iterator.
				if (done == true) {			
					return stack.iterator();
				}				
				tempEdge = nodeEdges.next();
				type = tempEdge.getType();				
				// if edge is not labelled
				if (tempEdge.firstEndpoint().getName() == start) {
					nextNode = tempEdge.secondEndpoint();
				}
				else if (tempEdge.secondEndpoint().getName() == start){
					nextNode = tempEdge.firstEndpoint();
				}
				if (tempEdge.getLabel().equals("")) {
					if (nextNode.getMark() == true){
						tempEdge.setLabel("back");
						
					}
					else {						
						// if toll and have enough money, go
						if ( (type == 1)) {	
							if (money >= tollAmt){
								money -= tollAmt;
								tempEdge.setLabel("discovery");
								findPath(nextNode.getName(), destination, money);
							}
						}
						// else if free go 
						else if (type == 0) {
							tempEdge.setLabel("discovery");
							findPath(nextNode.getName(), destination, money);							
						}
						// else compensation, go there
						else if (type == -1){							
							money -= gainAmt;
							tempEdge.setLabel("discovery");							
							findPath(nextNode.getName(), destination, money);
							
						}						
					}					
				}
				tempEdge.setLabel("");
			}
			// if not done, set mark of the node to false and pop it from the stack
			if (!done) {				
				stack.peek().setMark(false);
				Node tempNode = stack.peek();
				stack.pop();
				if (stack.size() > 0 ){
					prev = mapGraph.getEdge(mapGraph.getNode(stack.peek().getName()), tempNode);
					prev.setLabel("");
					if (prev.getType() == 1) {
						money += tollAmt;
					}
					else if (prev.getType() == -1) {
						money += gainAmt;
					}	
				}				
			}
			
		}
		catch (GraphException e) {
			System.out.println(e);
		}		
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
		if (stack.empty()) return null;
		else return stack.iterator();		
	}
}
