import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Queue;

import Combinatorics.RSubsetGenerator;
import Combinatorics.SubsetGenerator;
import classes.SortedLinkedListCenter;
import functions.HarderMathFunctions;

public class Main {

	private static void printArray(int[] arr) {
		for(int i: arr) {
			System.out.print(i+" ");
		}
		System.out.println("");
	}
	public static void main(String[] args) {
		RSubsetGenerator gen = new RSubsetGenerator(10,5);
		printArray(gen.current());
		int count = 1;
		while(gen.next() != null) {
			printArray(gen.current());
			count += 1;
		}
		System.out.println("count: "+count);
		
	}
}
