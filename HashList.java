/**
 * Class that creates a linked list of nodes with methods to add and remove them
 * 
 * @author Michael Booth
 */
public class HashList {

	private int count; // Size of the list
	private HashNode head; // Reference to the first node in the list

	/*
	 * Constructor creates a linked list of HashNode's
	 */
	public HashList() {
		count = 0;
		head = null;
	}

	/*
	 * Adds the node to the linked list. Returns 1 if already in list, 0
	 * otherwise
	 */
	public int add(HashNode node) {
		HashNode previous = null;
		HashNode current = head;

		if (count == 0) {
			head = node;
		} else {
			while (current != null) {
				if (current.getRecord().getConfiguration().equals(node.getRecord().getConfiguration())) {
					return 1;
				}
				previous = current;
				current = current.getNext();
			}
			previous.setNext(node);
		}
		count++;
		return 0;
	}

	/*
	 * Removes and returns the node holding the config from the list
	 */
	public HashNode remove(String config) {
		boolean found = false;
		HashNode previous = null;
		HashNode current = head;

		while (current != null && !found) {
			if (config.equals(current.getRecord().getConfiguration())) {
				found = true;
			} else {
				previous = current;
				current = current.getNext();
			}
		}

		if (found) {
			if (size() == 1) {
				head = null;
			} else if (current.equals(head)) {
				head = current.getNext();
			} else {
				previous.setNext(current.getNext());
			}

			count--;
		}

		return current;
	}

	/*
	 * Returns the TTRecord holding the specified config
	 */
	public TTTRecord getRecord(String config) {
		HashNode current = head;

		for (int i = 0; i < size(); i++) {
			if (config.equals(current.getRecord().getConfiguration())) {
				return current.getRecord();
			} else {
				current = current.getNext();
			}
		}
		return null;
	}

	/*
	 * Returns the size of the list
	 */
	public int size() {
		return count;
	}
}
