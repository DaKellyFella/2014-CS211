package lab6.attackStrategies;

import java.math.BigInteger;

/**
 * An interface for developing attack strageties 
 * to the discrete log problem.
 * @author rob
 */
public interface AttackInterface
{
	public BigInteger computePrivateKey(BigInteger p, BigInteger g, BigInteger y);
}
