package lab5.utils;

public class DuplicateRemover
{
	private static final int asciiRange = 256;
	private boolean[] seenBefore = new boolean[asciiRange];
	public DuplicateRemover(){}
	public String removeDuplicates(String inString)
	{
		StringBuilder newWord = new StringBuilder();
		for(int i = 0; i < inString.length(); ++i)
		{
			char letter = inString.charAt(i);
			if(!seenBefore[letter]) newWord.append(letter);
			seenBefore[letter] = true;
		}
		for(int i = 0; i < asciiRange; ++i) seenBefore[i] = false;
		return newWord.toString();
	}
}
