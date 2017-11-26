package classes;

import java.util.HashMap;
import java.util.Map;

/*
 * Keeps a sorted linked list which keeps track of the two nodes next to the center value
 * The center value is (lowest+highest)/2 (center of the spectrum)
 * THIS IS NOT PROPERLY IMPLEMENTED YET
 */
public class SortedLinkedListCenter {

	private class Node{
		public Node previous;
		public Node next;
		public int value;
		public int num;
	}

	/*
	 * This will be the highest node <= the center value
	 */
	private Node centerLower;
	/*
	 * This will be the lowest node >= center value
	 */
	private Node centerUpper;
	private Node head;
	private Node end;
	private Map<Integer, Node> map;

	public SortedLinkedListCenter() {
		this.head = new Node();
		this.end = new Node();
		this.head.previous = null;
		this.head.next = this.end;
		this.end.previous = this.head;
		this.end.next = null;
		this.map = new HashMap<Integer, Node>();
	}

	//if its value is equal to another node, adds it to the left
	private Node addToList(int value) {
		Node node = new Node();
		node.value = value;

		Node currentNode = this.head.next;
		while(currentNode.value < value && currentNode.next != null) {
			currentNode = currentNode.next;
		}
		node.previous = currentNode.previous;
		currentNode.previous.next = node;
		node.next = currentNode;
		currentNode.previous = node;
		return node;
	}

	private Node findCenterLower(double centerValue) {
		Node currentNode = this.head.next;
		while(currentNode.next  != null && currentNode.next.value <= centerValue) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}

	private Node findCenterUpper(double centerValue) {
		Node currentNode = this.end.previous;
		while(currentNode.previous != null && currentNode.previous.value >= centerValue) {
			currentNode = currentNode.previous;
		}
		return currentNode;
	}

	//node is the node recently added to the list AND map
	private void setNewCenters(Node node) {
		double newCenterValue = (this.head.next.value+this.end.previous.value)/2.0;
		if(this.centerLower == null) {
			this.centerLower = node;
			this.centerUpper = node;
		}else if(this.map.size() == 1) {
			//do nothing
		}else if(this.map.size() == 2) {
			this.centerLower = this.head.next;
			this.centerUpper = this.end.previous;
		}else if(this.head.next == node || this.end.previous == node) { //new center value
			this.centerUpper = findCenterUpper(newCenterValue);
			this.centerLower = findCenterLower(newCenterValue);
		}else{
			if(node.value > this.centerLower.value && node.value <= newCenterValue) {
				this.centerLower = node;
			}
			if(node.value < this.centerUpper.value && node.value >= newCenterValue) {
				this.centerUpper = node;
			}
		}
	}

	public void add(int value) {
		if(this.map.containsKey(value)) {
			this.map.get(value).num += 1;
			return;
		}
		Node added = addToList(value);
		this.map.put(value, added);
		setNewCenters(added);
	}

	public int[] getCenters() {
		int[] toReturn = {this.centerLower.value, this.centerUpper.value};
		return toReturn;
	}

	public int[] getCenterAdjacentValues() {
		int[] toReturn = {this.centerLower.previous.value, this.centerUpper.next.value};
		return toReturn;
	}

	public int totalNumNodes() {
		return this.map.size();
	}

}
