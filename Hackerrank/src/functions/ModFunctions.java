package functions;

/*
 * Basic functions but with mod (multiply, pow, divide, add, subtract, inverse)
 */
public class ModFunctions {
	
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

	public static int modAdd(int i, int j, int mod) {
		long i2 = i;
		long j2 = j;
		return (int)((i2+j2) % mod);
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
		pSeries[2] = ClockFunctions.clockSubtract(pSeries[0],modMultiply(pSeries[1],quotients[0], mod), mod);
		if(remainders[1] == 0) {return pSeries[2];}
		remainders[2] = remainders[0]%remainders[1];
		quotients[2] = remainders[0]/remainders[1];
		while(remainders[2] != 0) {
			ArrayFunctions.shiftLeft(remainders,1);
			ArrayFunctions.shiftLeft(quotients,1);
			ArrayFunctions.shiftLeft(pSeries,1);
			remainders[2] = remainders[0]%remainders[1];
			quotients[2] = remainders[0]/remainders[1];
			pSeries[2] = ClockFunctions.clockSubtract(pSeries[0],modMultiply(pSeries[1],quotients[0], mod), mod);
		}
		if(remainders[1] != 1) {return -1;}
		return ClockFunctions.clockSubtract(pSeries[1],modMultiply(pSeries[2],quotients[1], mod), mod);
	}

	public static int modDivide(int base, int divideBy, int mod) {
		assert divideBy != 0;
		if(divideBy > 0) {
			return modMultiply(base, modularInverse(divideBy, mod), mod);
		}else {
			return modMultiply(base, -1*modularInverse(-1*divideBy, mod), mod);
		}
	}
	
	public static int modMultiply(int i, int j, long mod){
        long i2 = i;
        long j2 = j;
        return (int)((i2*j2) % mod);
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
	
	public static void main(String[] args) {
		System.out.println(modDivide(1000000000, 1000000000, 1000000001));
	}
}
