import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

public class Functions {

	public static int modMultiply(int i, int j, int mod){
		long i2 = i;
		long j2 = j;
		int toReturn = (int)((i2*j2) % mod);
		return toReturn;
	}

	public static int modSubtract(int subtractFrom, int subtractThis, int mod) {
		long i2 = subtractFrom;
		long j2 = subtractThis;
		return (int)((i2-j2) % mod);
	}

	public static int clockSubtract(int subtractFrom, int subtractThis, int mod) {
		long i2 = subtractFrom;
		long j2 = subtractThis;
		return (int)((((i2-j2) % mod)+mod) % mod);
	}

	public static int modAdd(int i, int j, int mod) {
		long i2 = i;
		long j2 = j;
		return (int)((i2+j2) % mod);
	}

	public static void shiftLeft(int[] arr, int shiftLeftBy) {
		for(int i = 0; i < arr.length-shiftLeftBy; i += 1) {
			arr[i] = arr[i+shiftLeftBy];
		}
	}

	/*
	 * returns -1 if no modular inverse
	 * @requires n > 0
	 */
	public static int modularInverse(int n, int mod) {
		assert n > 0;

		n = n % mod;
		if(n == 1) {return 1;}
		int[] pSeries = {0,1,0};
		int[] quotients = {0,0,0};
		int[] remainders = new int[3];
		quotients[0] = mod/n;
		remainders[0] = mod%n;
		if(remainders[0] == 0) {return -1;}
		quotients[1] = n/remainders[0];
		remainders[1] = n%remainders[0];
		pSeries[2] = clockSubtract(pSeries[0],modMultiply(pSeries[1],quotients[0], mod), mod);
		if(remainders[1] == 0) {return pSeries[2];}
		remainders[2] = remainders[0]%remainders[1];
		quotients[2] = remainders[0]/remainders[1];
		while(remainders[2] != 0) {
			shiftLeft(remainders,1);
			shiftLeft(quotients,1);
			shiftLeft(pSeries,1);
			remainders[2] = remainders[0]%remainders[1];
			quotients[2] = remainders[0]/remainders[1];
			pSeries[2] = clockSubtract(pSeries[0],modMultiply(pSeries[1],quotients[0], mod), mod);
		}
		if(remainders[1] != 1) {return -1;}
		return clockSubtract(pSeries[1],modMultiply(pSeries[2],quotients[1], mod), mod);
	}

	public static int modDivide(int base, int divideBy, int mod) {
		assert divideBy != 0;
		if(divideBy > 0) {
			return modMultiply(base, modularInverse(divideBy, mod), mod);
		}else {
			return modMultiply(base, -1*modularInverse(-1*divideBy, mod), mod);
		}
	}
	
	static String toBinaryString(int n) {
		StringBuilder str = new StringBuilder(32);
		str.append('0');
		int bitValue = 1073741824;
		while(bitValue > 0) {
			if(n >= bitValue) {
				n -= bitValue;
				str.append('1');
			}else {
				str.append('0');
			}
			bitValue /= 2;
		}
		return str.toString();
	}
	
	public static long pow(long base, int power) {
		if(power == 0) {
			return 1;
		}
		long half = pow(base, power/2);
		long toReturn = half*half;
		if(power % 2 == 1) {
			toReturn *= base;
		}
		return toReturn;
	}
	
	public static int maxContiguousSubarray(int[] arr) {
		int prevMax = 0;
		int max = 0;
		for(int i = 0; i < arr.length; i += 1) {
			int alt = arr[i];
			if(prevMax > 0) {
				alt += prevMax;
			}
			prevMax = alt;
			if(max < alt) {
				max = alt;
			}
		}
		return max;
	}
	
	public static String arrayToString(int[] arr) {
		if(arr.length == 0) {
			return "[]";
		}
		StringBuilder strBld = new StringBuilder();
		strBld.append("[");
		strBld.append(arr[0]);
		for(int i = 1; i < arr.length; i += 1) {
			strBld.append(", ");
			strBld.append(arr[i]);
		}
		strBld.append("]");
		return strBld.toString();
	}
	
	public static String matrixToString(int[][] arr) {
		if(arr.length == 0) {
			return "[]";
		}
		StringBuilder strBld = new StringBuilder();
		strBld.append("[");
		strBld.append(arrayToString(arr[0]));
		for(int i = 1; i < arr.length; i += 1) {
			strBld.append("\n ");
			strBld.append(arrayToString(arr[i]));
		}
		strBld.append("]");
		return strBld.toString();
	}
	
	public static String queueToString(Queue<int[]> queue) {
		if(queue.size() == 0) {
			return "{}";
		}
		StringBuilder strBld = new StringBuilder();
		strBld.append("{");
		strBld.append(arrayToString(queue.peek()));
		for(int i = 1; i < queue.size(); i += 1) {
			strBld.append(", ");
			strBld.append(arrayToString(queue.peek()));
			queue.add(queue.poll());
		}
		strBld.append("}");
		return strBld.toString();
	}
	
	public static int modMultiply(int i, int j, long mod){
        long i2 = i;
        long j2 = j;
        return (int)((i2*j2) % mod);
    }
    
	public static int brutePowerSumMod(int begin, int end, int p, int mod) {
		int sum = 0;
		for(int i = begin; i <= end; i += 1) {
			sum = modAdd(sum, powMod(i, p, mod), mod);
		}
		return sum;
	}
	
	public static int powMod(int n, int pow, int mod){
		assert pow >= 0;

		if(pow == 0){
			return 1;
		}if(pow == 1){
			return n;
		}
		int half = powMod(n,pow/2, mod);
		int halfSquared = modMultiply(half, half, mod);
		if(pow % 2 == 1){
			return modMultiply(halfSquared, n%mod, mod);
		}else{
			return halfSquared;
		}
	}
	
	public static int powMod(long n, int pow, int mod){
		assert pow >= 0;

		if(pow == 0){
			return 1;
		}if(pow == 1){
			return (int)(n%mod);
		}
		int half = powMod(n,pow/2, mod);
		int halfSquared = modMultiply(half, half, mod);
		if(pow % 2 == 1){
			return modMultiply(halfSquared, (int)(n%mod), mod);
		}else{
			return halfSquared;
		}
	}
	
	public static int binomialCoefficientMod(int n, int k, int mod) {
		assert k < n;
		assert n > 0;
		if(k == 0)
			return 1;
		if(k > n/2)
			return binomialCoefficientMod(n,n-k, mod);
		int next = binomialCoefficientMod(n-1,k-1, mod);
		int next2 = modDivide(next, k, mod);
		return modMultiply(n, next2 , mod);
	}
	
	public static int bernoulliMod(int n, int mod) {
		if(n % 2 == 1 && n > 10) {
			return 0;
		}
		int result = 0;
		for (int k = 0; k <= n; k++) {
			int jSum = 0;
			int bInt = 1;
			for (int j = 0; j <= k; j++) {
				int jPowN = powMod(j, n, mod);
				if (j % 2 == 0) {
					jSum = modAdd(jSum, modMultiply(jPowN,bInt, mod), mod);
				} else {
					jSum = modSubtract(jSum, modMultiply(jPowN,bInt, mod), mod);
				}
				bInt = modDivide(modMultiply(bInt,modSubtract(k,j,mod),mod),modAdd(j,1,mod),mod);
			}
			result = modAdd(result, modDivide(jSum, modAdd(k,1,mod), mod), mod);
		}
		return result;
	}
	
	public static BigFraction bernoulli(int n) {
		if(n % 2 == 1 && n > 10) {
			return new BigFraction(0,1);
		}
		BigFraction result = new BigFraction(0,1);
		for (int k = 0; k <= n; k++) {
			BigFraction jSum = new BigFraction(0,1);
			BigInteger bInt = BigInteger.ONE;
			for (int j = 0; j <= k; j++) {
				BigInteger jPowN = (new BigInteger("" + j))
						.pow(n);
				if (j % 2 == 0) {
					jSum = jSum.add(bInt.multiply(jPowN));
				} else {
					jSum = jSum.subtract(bInt.multiply(jPowN));
				}

				/* update binomial(k,j) recursively
				 */
				bInt = bInt.multiply(new BigInteger("" + (k - j))).
						divide(new BigInteger("" + (j + 1)));
			}
			result = result.add(jSum.divide(new BigInteger("" + (k + 1))));
			//System.out.println("before: "+result);
			result.simplify();
			//System.out.println("after: "+result);
		}
		return result;
	}
	
	public static int kroneckerDelta(int i, int j) {
		if(i != j) {return 0;}
		return 1;
	}
	
	/*
	 * @requires p > 0 && n > 0 && mod > 0
	 * Returns the sum(1^p+2^p+...n^p) mod mod
	 */
	public static int faulhaberMod(long n, int p, int mod) {
		assert n > 0;
		assert p > 0;

		int sum = 0;
		for(int k = 1; k <= p+1; k += 1) {
			int summand = powMod(-1, kroneckerDelta(k, p), mod);
			summand = modMultiply(summand, binomialCoefficientMod(p+1, k, mod), mod);
			summand = modMultiply(summand, bernoulliMod(p+1-k, mod), mod);
			summand = modMultiply(summand, powMod(n, k, mod), mod);
			sum = modAdd(summand, sum, mod);
		}
		return modDivide(sum,(p+1), mod);
	}
	
	//inclusive lowerIndex, exclusive higherindex, middleIndex belongs to upper portion
	public static void merge(int[] arr, int lowerIndex, int middleIndex, int higherIndex) {
		int[] helper = new int[higherIndex-lowerIndex];
		int j = lowerIndex;
		for(int i = 0; i < helper.length; i += 1) {
			helper[i] = arr[j];
			j += 1;
		}
		int i = lowerIndex;
		int ih = 0;
		int jh = middleIndex-lowerIndex;
		int mhi = middleIndex-lowerIndex;
		while(ih < mhi && jh < helper.length) {
			if(helper[ih] <= helper[jh]) {
				arr[i] = helper[ih];
				ih += 1;
				i += 1;
			}else {
				arr[i] = helper[jh];
				jh += 1;
				i += 1;
			}
		}
		while(ih < mhi) {
			arr[i] = helper[ih];
			ih += 1;
			i += 1;
		}
		while(jh < helper.length) {
			arr[i] = helper[jh];
			jh += 1;
			i += 1;
		}
	}
	
	//inclusive lowerIndex, exclusive higherIndex
	public static void mergeSort(int[] arr,int lowerIndex, int higherIndex) {
		if(higherIndex-lowerIndex <= 1) {
			return;
		}
		int middleIndex = (higherIndex+lowerIndex)/2;
		mergeSort(arr,lowerIndex,middleIndex);
		mergeSort(arr,middleIndex,higherIndex);
		merge(arr,lowerIndex,middleIndex,higherIndex);
	}
	//higherIndex is exclusive, lowerIndex inclusive
	public static int binarySearch(int[] arr, int toFind, int lowerIndex, int higherIndex) {
		if(higherIndex <= lowerIndex+1) {
			if(arr[lowerIndex] == toFind) {
				return lowerIndex;
			}else {
				return -1;
			}
		}
		int middleIndex = (higherIndex+lowerIndex)/2;
		if(arr[middleIndex] <= toFind) {
			return binarySearch(arr, toFind, middleIndex, higherIndex);
		}else {
			return binarySearch(arr, toFind, lowerIndex, middleIndex);
		}
	}
	
	
	public static void printArr(int[] arr) {
		System.out.print(arr[0]);
		for(int i = 1;i < arr.length; i+= 1) {
			System.out.print(" "+arr[i]);
		}
		System.out.print("\n");
	}
	
	//note that x is index at 0 from left and y is indexed at 0 from top
	public static Queue<int[]> getPossibleKnightMoves(int boardWidth, int boardHeight, int knightX, int knightY, int initialLength, int secondLength){
		Queue<int[]> toReturn = new LinkedList<int[]>();
		if(knightY-initialLength >= 0 && knightX-secondLength >= 0) {
			int[] toAdd = {knightX-secondLength, knightY-initialLength};
			toReturn.add(toAdd);
		}
		if(knightY-initialLength >= 0 && knightX+secondLength < boardWidth) {
			int[] toAdd = {knightX+secondLength, knightY-initialLength};
			toReturn.add(toAdd);
		}
		if(knightY+initialLength < boardHeight && knightX-secondLength >= 0) {
			int[] toAdd = {knightX-secondLength, knightY+initialLength};
			toReturn.add(toAdd);
		}
		if(knightY+initialLength < boardHeight && knightX+secondLength < boardWidth) {
			int[] toAdd = {knightX+secondLength, knightY+initialLength};
			toReturn.add(toAdd);
		}
		if(knightX+initialLength < boardWidth && knightY+secondLength < boardHeight) {
			int[] toAdd = {knightX+initialLength, knightY+secondLength};
			toReturn.add(toAdd);
		}
		if(knightX+initialLength < boardWidth && knightY-secondLength >= 0) {
			int[] toAdd = {knightX+initialLength, knightY-secondLength};
			toReturn.add(toAdd);
		}
		if(knightX-initialLength >= 0 && knightY+secondLength < boardHeight) {
			int[] toAdd = {knightX-initialLength, knightY+secondLength};
			toReturn.add(toAdd);
		}
		if(knightX-initialLength >= 0 && knightY-secondLength >= 0) {
			int[] toAdd = {knightX-initialLength, knightY-secondLength};
			toReturn.add(toAdd);
		}
		return toReturn;
	}
	
	public static void main(String[] args) {
		System.out.println(toBinaryString(2000000000));
	}
	
}
