package main;

import lab1.question1.Question1;
import lab1.question2.Question2;
import lab1.question3.Question3;

public class Main
{
	public static void main(String args[])
	{
		// Lab 1
		System.out.println(Question1.percentClock(5));
		System.out.println(Question2.coinFlip(100, 99));
		
		int[] testArray = {1, 2, 1, 4, 1};
		int[] answer = Question3.removeDuplicates(testArray);
		for(int elem : answer)
		{
			System.out.println(elem);
		}
		// Lab 1 end
		
	}
}
