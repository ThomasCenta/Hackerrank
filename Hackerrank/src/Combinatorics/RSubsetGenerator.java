package Combinatorics;

public class RSubsetGenerator {

	private int n;
	private int r;
	private int[] current;
	
	private static int[] copy(int[] arr) {
		int[] toReturn = new int[arr.length];
		for(int i = 0; i < arr.length; i += 1) {
			toReturn[i] = arr[i];
		}
		return toReturn;
	}
	
	public RSubsetGenerator(int n, int r) {
		this.n = n;
		this.r = r;
		this.current = new int[r];
		for(int i = 0; i < r; i += 1) {
			this.current[i] = i;
		}
	}
	
	public int[] current() {
		return copy(current);
	}
	
	public int[] next() {
		for(int i = r-1; i >= 0; i -= 1) {
			int maxAllowed = (n-1)-(r-1-i);
			if(current[i] < maxAllowed) {
				current[i]+=1;
				for(int j = i+1; j < r; j += 1) {
					current[j] = current[j-1]+1;
				}
				return copy(current);
			}
		}
		return null;
	}
}
