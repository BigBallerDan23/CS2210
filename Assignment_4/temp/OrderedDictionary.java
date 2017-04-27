/**
 * 
 * @author Robert O'Neill
 * 		   250052733
 * Assignment 4 - 
 *
 */
public class OrderedDictionary implements OrderedDictionaryADT {
	// variable declarations	
	private Node<Record> root;
	
	/**
	 * Constructor method for Dictionary
	 */
	public OrderedDictionary() {
		root = new Node<Record>();
	}
	
	/**
	 * This will find the key in the dictionary, return null otherwise.
	 * @param k - The key of the record to be found in the dictionary.
	 * @return - Returns the record with key k, null otherwise.
	 */
	public Record find(Key k){	
		Node<Record> current = root;
		
		// while the node has children and the current node doesn't match the key.
		while ((current.hasChildren()) && (current.getElement().getKey().compareTo(k) != 0)){
			// if k is > than current, go right
			if (current.getElement().getKey().compareTo(k) < 0){
				current = current.getRightChild();
			}
			// otherwise go left
			else {
				current = current.getLeftChild();
			}			
		}
		return current.getElement();
	}
	
	/**
	 * This will insert a record into the dictionary.
	 * @param r - The record to be inserted into the dictionary.
	 */
	public void insert (Record r) throws DictionaryException {
		Node<Record> current = root;
		Node<Record> parent = new Node<Record>();
		Node<Record> lChild = new Node<Record>();
		Node<Record> rChild = new Node<Record>();
		
		//checks to see if the tree is empty
		if (root.getElement() == null) {			
			root.setElement(r);
			root.setLeftChild(lChild);
			root.setRightChild(rChild);
		}
		else if (find(r.getKey()) != null) {
			throw new DictionaryException("Error: Entry already exists.");
		}
		else {			
			while (current.hasChildren()) {
				parent = current;
				// if k is > then go right
				if (current.getElement().getKey().compareTo(r.getKey()) < 0){
					current = current.getRightChild();
				}
				// otherwise go left
				else {
					current = current.getLeftChild();
				}					
			}
			// found spot, now insert.
			current.setElement(r);
			current.setParent(parent);
			current.setLeftChild(lChild);
			current.setRightChild(rChild);
		}
	}
	
/**
 *  This will remove a record from the dictionary if the record is there.
 *  @param k - 	The key of the record to be removed from the dictionary.
 */
	public void remove (Key k) throws DictionaryException {
		Node<Record> current = root;
		Node<Record> parent = current;
		Node<Record> rem;
		Node<Record> child = new Node<Record>();
		Node<Record> small = new Node<Record>();
		Node<Record> smallParent;
				
		rem = findNode(k);
		current = rem;
		parent = rem.getParent();
		
		// if to be removed is a leaf, throw an exception.
		if (rem.isLeaf()) {
			throw new DictionaryException("Error: Entry not in dictionary.");
		}
		else {			
			// if at least one of its children are a leaf then.}
			if ((rem.getLeftChild().isLeaf()) || (rem.getRightChild().isLeaf())) {				
				// if left child is a leaf
				if (rem.getLeftChild().isLeaf()) {					
					child = rem.getRightChild();					
				}
				else {					
					child = rem.getLeftChild();					
				}
				// if parent is null, then its root.
				if (rem.getParent() == null){					
					child.setLeftChild(root.getLeftChild());
					child.setRightChild(root.getRightChild());
					root.setElement(child.getElement());
					return;
				}
				// if current is right child of parent, then 
				else if ((parent.getRightChild().getElement() != null) && (parent.getRightChild().getElement().getKey().compareTo(current.getElement().getKey()) == 0)) {					
					parent.setRightChild(child);					
				}
				// else it is the left child 
				else {					
					parent.setLeftChild(child);
				}
			}
			// else they are both internal nodes
			else {
				small = small(rem.getRightChild());
				small.setRightChild(rem.getRightChild());
				rem.getRightChild().setParent(small);
				small.setLeftChild(rem.getLeftChild());
				rem.getLeftChild().setParent(small);
				smallParent = small.getParent();
				small.setParent(parent);
				// check if root again
				if (parent == null) {
					root.setElement(small.getElement());
				}
				// if s is now left child of parent
				else if (parent.getLeftChild().getElement().getKey().compareTo(rem.getElement().getKey()) == 0) {
					parent.setLeftChild(small);
				}
				// else it is right child
				else {
					parent.setRightChild(small);
				}
				if (smallParent.getLeftChild().getElement().getKey().compareTo(small.getElement().getKey()) == 0) {
					smallParent.setLeftChild(new Node<Record>());
				}				
			}
		}		
	}
	
	
	/**
	 * This will find the record with the smallest key larger than k.
	 * @param k - The key of the record to find the successor of.
	 * @return - Returns the record with the smallest key larger than k. Null if there isn't one.
	 */
	public Record successor(Key k) {
		Node<Record> current = root;
		Node<Record> parent = new Node<Record>();
		Node<Record> prev;
				
		if (current.isLeaf()) return null;
		
		// find the key or where it should go
		current = findNode(k);

		if (current.getElement() == null) {
			current = root;
			prev = current;
			while ((current.hasChildren()) && (current.getElement().getKey().compareTo(k) != 0)){
				// if k is > than current, go right
				if (current.getElement().getKey().compareTo(k) < 0){
					prev = current;
					current = current.getRightChild();
				}
				// otherwise go left
				else {
					prev = current;
					current = current.getLeftChild();
				}			
			}
			current = prev;
			if ((current.getElement().getKey().compareTo(k) > 0) && (current.getRightChild().getElement() == null)) {
				return current.getElement();
			}
		}

		// if right child of p is an internal node.
		if (!current.getRightChild().isLeaf()) {			
			return smallest(current.getRightChild());			
		}
		else {			
			parent = current.getParent();
			
			while ((parent.getParent() != null ) && (current.isRightChild()))  {				
				current = parent;
				parent = parent.getParent();
			}
			
			if ((parent == root) && (current.isRightChild())) return null;
//			if (parent.getParent() == null) return null;
			else return parent.getElement();
		}		
	}

	
	/**
	 * This will find the record with the largest key smaller than k.
	 * @param k - - The key of the record to find the successor of.
	 * @return - Returns the record with the largest key smaller than k.
	 */
	public Record predecessor (Key k) {
		// Largest key smaller than k, k doesn't have to exist.
		Node<Record> current = root;
		Node<Record> parent = new Node<Record>();
		Node<Record> prev;
		
		if (current.isLeaf()) return null;
		
		// find the key or where it should go.
		current = findNode(k);
		
		// if it is not there, the space it should be.
		if (current.getElement() == null) {
			current = root;
			prev = current;
			while ((current.hasChildren()) && (current.getElement().getKey().compareTo(k) != 0)){
				// if k is > than current, go right
				if (current.getElement().getKey().compareTo(k) < 0){
					prev = current;
					current = current.getRightChild();
				}
				// otherwise go left
				else {
					prev = current;
					current = current.getLeftChild();
				}			
			}
			current = prev;
			if ((current.getElement().getKey().compareTo(k) < 0) && (current.getRightChild().getElement() == null)) {
				return current.getElement();
			}
		}
		
		// if left child of current is an internal node.
		if (!current.getLeftChild().isLeaf()) {
			return largest(current.getLeftChild());
		}
		else {
			parent = current.getParent();			
			// while current is a left child and parent is not null.
			while ((parent.getParent() != null) && (current.isLeftChild()) ){
				current = parent;
				parent = parent.getParent();
			}
			
			if ((parent == root) && (current.isLeftChild())) return null;
			else return parent.getElement();
		}
	}
		
	/**
	 * This will return the smallest record in the dictionary.
	 * @return - Returns the record with the smallest key. Null if empty.
	 */
	public Record smallest () {
		Node<Record> current = root;
		Node<Record> previous = current;
		
		if (root.getElement() == null) {
			 return null;
		}
		else {
			while ((current.hasChildren()) && (current.getElement() != null)) {
				previous = current;
				current  = current.getLeftChild();
			}			
			return previous.getElement();
		}	
	}	
		
	/**
	 * This will return the largest record in the dictionary.
	 * @return - Returns the record with the largest key. Null if empty.
	 */
	public Record largest () {
		Node<Record> current = root;
		Node<Record> previous = current;
		
		if (root.getElement() == null) {
			 return null;
		}
		else {
			while ((current.hasChildren()) && (current.getElement() != null)) {
				previous = current;
				current  = current.getRightChild();
			}			
			return previous.getElement();
		}		
	}
	
	/**
	 * This passes the node to consider root, finds the largest record in the dictionary.
	 * @return - Returns the record with the largest key. Null if empty.
	 */
	private Record largest (Node<Record> r) {
		Node<Record> current = r;
		Node<Record> previous = current;
		
		if (root.getElement() == null) {
			 return null;
		}
		else {
			while ((current.hasChildren()) && (current.getElement() != null)) {
				previous = current;
				current  = current.getRightChild();
			}			
			return previous.getElement();
		}		
	}
	
	/**
	 * This passes the root to start at, and finds the smallest record in the 
	 * dictionary from that root.
	 * @return - Returns the record with the smallest key. Null if empty.
	 */
	private Record smallest (Node<Record> r) {
		Node<Record> current = r;
		Node<Record> previous = current;
		
		if (root.getElement() == null) {
			 return null;
		}
		else {
			while ((current.hasChildren()) && (!current.isLeaf())) {
				previous = current;
				current  = current.getLeftChild();
			}			
			return previous.getElement();
		}	
	}
	
	/**
	 * This will return the node of the smallest record from provided root.
	 * @param r - The root node to start from.
	 * @return - Returns the node of the smallest record from the provided root.
	 */
	private Node<Record> small (Node<Record> r) {
		Node<Record> current = r;
		Node<Record> previous = current;
		
		if (root.getElement() == null) {
			 return null;
		}
		else {
			while ((current.hasChildren()) && (!current.isLeaf())) {
				previous = current;
				current  = current.getLeftChild();
			}			
			return previous;
		}	
	}	
	
	/**
	 * This will find a node in the dictionary and return that node.
	 * @param k - The key for the node to be found.
	 * @return - Returns the node with key k, null otherwise.
	 */
	private Node<Record> findNode(Key k){	
		Node<Record> current = root;		
		
		// while the node has children and the current node doesn't match the key.
		while ((current.hasChildren()) && (current.getElement().getKey().compareTo(k) != 0)){
			// if k is > than current, go right
			if (current.getElement().getKey().compareTo(k) < 0){				
				current = current.getRightChild();
			}
			// otherwise go left
			else {				
				current = current.getLeftChild();
			}			
		}		
		return current;
	}
}
