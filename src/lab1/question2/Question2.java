package lab1.question2;

import java.util.Random;
/*
 * Example test cases:
 * 2,1 -> 0.798934
 * 2,2 -> 0.500816
 * 10,5 -> 0.934594
 * 10,9 -> 0.606436
 * 100,99 -> 0.530298
 */
public class Question2
{
	private static final int SomeBigNumber = 500000;
	private static final int Outcomes = 2;
	// No draws allowed.
	public static float coinFlip(int player1Coins, int player2Coins)
	{
		Random rand = new Random(System.currentTimeMillis());
		int player1Total = 0;
		int iterCount = 0;
		while(iterCount < SomeBigNumber)
		{
			int player1Heads = 0;
			int player2Heads = 0;
			
			for(int toss = 0; toss < player1Coins; ++toss) player1Heads += rand.nextInt(Outcomes);
			for(int toss = 0; toss < player2Coins; ++toss) player2Heads += rand.nextInt(Outcomes);
			
			// Not a draw
			if(player1Heads - player2Heads != 0)
			{
				++iterCount;
				// Player 1 wins (this round)
				if(player1Heads > player2Heads)
				{
					++player1Total;
				}
			}
		}
		return (float)player1Total / (float)SomeBigNumber;
	}
}
