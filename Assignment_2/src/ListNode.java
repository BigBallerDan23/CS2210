/**
 * 
 * @author Robert O'Neill
 * 		   250052733	
 * 		   CS 2210 - Assignment 2 - This is the node class to work with a linked list.
 *
 * @param <E> - The type the node will be.
 */
public class ListNode<E> {
	private ListNode<E> next;
	private E current;
	
	/**
	 * Constructor that creates an empty node. It sets current and next to null.
	 */
	public ListNode(){
		next = null;
		current = null;
	}
	
	/**
	 * Constructor that creates a node and sets it to the element provided. Sets next to null.
	 * @param elem - The current element in the list.
	 */
	public ListNode(E elem){
		next = null;
		current = elem;
	}
	
	/**
	 * Gets the next element node.
	 * @return - Returns the next element.
	 */
	public ListNode<E> getNext(){
		return next;
	}
	
	/**
	 * Sets the current elements next node to the node provided.
	 * @param node - Node to be set as the currnet elements next node.
	 */
	public void setNext(ListNode<E> node){
		next = node;
	}
	
	/**
	 * Gets the current node.
	 * @return - The current node.
	 */
	public E getcurrent(){
		return current;
	}
	
	/**
	 * Sets the current element node to the one provided.
	 * @param elem - The element to set as the current element.
	 */
	public void setcurrent(E elem) {
		current = elem;
	}
}
