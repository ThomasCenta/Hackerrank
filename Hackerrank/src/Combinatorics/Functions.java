package Combinatorics;

public class Functions {

	public static long factorial(int n) {
		assert n >= 0;
		if(n <= 1) {return 1;}
		return n*factorial(n-1);
	}
	public static long permutations(int n, int r) {
		if(r > n) {return 0;}
		if(r == 0) {return 1;}
		return n*permutations(n-1, r-1);
	}
	public static long circularPermutations(int n, int r) {
		assert n >= r;
		return permutations(n, r)/r;
	}
	
	/*
	 * n choose r
	 */
	public static long numSubsets(int n, int r) {
		return permutations(n,r)/factorial(r);
	}
	
	/*
	 * arr is a multiset with each index being the number of a certain type of object.
	 * Also used for number of ways to partition n objects into boxes of size arr[i],
	 * 		where n = sum(arr)
	 * This is also n choose n1,n2,n3,...,
	 */
	public static long numMultisetPermutations(int[] arr) {
		int sum = 0;
		for(int i = 0; i < arr.length; i += 1) {
			sum += arr[i];
		}
		long toReturn = factorial(sum);
		for(int i = 0; i < arr.length; i += 1) {
			toReturn /= factorial(arr[i]);
		}
		return toReturn;
	}
}
