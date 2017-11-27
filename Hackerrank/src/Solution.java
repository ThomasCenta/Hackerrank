import java.io.*;
import java.util.*;

public class Solution {

	private static int modSubtract(int i, int j) {
		return (((i-j) % 101) + 101) % 101;
	}
	private static int modAdd(int i, int j) {
		return (i+j) % 101;
	}

	private static int modMultiply(int i, int j) {
		return (i*j)%101;
	}

	private static class Struct{
		public int i;
		public char c;
		public Struct(int i, char c) {
			this.i = i;
			this.c = c;
		}
	}
	
	private static Struct getPreviousOperation(boolean[][] table, int[] arr, int currentIndex, int currentNumber) {
		for(int i = 0; i < 101; i += 1) {
			if(table[i][currentIndex-1]) {
				if(modMultiply(i,arr[currentIndex]) == currentNumber) {return new Struct(i,'*');}
				if(modAdd(i,arr[currentIndex]) == currentNumber) {return new Struct(i,'+');}
				if(modSubtract(i,arr[currentIndex]) == currentNumber) {return new Struct(i,'-');}
			}
		}
		return null;
	}
	
	private static Stack<Character> getOperations(int[] arr){
		boolean[][] table = new boolean[101][arr.length];
		table[arr[0]][0] = true;
		for(int i = 1; i < arr.length; i += 1) {
			for(int j = 0; j < 101; j += 1) {
				if(table[j][i-1]) {
					table[modMultiply(j,arr[i])][i] = true;
					table[modAdd(j,arr[i])][i] = true;
					table[modSubtract(j,arr[i])][i] = true;
				}
			}
		}
		Stack<Character> toReturn = new Stack<Character>();
		int currentNumber = 0;
		for(int i = arr.length-1; i >= 1; i -= 1) {
			Struct struct = getPreviousOperation(table, arr, i, currentNumber);
			currentNumber = struct.i;
			toReturn.push(struct.c);
		}
		assert currentNumber == arr[0];
		return toReturn;
	}

	private static void printSolution(int[] arr, Stack<Character> operations){
		StringBuilder str = new StringBuilder();
		str.append(arr[0]);
		for(int i = 1; i < arr.length; i += 1){
			str.append(operations.pop());
			str.append(arr[i]);
		}
		System.out.println(str);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i += 1){
			arr[i] = in.nextInt();
		}
		Stack<Character> operations = getOperations(arr);
		printSolution(arr, operations);
	}
}