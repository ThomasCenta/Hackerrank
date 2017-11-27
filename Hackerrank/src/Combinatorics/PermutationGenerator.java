package Combinatorics;

public class PermutationGenerator{

	private static int[] copy(int[] arr) {
		int[] toReturn = new int[arr.length];
		for(int i = 0; i < arr.length; i += 1) {
			toReturn[i] = arr[i];
		}
		return toReturn;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static void swap(boolean[] arr, int i, int j) {
		boolean temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private boolean isMobile(int i) {
		if(i == this.n-1) {
			return !this.directions[this.n-1] && this.current[this.n-1] > this.current[this.n-2];
		}else if(i == 0) {
			return this.directions[0] && this.current[1] > this.current[0];
		}else {
			if(this.directions[i]){
				return this.current[i+1] < this.current[i];
			}else {
				return this.current[i-1] < this.current[i];
			}
		}
	}
	
	private void performMove(int i) {
		if(this.directions[i]) {
			swap(this.current,i, i+1);
			swap(this.directions,i, i+1);
		}
	}
	
	private int[] current;
	//false is left, true is right
	private boolean[] directions;
	private int n;
	
	public PermutationGenerator(int n) {
		this.current = new int[n];
		for(int i = 0; i < n; i += 1) {
			this.current[i] = i;
		}
		this.n = n;
	}
	
	public int[] current() {
		return copy(this.current);
	}

	public int[] next() {                          
		int largestMobileInteger = -1;
		int largestIndex = -1;
		for(int i = 0; i < this.n; i += 1) {
			if(isMobile(i) && this.current[i] > largestMobileInteger) {
				largestMobileInteger = this.current[i];
				largestIndex = i;
			}
		}
		if(largestIndex == -1) {return null;}
		performMove(largestIndex);
		for(int i = 0; i < this.n; i += 1) {
			if(this.current[i] > largestMobileInteger) {
				this.directions[i] = !this.directions[i];
			}
		}
		return copy(this.current);
	}

}
