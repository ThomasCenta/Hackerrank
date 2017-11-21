package Functions;

public class ClockFunctions {
	public static int clockSubtract(int subtractFrom, int subtractThis, int mod) {
		long i2 = subtractFrom;
		long j2 = subtractThis;
		return (int)((((i2-j2) % mod)+mod) % mod);
	}
}
