import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;



/*
 * T is the node class to be used
 */
public class MyGraph {
	
	private final class MyEntry<K, V> implements Map.Entry<K, V> {
	    private final K key;
	    private V value;

	    public MyEntry(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }

	    @Override
	    public K getKey() {
	        return key;
	    }

	    @Override
	    public V getValue() {
	        return value;
	    }

	    @Override
	    public V setValue(V value) {
	        V old = this.value;
	        this.value = value;
	        return old;
	    }
	}
	
	
	private final class MyEntrySort implements Comparator<MyEntry<Integer, Integer>>{
		@Override
		public int compare(MyEntry<Integer, Integer> o1, MyEntry<Integer, Integer> o2) {
			if(o1.getValue() < o2.getValue()) {
				return -1;
			}
			if(o1.getValue() > o2.getValue()) {
				return 1;
			}
			return 0;
		}
	}
	
	private final class EdgeWeightSort implements Comparator<Edge>{
		@Override
		public int compare(Edge o1, Edge o2) {
			if(o1.weight < o2.weight) {
				return -1;
			}if(o1.weight > o2.weight) {
				return 1;
			}
			return 0;
		}
	}
	
	private class Edge{
		public int startNode;
		public int endNode;
		public int weight;
		
		public Edge(int startNode, int endNode, int weight) {
			this.startNode = startNode;
			this.endNode = endNode;
			this.weight = weight;
		}
	}
	
	private class Node{
		public boolean visited;
		public boolean set;
	}
	
	//edgeMatrix[0][1] == true ==> edge going from node 0 to node 1
	private boolean[][] edgeMatrix;
	private int[][] edgeWeights;
	private ArrayList<Queue<Integer>> edgeQueue;
	private ArrayList<Node> nodes;
	
	/*
	 * Pass an array of nodes. Nodes can later be accessed by their index number
	 */
	public MyGraph(int numNodes) {
		this.nodes = new ArrayList<Node>(numNodes);
		this.edgeMatrix = new boolean[numNodes][numNodes];
		this.edgeWeights = new int[numNodes][numNodes];
		this.edgeQueue = new ArrayList<Queue<Integer>>(numNodes);
		for(int i = 0; i < numNodes; i += 1) {
			this.edgeQueue.add(i, new LinkedList<Integer>());
			this.nodes.add(new Node());
		}
	}
	
	//will only add if not added already
	public void addEdge(int startNode, int endNode, int weight) {
		if(!this.edgeMatrix[startNode][endNode]) {
			this.edgeMatrix[startNode][endNode] = true;
			this.edgeWeights[startNode][endNode] = weight;
			this.edgeQueue.get(startNode).add(endNode);
		}
	}
	
	//only sets it if edge already exists
	public void setEdgeWeight(int startNode, int endNode, int weight) {
		if(this.edgeMatrix[startNode][endNode]) {
			this.edgeWeights[startNode][endNode] = weight;
		}
	}
	
	public boolean hasEdge(int startNode, int endNode) {
		return this.edgeMatrix[startNode][endNode];
	}
	
	//if there is no edge between these nodes, it returns 0
	public int getEdgeWeight(int startNode, int endNode) {
		return this.edgeWeights[startNode][endNode];
	}
	
	/*
	 * Returns Integer.MAX_VALUE if there is no way to reach endNode from startNode
	 */
	public int dijkstrasMinimalPath(int startNode, int endNode) {
		ArrayList<MyEntry<Integer, Integer>> values = new ArrayList<MyEntry<Integer, Integer>>(this.nodes.size());
		PriorityQueue<MyEntry<Integer, Integer>> vertexQueue = new PriorityQueue<MyEntry<Integer, Integer>>(new MyEntrySort());
		for(int i = 0; i < this.nodes.size(); i += 1) {
			this.nodes.get(i).visited = false;
			this.nodes.get(i).set = false;
			MyEntry<Integer, Integer> entry = new MyEntry<Integer, Integer>(i, Integer.MAX_VALUE);
			if(i == startNode) {
				entry.setValue(0);
			}
			values.add(entry);
			vertexQueue.add(entry);
		}
		this.nodes.get(startNode).visited = true;
		while(vertexQueue.size() > 0) {
			int minNode = vertexQueue.poll().getKey();
			if(minNode == endNode) {
				return values.get(minNode).getValue();
			}
			//basically all that are left are unreachable nodes
			if(!this.nodes.get(minNode).visited) {
				return Integer.MAX_VALUE;
			}
			this.nodes.get(minNode).set = true;
			int base = values.get(minNode).getValue();
			for(int neighbor: this.edgeQueue.get(minNode)) {
				if(!this.nodes.get(neighbor).set) {
					int newDist = base+this.edgeWeights[minNode][neighbor];
					if(!this.nodes.get(neighbor).visited || newDist < values.get(neighbor).getValue()) {
						this.nodes.get(neighbor).visited = true;
						vertexQueue.remove(values.get(neighbor));
						values.get(neighbor).setValue(newDist);
						vertexQueue.add(values.get(neighbor));
					}
				}
			}
		}
		return -1; // should never reach here unless endNode is out of bounds
	}
	
	/*
	 * Integer.MAX_VALUE if there is no way to reach a node from startNode
	 */
	public int[] dijkstrasMinimalPaths(int startNode) {
		int[] toReturn = new int[this.nodes.size()];
		ArrayList<MyEntry<Integer, Integer>> values = new ArrayList<MyEntry<Integer, Integer>>(this.nodes.size());
		PriorityQueue<MyEntry<Integer, Integer>> vertexQueue = new PriorityQueue<MyEntry<Integer, Integer>>(new MyEntrySort());
		for(int i = 0; i < this.nodes.size(); i += 1) {
			this.nodes.get(i).set = false;
			toReturn[i] = Integer.MAX_VALUE;
			MyEntry<Integer, Integer> entry = new MyEntry<Integer, Integer>(i, Integer.MAX_VALUE);
			if(i == startNode) {
				entry.setValue(0);
				vertexQueue.add(entry);
			}
			values.add(entry);
		}
		while(vertexQueue.size() > 0) {
			int minNode = vertexQueue.poll().getKey();
			toReturn[minNode] = values.get(minNode).getValue();
			this.nodes.get(minNode).set = true;
			int base = values.get(minNode).getValue();
			for(int neighbor: this.edgeQueue.get(minNode)) {
				if(!this.nodes.get(neighbor).set) {
					int newDist = base+this.edgeWeights[minNode][neighbor];
					if(newDist < values.get(neighbor).getValue()) {
						vertexQueue.remove(values.get(neighbor));
						values.get(neighbor).setValue(newDist);
						vertexQueue.add(values.get(neighbor));
					}
				}
			}
		}
		return toReturn;
	}
	
	//returns queue of arrays with index 0 being the startNode, index 1 being the endNode
	//and index 2 being the edge weight
	public Queue<int[]> primsMSTChosenNode(int node){
		Queue<int[]> toReturn = new LinkedList<int[]>();
		for(int i = 0; i < this.nodes.size(); i += 1) {
			this.nodes.get(i).visited = false;
		}
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<Edge>(new EdgeWeightSort());
		for(int neighbor: this.edgeQueue.get(node)) {
			edgeQueue.add(new Edge(node, neighbor, this.edgeWeights[node][neighbor]));
		}
		this.nodes.get(node).visited = true;
		while(edgeQueue.size() > 0) {
			Edge next = edgeQueue.poll();
			if(!this.nodes.get(next.endNode).visited) {
				this.nodes.get(next.endNode).visited = true;
				int[] edgeArr = {next.startNode, next.endNode, next.weight};
				toReturn.add(edgeArr);
				for(int neighbor: this.edgeQueue.get(next.endNode)) {
					edgeQueue.add(new Edge(next.endNode, neighbor, this.edgeWeights[next.endNode][neighbor]));
				}
			}
		}
		return toReturn;
	}
	
	
	public Queue<Integer> islandSizes(){
		Queue<Integer> toReturn = new LinkedList<Integer>();
		int[] visited = new int[this.nodes.size()];
		int currentlyChecking = 0;
		while(currentlyChecking < this.nodes.size()) {
			visited[currentlyChecking] = 1;
			Queue<Integer> currentIslandCheck = new LinkedList<Integer>();
			currentIslandCheck.add(currentlyChecking);
			int islandSize = 0;
			while(currentIslandCheck.size() > 0) {
				islandSize += 1;
				int base = currentIslandCheck.poll();
				Queue<Integer> queue = this.edgeQueue.get(base);
				for(int i = 0; i < queue.size(); i += 1) {
					int next = queue.peek();
					if(visited[next] == 0) {
						visited[next] = 1;
						currentIslandCheck.add(next);
					}
					queue.add(queue.poll());
				}
			}
			while(currentlyChecking < this.nodes.size() && visited[currentlyChecking] == 1) {
				currentlyChecking += 1;
			}
			toReturn.add(islandSize);
		}
		return toReturn;
	}
	
}
