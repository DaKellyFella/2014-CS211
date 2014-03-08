package lab4.question1;

import java.util.ArrayList;
import java.util.Arrays;

public class Tree
{
	Node head = new Node();
	public Tree(String[] dictionary)
	{
		long start = System.currentTimeMillis();
		System.out.println("Building tree");
		for(String word : dictionary)
		{
			String sortedWord = sortWord(word);
			addWord(sortedWord, word);
		}
		long end = System.currentTimeMillis() - start;
		System.out.println("Tree built in: " + end + "ms");
	}
	private void addWord(String inWord, String inOriginalWord)
	{
		head.addWord(inWord, inOriginalWord);
	}
	public ArrayList<String> getAllPossibleWords(String inWord)
	{
		String sortedWord = sortWord(inWord);
		Node[] searchSpace = head.getChildren();
		ArrayList<Node> toProcess = new ArrayList<Node>();
		for(int i = 0; i < sortedWord.length(); ++i)
		{
			char letter = sortedWord.charAt(i);
			int index = Node.getIndex(letter);
			ArrayList<Node> currentLoop = new ArrayList<Node>();
			for(Node item : toProcess)
			{
				Node potentialMove = item.getChild(letter);
				if(potentialMove == null) continue;
				currentLoop.add(potentialMove);
			}
			toProcess.addAll(currentLoop);
			if(searchSpace[index] == null) continue;
			toProcess.add(searchSpace[index]);
		}
		System.out.println(toProcess.size());
		ArrayList<String> foundWords = new ArrayList<>();
		for(Node currentNode : toProcess)
		{
			if(currentNode == null) continue;
			ArrayList<String> currentWords = currentNode.getWords();
			
			if(currentWords == null) continue;
			foundWords.addAll(currentNode.getWords());
		}
		return foundWords;
	}
	
	public String getLargestWord(String inWord)
	{
		ArrayList<String> results = getAllPossibleWords(inWord);
		if(results.size() == 0) return "NO SOLUTION";
		String largestWord = new String();
		for(String word : results)
		{
			int curLength = word.length();
			if(largestWord.length() < curLength) largestWord = word;
		}

		return largestWord;
	}
	
	private String sortWord(String inString)
	{
		char[] demChars = inString.toCharArray();
		Arrays.sort(demChars);
		return new String(demChars);
	}
}
