package ModFunctionTests;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import functions.ModFunctions;

public class modAddTests {

	@Test
	public void testBothMax(){
		int addOne = Integer.MAX_VALUE;
		int addTwo = Integer.MAX_VALUE;
		int mod = Integer.MAX_VALUE;
		int expected = 0;
		assertEquals(expected, ModFunctions.modAdd(addOne, addTwo, mod));
	}
	
	@Test
	public void testBothMin(){
		int addOne = Integer.MIN_VALUE;
		int addTwo = Integer.MIN_VALUE;
		int mod = Integer.MAX_VALUE;
		int expected = -2;
		assertEquals(expected, ModFunctions.modAdd(addOne, addTwo, mod));
	}
}
