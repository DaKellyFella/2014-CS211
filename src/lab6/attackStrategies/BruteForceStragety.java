package lab6.attackStrategies;

import java.math.BigInteger;

public class BruteForceStragety implements AttackInterface
{
	@Override
	public BigInteger computePrivateKey(BigInteger p, BigInteger g, BigInteger y)
	{
		for(BigInteger i = BigInteger.ONE;; i = i.add(BigInteger.ONE))
		{
			BigInteger guess = g.modPow(i, p);
			if(guess.compareTo(y) == 0) return i; // Found the private key
		}
	}
	
}
