package functions;
import java.math.BigInteger;

import classes.BigFraction;

public class HarderMathFunctions {
	public static int binomialCoefficientMod(int n, int k, int mod) {
		assert k < n;
		assert n > 0;
		if(k == 0)
			return 1;
		if(k > n/2)
			return binomialCoefficientMod(n,n-k, mod);
		int next = binomialCoefficientMod(n-1,k-1, mod);
		int next2 = ModFunctions.modDivide(next, k, mod);
		return ModFunctions.modMultiply(n, next2 , mod);
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
				int jPowN = ModFunctions.powMod(j, n, mod);
				if (j % 2 == 0) {
					jSum = ModFunctions.modAdd(jSum, ModFunctions.modMultiply(jPowN,bInt, mod), mod);
				} else {
					jSum = ModFunctions.modSubtract(jSum, ModFunctions.modMultiply(jPowN,bInt, mod), mod);
				}
				int multiplyBy = ModFunctions.modDivide(ModFunctions.modSubtract(k,j,mod),ModFunctions.modAdd(j,1,mod), mod);
				bInt = ModFunctions.modMultiply(bInt,multiplyBy, mod);
			}
			result = ModFunctions.modAdd(result, ModFunctions.modDivide(jSum, ModFunctions.modAdd(k,1,mod), mod), mod);
			//System.out.println(result);
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
			result.simplify();
			//System.out.println(result+" "+result.modForm(11).toString());
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
			int summand = ModFunctions.powMod(-1, kroneckerDelta(k, p), mod);
			summand = ModFunctions.modMultiply(summand, binomialCoefficientMod(p+1, k, mod), mod);
			summand = ModFunctions.modMultiply(summand, bernoulliMod(p+1-k, mod), mod);
			summand = ModFunctions.modMultiply(summand, ModFunctions.powMod(n, k, mod), mod);
			sum = ModFunctions.modAdd(summand, sum, mod);
		}
		return ModFunctions.modDivide(sum,(p+1), mod);
	}
	
	public static int brutePowerSumMod(int begin, int end, int p, int mod) {
		int sum = 0;
		for(int i = begin; i <= end; i += 1) {
			sum = ModFunctions.modAdd(sum, ModFunctions.powMod(i, p, mod), mod);
		}
		return sum;
	}
	
	public static void main(String[] args) {
			int mod = 11;
			//System.out.println(bernoulli(4)+ " "+bernoulli(4).modForm(mod));
			System.out.println(bernoulliMod(4,mod));
		
	}
}
