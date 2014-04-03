package lab6.keybreaker;

import java.math.BigInteger;

import lab6.attackStrategies.AttackInterface;
import lab6.attackStrategies.BruteForceStragety;

public class KeyCracker
{
	private BigInteger p, g, y; // Alice's public keys
	
	private BigInteger privateKey; // Alice's private key
	boolean gotPrivateKey = false;
	AttackInterface attackingMethod = new BruteForceStragety();
	public KeyCracker(){}
	public KeyCracker(AttackInterface inGivenStragety){this.attackingMethod = inGivenStragety;}
	public void givePublicKeys(BigInteger p, BigInteger g, BigInteger y)
	{
		// Make copies and compute other valuable variables
		this.p = new BigInteger(p.toByteArray());
		this.g = new BigInteger(g.toByteArray());
		this.y = new BigInteger(y.toByteArray());
		gotPrivateKey = false; // New keys invalidate this
	}
	public BigInteger computePrivateKey()
	{
		if(gotPrivateKey) return privateKey;
		privateKey = attackingMethod.computePrivateKey(p, g, y);
		gotPrivateKey = true;
		return privateKey;
	}
	public BigInteger getMessage(BigInteger c1, BigInteger c2)
	{
		if(!gotPrivateKey) return BigInteger.ZERO;
		BigInteger exponent = p.subtract(privateKey.add(BigInteger.ONE));
		BigInteger c1x = c1.modPow(exponent, p);
		BigInteger message = c2.multiply(c1x);
		return message.mod(p);
	}
	public void setStragety(AttackInterface newStragety)
	{
		this.attackingMethod = newStragety;
	}
	public void invalidatePrivateKey()
	{
		gotPrivateKey = false;
	}
}
