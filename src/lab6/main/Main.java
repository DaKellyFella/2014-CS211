package lab6.main;

import java.math.BigInteger;

import lab6.attackStrategies.AttackInterface;
import lab6.attackStrategies.BabyStepGiantStepsStragety;
import lab6.attackStrategies.BruteForceStragety;
import lab6.attackStrategies.PolardRho;
import lab6.keybreaker.KeyCracker;
/*
 * Answers.
 * Written - Private key: 5, Message: 15
 * Programming - Private key: 94156, Message: 12345678
 * Bonus - Private key: 42201171294, Message: 17171717171
 */
public class Main
{
	public static void main(String args[])
	{
		// Strageties
		AttackInterface bruteForceAttack = new BruteForceStragety();
		AttackInterface babyStepsAttack = new BabyStepGiantStepsStragety();
		AttackInterface pollardRhoAttack = new PolardRho();
		
		// The cracker
		KeyCracker myKeyCracker = new KeyCracker();
		
		/* Written exercise */
		
		// Alice's public key information
		BigInteger pWritten = new BigInteger("29"),
				gWritten = new BigInteger("2"),
				yWritten = new BigInteger("3");
		// Bob's cipher using Alice's public key information
		BigInteger c1Written = new BigInteger("23"), c2Written = new BigInteger("27");
		myKeyCracker.givePublicKeys(pWritten, gWritten, yWritten);
		
		// Brute force
		myKeyCracker.setStragety(bruteForceAttack);
		decodeAndTime(myKeyCracker, c1Written, c2Written);
		
		// Baby steps giant steps
		myKeyCracker.setStragety(babyStepsAttack);
		decodeAndTime(myKeyCracker, c1Written, c2Written);
		
		/* End written exercise */
		
		
		System.out.println("******************************");
		
		
		/* Programming exercise */
		
		// Alice's public key information
		BigInteger pNormal = new BigInteger("24852977"),
				gNormal = new BigInteger("2744"),
				yNormal = new BigInteger("8414508");
		// Bob's cipher using Alice's public key information
		BigInteger c1Normal = new BigInteger("15268076"), c2Normal = new BigInteger("743675");
		myKeyCracker.givePublicKeys(pNormal, gNormal, yNormal);
		
		// Brute force
		myKeyCracker.setStragety(bruteForceAttack);
		decodeAndTime(myKeyCracker, c1Normal, c2Normal);
		
		myKeyCracker.invalidatePrivateKey();
		
		// Baby steps giant steps
		myKeyCracker.setStragety(babyStepsAttack);
		decodeAndTime(myKeyCracker, c1Normal, c2Normal);
		
		/* End programming exercise */
		
		
		System.out.println("******************************");
		
		
		/* Bonus exercise */
		
		// Alice's public key information
		BigInteger pBonus = new BigInteger("85754635859"),
				gBonus = new BigInteger("181673"),
				yBonus = new BigInteger("34109547043");
		// Bob's cipher using Alice's public key information
		BigInteger c1Bonus = new BigInteger("26398053246"), c2Bonus = new BigInteger("72955071594");
		myKeyCracker.givePublicKeys(pBonus, gBonus, yBonus);
		// Baby steps giant steps
		myKeyCracker.setStragety(babyStepsAttack);
		decodeAndTime(myKeyCracker, c1Bonus, c2Bonus);
		
		myKeyCracker.invalidatePrivateKey();
		
		myKeyCracker.setStragety(pollardRhoAttack);
		//decodeAndTime(myKeyCracker, c1Bonus, c2Bonus);
		
		myKeyCracker.givePublicKeys(new BigInteger("1019"), new BigInteger("2"), new BigInteger("5"));
		decodeAndTime(myKeyCracker, null, null);
		
		/* End bonus exercise */
	}
	public static void decodeAndTime(KeyCracker inCracker, BigInteger c1, BigInteger c2)
	{
		long startWrittenBruteForce = System.currentTimeMillis();
		System.out.println("Alice's private key: " + inCracker.computePrivateKey().toString());
		System.out.println("Time taken to get key: " + (System.currentTimeMillis() - startWrittenBruteForce) + "ms");
		System.out.println("Message: " + inCracker.getMessage(c1, c2).toString());
	}
}
