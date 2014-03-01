package lab1.main;

import java.util.Arrays;

import lab1.question1.Question1;
import lab1.question2.Question2;
import lab1.question3.Question3;

public class Main {
	public static void main(String args[]) {
		System.out.println("Percentage clock answer for 11: "
				+ Question1.percentClock(11));
		System.out.println("Percentage clock answer for 17: "
				+ Question1.percentClock(17));

		System.out.println("Coin flipping question for 10 and 10: "
				+ Question2.coinFlip(10, 10));
		System.out.println("Percentage clock answer for 10 and 5: "
				+ Question2.coinFlip(10, 5));

		System.out.println("Percentage clock answer for { 1, 1, 1, 3, 4 }: "
				+ Arrays.toString(Question3.removeDuplicates(new int[] { 1, 1, 1, 3, 4 })));
	}
}
