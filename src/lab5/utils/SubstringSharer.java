package lab5.utils;

import java.util.HashMap;
import java.util.Map;

public class SubstringSharer
{
	Map<Integer, Boolean> seenBefore = new HashMap<Integer, Boolean>();
	public SubstringSharer(){}
	public boolean shareSubstring(String inString1, String inString2)
	{
		int inString1Loop = inString1.length() - 1;
		for(int i = 0; i < inString1Loop; ++i)
		{
			// Generate a number of base 128 and hash it to be true.
			int pairNumber = inString1.charAt(i) + (128 * inString1.charAt(i + 1));
			seenBefore.put(pairNumber, true);
		}
		int inString2Loop = inString2.length() - 1;
		for(int i = 0; i < inString2Loop; ++i)
		{
			// Generate a number of base 128 and hash it. If the value is true, return true
			int pairNumber = inString2.charAt(i) + (128 * inString2.charAt(i + 1));
			if(seenBefore.containsKey(pairNumber))
			{
				seenBefore.clear();
				return true;
			}
		}
		seenBefore.clear();
		return false;
	}
}
