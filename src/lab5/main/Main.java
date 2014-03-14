package lab5.main;

import java.io.File;

import lab5.question3.GreatestNonSharingPair;
import lab5.utils.WordPair;

public class Main
{
	public static void main(String args[])
	{
		WordPair bestPair = GreatestNonSharingPair.findBiggestPair(new File("File name here"));
		System.out.println(bestPair.getLargest() + " " + bestPair.getSecondLargest());
	}
}
