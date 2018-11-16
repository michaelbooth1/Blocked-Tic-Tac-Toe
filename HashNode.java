/**
 * Class that creates a node to store the TTTRecord and links them together
 * 
 * @author Michael Booth
 */

public class HashNode {
	private HashNode next; // Pointer to the next HashNode in the linked list
	private TTTRecord data; // TTTRecord the node holds

	/*
	 * Constructor stores the record in a node
	 */
	public HashNode(TTTRecord node) {
		next = null;
		data = node;
	}

	/*
	 * Sets the node the current node is linked to
	 */
	public void setNext(HashNode node) {
		next = node;
	}

	/*
	 * Gets the node the current node is linked to
	 */
	public HashNode getNext() {
		return next;
	}

	/*
	 * Gets the data stored in the node
	 */
	public TTTRecord getRecord() {
		return data;
	}
}
