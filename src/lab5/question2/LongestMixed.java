package lab5.question2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;

import lab5.utils.WordPair;

public class LongestMixed
{
	public static WordPair findBiggestPair(File inFile)
	{
		WordPair longestPair = new WordPair("", "");
		HashMap<String, WordPair> myMap = new HashMap<String, WordPair>();
	    try
	    {
	    	Reader fileReader = new FileReader(inFile);
	    	BufferedReader input = new BufferedReader(fileReader);
	    	String line = null;
	    	long start = System.currentTimeMillis();
	    	while((line = input.readLine()) != null)
	    	{
	    		String word = sortWord(line);
	    		WordPair found = myMap.get(word);
	    		if(found == null)
	    		{
	    			WordPair newPair = new WordPair(line, "");
	    			if(newPair.getSize() > longestPair.getSize()) longestPair = newPair;
	    			myMap.put(word, newPair);
	    		}else // If present, now replace if bigger
	    		{
	    			boolean replaced = found.tryReplace(line);
	    			if(replaced && found.getSize() > longestPair.getSize()) longestPair = found;
	    		}
	    	}
	    	System.out.println("Time taken: " + (System.currentTimeMillis() - start));
	    	input.close();
	    	fileReader.close();
	    }catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
	    return longestPair;
	}
	private static String sortWord(String inString)
	{
		char[] demChars = inString.toCharArray();
		Arrays.sort(demChars);
		return new String(demChars);
	}

}
