package classes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyTree {
	private class Node{
		public int id;
		public int level; //top node is 0, each level below is +1
		public Node parent;
		public Queue<Node> children;
		
		public Node(int id){
			this.id = id;
			this.parent = null;
			this.children = new LinkedList<Node>();
		}
	}
	
	private ArrayList<Queue<Node>> edges;
	private ArrayList<Node> nodes;
	
	public MyTree(int numNodes) {
		this.edges = new ArrayList<Queue<Node>>(numNodes);
		this.nodes = new ArrayList<Node>(numNodes);
		for(int i = 0; i < numNodes; i += 1) {
			this.edges.add(new LinkedList<Node>());
			this.nodes.add(new Node(i));
		}
	}
	
	//does not allow duplicate edges
	public void addEdge(int node1, int node2) {
		if(!this.edges.get(node1).contains(node2)) {
			this.edges.get(node1).add(this.nodes.get(node2));
			this.edges.get(node2).add(this.nodes.get(node1));
		}
	}
	
	//will override any previous family
	public void designateFamily() {
		Queue<Node> newParents = new LinkedList<Node>();
		this.nodes.get(0).level = 0;
		newParents.add(this.nodes.get(0));
		while(newParents.size() > 0) {
			Node nextParent = newParents.poll();
			for(Node node: this.edges.get(nextParent.id)) {
				if(node != nextParent.parent) {
					nextParent.children.add(node);
					node.parent = nextParent;
					node.level = nextParent.level + 1;
					newParents.add(node);
				}
			}
		}
	}
	
	public int getDegree(int node) {
		int edges = this.nodes.get(node).children.size();
		if(node != 0) {
			edges += 1;
		}
		return edges;
	}
	
	public int getNumChildren(int node) {
		return this.nodes.get(node).children.size();
	}
	
	public int getParent(int node) {
		if(node == 0) {
			return -1;
		}else {
			return this.nodes.get(node).parent.id;
		}
	}
	
	public int getTreeHeight() {
		int maxLevel = -1;
		for(int i = 0; i < this.nodes.size(); i += 1) {
			if(this.nodes.get(i).level > maxLevel) {
				maxLevel = this.nodes.get(i).level;
			}
		}
		return maxLevel+1;
	}
	
	public Queue<Integer> getLeaves(){
		Queue<Integer> toReturn = new LinkedList<Integer>();
		for(int i = 0; i < this.nodes.size(); i += 1) {
			if(this.nodes.get(i).children.size() == 0) {
				toReturn.add(i);
			}
		}
		return toReturn;
	}
	
	public ArrayList<Queue<Integer>> getLevels(){
		int treeHeight = this.getTreeHeight();
		ArrayList<Queue<Integer>> toReturn = new ArrayList<Queue<Integer>>(treeHeight);
		for(int i = 0; i < treeHeight; i += 1) {
			toReturn.add(new LinkedList<Integer>());
		}
		for(int i = 0; i < this.nodes.size(); i += 1) {
			toReturn.get(this.nodes.get(i).level).add(i);
		}
		return toReturn;
	}
	
	public Queue<Integer> getPath(int start, int end){
		Queue<Integer> pathStart = new LinkedList<Integer>();
		pathStart.add(start);
		while(this.nodes.get(start).level > this.nodes.get(end).level) {
			start = this.nodes.get(start).parent.id;
			pathStart.add(start);
		}
		Stack<Integer> pathEnd = new Stack<Integer>();
		while(this.nodes.get(end).level > this.nodes.get(start).level) {
			pathEnd.add(end);
			end = this.nodes.get(end).parent.id;
		}
		if(start != end) {
			while(start != end) {
				pathEnd.add(end);
				start = this.nodes.get(start).parent.id;
				end = this.nodes.get(end).parent.id;
				pathStart.add(start);
			}
		}
		while(pathEnd.size() > 0) {
			pathStart.add(pathEnd.pop());
		}
		return pathStart;
	}
	
	public int leastCommonAncestor(int start, int end) {
		while(this.nodes.get(start).level > this.nodes.get(end).level) {
			start = this.nodes.get(start).parent.id;
		}
		while(this.nodes.get(end).level > this.nodes.get(start).level) {
			end = this.nodes.get(end).parent.id;
		}
		while(start != end) {
			start = this.nodes.get(start).parent.id;
			end = this.nodes.get(end).parent.id;
		}
		return start;
	}
	
	
}
