package lab7.hashtable.strategies;

import java.math.BigInteger;

public class LinearProbing implements InsertionStragety
{
	public int getHashCode(String inWord)
	{
		BigInteger hashCode = BigInteger.ZERO;
		BigInteger power = BigInteger.valueOf(31L);
		for(int i = 0; i < inWord.length(); ++i)
		{
			BigInteger letterValue = BigInteger.valueOf(inWord.charAt(i));
			hashCode = hashCode.add(power.pow(i).multiply(letterValue));
		}
		
		int intValue = hashCode.intValue();
		if(intValue == Integer.MIN_VALUE) ++intValue;
		// Effectively a mod 2^31
		return Math.abs(intValue);
	}

	public int computeStepSize()
	{
		return 1;
	}
}
