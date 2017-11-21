package hardermathfunctionstests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import functions.HarderMathFunctions;
import functions.ModFunctions;

public class BernoulliModTests {

	private static final int[] numerators = {1,1,1,0,-1,0,1,0,-1,0,5,0,-691,0,7,0,-3617,0,43867,0,-174611};
	private static final int[] denominators = {1,2,6,1,30,1,42,1,30,1,66,1,2730,1,6,0,510,1,798,1,330};
	
	@Test
	public void testValuesLessThan21LargeMod() {
		int mod = 1000000009; // prime mod for guaranteed answer
		for(int n = 0; n <= 20; n += 1) {
			int expectedNumerator = numerators[n] % mod;
			int expectedDenominator = ModFunctions.modularInverse(denominators[n], mod);
			int expected = ModFunctions.modMultiply(expectedNumerator, expectedDenominator, mod);
			int actual = HarderMathFunctions.bernoulliMod(n, mod);
			assertEquals(expected, actual);
		}
	}
}
