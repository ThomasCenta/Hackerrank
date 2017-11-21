package MyTreeTests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Queue;

import org.junit.Test;

import classes.MyTree;

public class MyTreeTests {

	private boolean contains(Queue<Integer> queue, int toFind) {
		for(int i = 0; i < queue.size(); i += 1) {
			if(queue.peek() == toFind) {
				return true;
			}
			queue.add(queue.poll());
		}
		return false;
	}
	
	@Test
	public void testLevels() {
		MyTree tree = new MyTree(5);
		for(int i = 0; i < 4; i += 1) {
			tree.addEdge(i, i+1);
		}
		tree.designateFamily();
		ArrayList<Queue<Integer>> levels = tree.getLevels();
		for(int i = 0; i < 5; i += 1) {
			assertEquals(1, levels.get(i).size());
			assertEquals(i, (int)levels.get(i).peek());
		}
	}
	
	@Test
	public void testLeaves() {
		MyTree tree = new MyTree(5);
		for(int i = 0; i < 4; i += 1) {
			tree.addEdge(i, i+1);
		}
		tree.designateFamily();
		Queue<Integer> leaves = tree.getLeaves();
		assertEquals(1, leaves.size());
		assertEquals(4, (int)leaves.peek());
	}
	
	@Test
	public void testLeaves2() {
		MyTree tree = new MyTree(5);
		tree.addEdge(0, 1);
		tree.addEdge(0, 2);
		tree.addEdge(1, 3);
		tree.addEdge(1, 4);
		tree.designateFamily();
		Queue<Integer> leaves = tree.getLeaves();
		assertEquals(3, leaves.size());
		assertTrue(contains(leaves, 2));
		assertTrue(contains(leaves, 3));
		assertTrue(contains(leaves, 4));
	}
	
	@Test
	public void testPath() {
		MyTree tree = new MyTree(5);
		tree.addEdge(0, 1);
		tree.addEdge(0, 2);
		tree.addEdge(1, 3);
		tree.addEdge(1, 4);
		tree.designateFamily();
		Queue<Integer> result = tree.getPath(2, 3);
		assertEquals(4, result.size());
		assertEquals(2, (int)result.poll());
		assertEquals(0, (int)result.poll());
		assertEquals(1, (int)result.poll());
		assertEquals(3, (int)result.poll());
	}
	
	@Test
	public void testPath2() {
		MyTree tree = new MyTree(5);
		tree.addEdge(0, 1);
		tree.addEdge(0, 2);
		tree.addEdge(1, 3);
		tree.addEdge(1, 4);
		tree.designateFamily();
		Queue<Integer> result = tree.getPath(3, 2);
		assertEquals(4, result.size());
		assertEquals(3, (int)result.poll());
		assertEquals(1, (int)result.poll());
		assertEquals(0, (int)result.poll());
		assertEquals(2, (int)result.poll());
	}
	
	@Test
	public void testPath3() {
		MyTree tree = new MyTree(5);
		tree.addEdge(0, 1);
		tree.addEdge(0, 2);
		tree.addEdge(1, 3);
		tree.addEdge(1, 4);
		tree.designateFamily();
		Queue<Integer> result = tree.getPath(1, 2);
		assertEquals(3, result.size());
		assertEquals(1, (int)result.poll());
		assertEquals(0, (int)result.poll());
		assertEquals(2, (int)result.poll());
	}
	
	@Test
	public void testPath4() {
		MyTree tree = new MyTree(5);
		tree.addEdge(0, 1);
		tree.addEdge(0, 2);
		tree.addEdge(1, 3);
		tree.addEdge(1, 4);
		tree.designateFamily();
		Queue<Integer> result = tree.getPath(0,0);
		assertEquals(1, result.size());
		assertEquals(0, (int)result.poll());
	}
	
	@Test
	public void testLeastCommonAncestor() {
		MyTree tree = new MyTree(5);
		tree.addEdge(0, 1);
		tree.addEdge(0, 2);
		tree.addEdge(1, 3);
		tree.addEdge(1, 4);
		tree.designateFamily();
		assertEquals(1, tree.leastCommonAncestor(3,4));
		assertEquals(1, tree.leastCommonAncestor(1,4));
		assertEquals(1, tree.leastCommonAncestor(1,1));
		assertEquals(1, tree.leastCommonAncestor(3,1));
		assertEquals(0, tree.leastCommonAncestor(3,2));
		assertEquals(0, tree.leastCommonAncestor(3,0));
	}

}
