import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		System.out.println((new BigInteger(""+-1)).mod(new BigInteger(""+11)));
	}
}
