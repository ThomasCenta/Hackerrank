package Combinatorics;

import java.util.LinkedList;
import java.util.Queue;

public class SubsetGenerator {

	private static String reverse(String str) {
		StringBuilder strB = new StringBuilder();
		for(int i = str.length()-1; i >= 0; i -= 1) {
			strB.append(str.charAt(i));
		}
		return strB.toString();
	}
	
	private int n;
	private long current;
	
	/*
	 * Generates all subsets of 0,1,2,3,...,n
	 * returns null for next() if final subset was generated
	 */
	public SubsetGenerator(int n) {
		assert n <= 63;
		this.n = n;
		this.current = 0;
	}
	
	
	public Queue<Integer> current(){
		Queue<Integer> toReturn = new LinkedList<Integer>();
		String currentString = reverse(Long.toBinaryString(this.current));
		for(int i = 0; i < currentString.length(); i += 1) {
			if(currentString.charAt(i) == '1') {
				toReturn.add(i);
			}
		}
		return toReturn;
	}
	
	public Queue<Integer> next(){
		if(current == Math.pow(2, this.n)-1) {return null;}
		current += 1;
		return current();
	}
}
