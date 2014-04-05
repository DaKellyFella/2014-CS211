package lab7.utils;

import java.util.Random;

public class Utils
{
	public static boolean isPrime(int inNumber)
	{
		// Is even and 3 checks
		if(inNumber % 2 == 0) return false;
		else if(inNumber % 3 == 0) return false;
		int limit = (int) Math.sqrt(inNumber) + 1;
		for(int i = 3; i <= limit; i+=2)
		{
			if(inNumber % i == 0) return false;
		}
		return true;
	}
	
	public static int nextPrime(int inStartNumber)
	{
		if(inStartNumber % 2 == 0) ++inStartNumber;
		for(int i = inStartNumber;; i+= 2)
		{
			if(isPrime(i)) return i;
		}
	}
	public static void shuffleArray(String[] ar)
	{
		Random rnd = new Random(System.currentTimeMillis());
		for (int i = ar.length - 1; i > 0; i--)
	    {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
	    }
	}
}
