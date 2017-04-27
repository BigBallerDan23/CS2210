/**
 * 
 * @author Robert O'Neill
 * 		   250052733
 * Assignment 4 - 
 *
 */
public class Node<E> {
	//variable declarations
	private E element;
	private Node<E> leftChild, rightChild, parent;
	
	/**
	 * 
	 * @param object
	 */
	Node () {
		element = null;
		leftChild = null;
		rightChild = null;
		parent = null;
	}
	
	/**
	 * Constructor method for the node.
	 * @param object
	 */
	Node (E object) {
		element = object;
		leftChild = null;
		rightChild = null;
		parent = null;
	}
	
	/**
	 * 
	 * @param object - Node to be set as the parent of this node.
	 */
	public void setParent(Node<E> object) {
		parent = object;
	}
	
	/**
	 * 
	 * @return - Returns the parent node.
	 */
	public Node<E> getParent() {		
		return parent;
	}
	
	/**
	 * 
	 * @param object - Node to be set as the left child of this node.
	 */
	public void setLeftChild(Node<E> object) {
		leftChild = object;
	}
	
	/**
	 * 
	 * @return - Returns the node of the left child.
	 */
	public Node<E> getLeftChild() {
		return leftChild;
	}
	
	/**
	 * 
	 * @param object - node to be set as the right child of this node.
	 */
	public void setRightChild(Node<E> object) {
		rightChild = object;
	}
	
	/**
	 * 
	 * @return - Returns the node of the right child.
	 */
	public Node<E> getRightChild() {
		return rightChild;
	}
	
	/**
	 * 
	 * @return - returns the element of this node.
	 */
	public E getElement() {
		return element;
	}
	
	/**
	 * 
	 * @param object - the element to be set for this node.
	 */
	public void setElement(E object) {
		element = object;
	}
	
	/**
	 * 
	 * @return - Return true/false if the node has any children.
	 */
	public boolean hasChildren() {
		if ((leftChild != null) || (rightChild != null)) {
			return true;
		}
		else return false;
	}
	
	/**
	 * 
	 * @return - true/false if this node is a leaf.
	 */
	public boolean isLeaf() {
		if (this.hasChildren()) return false;
		else return true;
	}
	
	/**
	 * 
	 * @return - true/false if this node is a right child.
	 */
	public boolean isRightChild() {
		if (this.parent.rightChild.getElement() == this.element) return true;
		else return false;
	}
	
	/**
	 * 
	 * @return - true/false if this node is a left child.
	 */
	public boolean isLeftChild() {
		if (this.parent.leftChild.getElement() == this.element) return true;
		else return false;		
	}
	
	/**
	 * 
	 * @param node - node to compare to this node.
	 * @return - 0 if they are the same node, 1 otherwise.
	 */
	public int compareTo(Node<Record> node) {
		if ((node.getElement() == element)){
			return 0;
		}
		else return 1;
	}
}
