package lab5.question3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import lab5.utils.SubstringSharer;
import lab5.utils.WordPair;

public class GreatestNonSharingPair
{
	public static WordPair findBiggestPair(File inFile)
	{
		SubstringSharer shareChecker = new SubstringSharer();
		// To know where to start looking from
		TreeMap<Integer, ArrayList<String>> lengthsToWords = new TreeMap<Integer, ArrayList<String>>();
		// An anagram map
		Map<String, ArrayList<String>> anagramMap = new HashMap<String, ArrayList<String>>();
		long start = System.currentTimeMillis();
	    try
	    {
	    	Reader fileReader = new FileReader(inFile);
	    	BufferedReader input = new BufferedReader(fileReader);
	    	String line = null;
	    	while((line = input.readLine()) != null)
	    	{
	    		// Insert word into length map
	    		ArrayList<String> otherWords = lengthsToWords.get(line.length());
	    		if(otherWords == null)
	    		{
	    			ArrayList<String> newWordList = new ArrayList<String>();
	    			newWordList.add(line);
	    			lengthsToWords.put(line.length(), newWordList);
	    		}else otherWords.add(line);
	    		
	    		// Sort word and insert into anagram map
	    		String sortedWord = sortWord(line);
	    		ArrayList<String> wordList = anagramMap.get(sortedWord);
	    		if(wordList == null)
	    		{
	    			ArrayList<String> newWordList = new ArrayList<String>();
	    			newWordList.add(line);
	    			anagramMap.put(sortedWord, newWordList);
	    		}
	    		else wordList.add(line);
	    	}
	    	input.close();
	    	fileReader.close();
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
		// All words and their anagrams now present in myMap
	    
	    // Now start from the largest words and look for a pair of legal anagrams
		for(Integer length : lengthsToWords.descendingKeySet())
		{
			ArrayList<String> possibleWords = lengthsToWords.get(length);
			for(String possibleWord : possibleWords)
			{
				String sortedWord = sortWord(possibleWord);
				ArrayList<String> anagrams = anagramMap.get(sortedWord);
				// Check all pairs
				int loopLimit = anagrams.size() - 1;
				for(int i = 0; i < loopLimit; ++i)
				{
					String outterString = anagrams.get(i);
					for(int j = i + 1; j < anagrams.size(); ++j)
					{
						String innerString = anagrams.get(j);
						// If they don't share any substring then accept
						if(!shareChecker.shareSubstring(outterString, innerString))
						{
							System.out.println("Time taken: " + (System.currentTimeMillis() - start));    						
							return new WordPair(innerString, outterString);
						}
					}
				}
			}
		}
		System.out.println("Time taken: " + (System.currentTimeMillis() - start));
		return new WordPair("No solution", "Soz");
	}
	
	private static String sortWord(String inString)
	{
		char[] demChars = inString.toCharArray();
		Arrays.sort(demChars);
		return new String(demChars);
	}
}
