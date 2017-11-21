package classes;
import java.math.BigInteger;

public class BigFraction {
	protected BigInteger num;
	protected BigInteger den;

	public void simplify() {
		// Divide a and b by their gcd
		BigInteger c = this.num.abs();
		BigInteger d = this.den.abs();
		while(d.compareTo(BigInteger.ZERO) != 0){
			BigInteger newD = c.mod(d);
			c = d;
			d = newD;
		}
		this.num = this.num.divide(c);
		this.den = this.den.divide(c);
	}

	public BigFraction(int numerator, int denominator){
		this.num = new BigInteger(numerator+"");
		this.den = new BigInteger(denominator+"");
		//simplify();
	}

	public BigFraction(BigInteger num, BigInteger den) {
		this.num = num;
		this.den = den;
		//simplify();
	}

	public BigFraction add(BigInteger toAdd) {
		BigInteger newNumerator =this.num.add(toAdd.multiply(this.den));
		return new BigFraction(newNumerator, this.den);
	}
	public BigFraction add(BigFraction toAdd) {
		BigInteger numerator = this.num.multiply(toAdd.den).add(this.den.multiply(toAdd.num));
		BigInteger denominator = this.den.multiply(toAdd.den);
		return new BigFraction(numerator, denominator);
	}
	public BigFraction subtract(BigInteger toSubtract) {
		BigInteger newNumerator =this.num.subtract(toSubtract.multiply(this.den));
		return new BigFraction(newNumerator, this.den);
	}
	public BigFraction divide(BigInteger toDivide) {
		BigInteger newDenominator = this.den.multiply(toDivide);
		return new BigFraction(this.num, newDenominator);
	}
	public BigInteger modForm(int mod) {
		return this.num.multiply(this.den.modInverse(new BigInteger(""+mod))).mod(new BigInteger(""+mod));
	}
	@Override
	public String toString() {
		return this.num.toString()+'/'+this.den.toString();
	}

}
