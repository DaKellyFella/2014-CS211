package lab6.attackStrategies;

import java.math.BigInteger;
import java.util.HashMap;

public class BabyStepGiantStepsStragety implements AttackInterface
{
	private static final int ONCE = 1;
	private static final BigInteger TWO = BigInteger.valueOf(2L);	
	@Override
	public BigInteger computePrivateKey(BigInteger p, BigInteger g, BigInteger y)
	{
		BigInteger squareRootP = sqrtN(p);
		// Generator modPow to some value and that value itself.
		HashMap<BigInteger, BigInteger> precomputedValues = new HashMap<BigInteger, BigInteger>();
		for(BigInteger i = BigInteger.ZERO; i.compareTo(squareRootP) != 0; i = i.add(BigInteger.ONE))
		{
			precomputedValues.put(g.modPow(i, p), i); // Store computed value and value used to compute it
		}
		BigInteger privateKey = BigInteger.valueOf(0L); // Initial guess!
		BigInteger c = (g.modPow(squareRootP.negate(), p)); // In the notes you need c, which is defined as c = g^-m mod p
		// You also need t, which is originally just y
		BigInteger t = y;
		for(BigInteger i = BigInteger.ZERO; i.compareTo(squareRootP) != 0; i = i.add(BigInteger.ONE))
		{
			if(precomputedValues.containsKey(t))
			{
				BigInteger usedPower = precomputedValues.get(t);
				privateKey = (squareRootP.multiply(i)).add(usedPower);
				return privateKey;
			}else t = (t.multiply(c)).mod(p); // Recompute t
		}
		return privateKey;
	}
	private static BigInteger sqrtN(BigInteger inNumber) {
		int c = 0;
		BigInteger n0 = TWO.pow(inNumber.bitLength() >> 1);
		BigInteger np = inNumber;
		do{
		    n0 = n0.add(inNumber.divide(n0)).shiftRight(ONCE);
		    c = np.compareTo(n0);
		    np = n0;
		}while(c != 0);
		return n0;
	}
}
