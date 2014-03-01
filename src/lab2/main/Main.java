package lab2.main;

import lab2.question1.Question1;

public class Main
{
	public static void main(String args[])
	{
		String sentence = "The cat sat on the mat";
		int[] frequencies = Question1.frequencyCounter(sentence);
		for(char i = 0; i < Question1.AsciiRange; ++i)
		{
			if(frequencies[i] > 0)
			{
				System.out.println("Character '" + i + "' appeared " + frequencies[i] + " time(s)");
			}
		}
	}
}