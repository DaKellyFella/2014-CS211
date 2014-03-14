package lab5.utils;

public class WordPair
{
	private int totalLength;
	private String largestWord;
	private String secondLargestWord;
	public WordPair(String inLargest, String inSecondLargest)
	{
		totalLength = inLargest.length() + inSecondLargest.length();
		largestWord = inLargest;
		secondLargestWord = inSecondLargest;
	}
	public boolean tryReplace(String inString)
	{
		int inStringLength = inString.length();
		if(inStringLength > largestWord.length())
		{
			secondLargestWord = largestWord;
			largestWord = inString;
			totalLength = inStringLength + secondLargestWord.length();
			return true; // Replacement
		}else if(inStringLength > secondLargestWord.length())
		{
			secondLargestWord = inString;
			totalLength = inStringLength + largestWord.length();
			return true; // Replacement
		}
		return false; // No replacement
	}
	public String getLargest()
	{
		return largestWord;
	}
	public String getSecondLargest()
	{
		return secondLargestWord;
	}
	public int getSize()
	{
		return totalLength;
	}
}
