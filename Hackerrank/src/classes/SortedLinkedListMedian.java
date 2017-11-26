package classes;

public class SortedLinkedListMedian {

	private class Node{
		public Node previous;
		public Node next;
		public int value;
	}
	
	//keep track of the median value
	private Node medianLower;
	private Node medianUpper;
	private Node head;
	private Node end;
	
	//requires at least one value
	public SortedLinkedListMedian() {
		this.head = new Node();
		this.end = new Node();
		this.head.previous = null;
		this.head.next = this.end;
		this.end.previous = this.head;
		this.end.next = null;
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
	
	private void setNewMedians(Node node) {
		if(medianLower == null) {
			this.medianLower = node;
			this.medianUpper = node;
		}else if(medianLower == medianUpper) {
			if(node.value <= medianLower.value) {
				medianLower = medianLower.previous;
			}else {
				medianUpper = medianUpper.next;
			}
		}else {
			if(node.value > medianLower.value) {
				medianLower = medianLower.next;
			}
			if(node.value <= medianUpper.value) {
				medianUpper = medianUpper.previous;
			}
		}
	}
	
	public void add(int value) {
		Node added = addToList(value);
		setNewMedians(added);
	}
	
	private Node removeNode(int value) {
		
		Node currentNode = this.head.next;
		while(currentNode.value != value ) {
			currentNode = currentNode.next;
		}
		currentNode.previous.next = currentNode.next;
		currentNode.next.previous = currentNode.previous;
		return currentNode;
	}
	
	public void remove(int value) {
		Node removed = removeNode(value);
		if(this.medianLower == this.medianUpper) {
			if(removed == medianLower) {
				medianLower = removed.previous;
				medianUpper = removed.next;
			}else {
				if(removed.value <= medianLower.value) {
					medianUpper = medianLower.next;
				}else {
					medianLower = medianLower.previous;
				}
			}
		}else {
			if(removed == medianLower) {
				medianLower = medianUpper;
			}else if(removed == medianUpper) {
				medianUpper = medianLower;
			}else {
				if(removed.value <= medianLower.value) {
					medianLower = medianUpper;
				}else {
					medianUpper = medianLower;
				}
			}
		}
	}
	
	public int getMedian() {
		return (this.medianUpper.value+this.medianLower.value)/2;
	}
	
}
