import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import classes.SortedLinkedListCenter;
import functions.HarderMathFunctions;

public class Main {

	private static final int MOD = 1000000009;
	
	public static void main(String[] args) {

		SortedLinkedListCenter list = new SortedLinkedListCenter();
		list.add(1);
		System.out.println("center should be 1, 1: "+list.getCenters()[0]+", "+list.getCenters()[1]);
		list.add(3);
		System.out.println("center should be 1, 3: "+list.getCenters()[0]+", "+list.getCenters()[1]);
		list.add(2);
		System.out.println("center should be 2, 2: "+list.getCenters()[0]+", "+list.getCenters()[1]);
		list.add(2);
		System.out.println("center should be 2, 2: "+list.getCenters()[0]+", "+list.getCenters()[1]);
		System.out.println("center adjacents should be 1, 3: "+list.getCenterAdjacentValues()[0]+", "+list.getCenterAdjacentValues()[1]);
		
		list.add(4);
		System.out.println("center should be 2, 3: "+list.getCenters()[0]+", "+list.getCenters()[1]);
		System.out.println("center adjacents should be 1, 4: "+list.getCenterAdjacentValues()[0]+", "+list.getCenterAdjacentValues()[1]);
		list.add(10);
		System.out.println("center should be 4, 10: "+list.getCenters()[0]+", "+list.getCenters()[1]);
		System.out.println("center adjacents should be 3, 4: "+list.getCenterAdjacentValues()[0]+", "+list.getCenterAdjacentValues()[1]);
	}
}
