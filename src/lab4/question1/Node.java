package lab4.question1;

import java.util.ArrayList;

public class Node
{
	private final static int letterRange = 26;
	private final static char characterOffset = 97;
	
	private ArrayList<String> words = new ArrayList<>();
	private Node[] children;

	public Node()
	{
		children = new Node[letterRange];
	}
	
	public final ArrayList<String> getWords()
	{
		return words;
	}
	
	public final Node[] getChildren()
	{
		return children;
	}
	
	public final Node getChild(char letter)
	{
		return children[getIndex(letter)];
	}
	
	public void addWord(String inWord, String inOriginalWord)
	{
		recurseString(inWord, 0, inOriginalWord);
	}
	
	private void recurseString(String inWord, int inCurIndex, String inOriginalWord)
	{
		if(inCurIndex == inWord.length())
		{
			this.attachWord(inOriginalWord);
			return;
		}
		int index = getIndex(inWord.charAt(inCurIndex));
		Node newNode = addChild(index);
		newNode.recurseString(inWord, ++inCurIndex, inOriginalWord);
	}
	
	private void attachWord(String inWord)
	{
		words.add(inWord);
	}
	
	private Node addChild(int index)
	{
		if(children[index] == null) children[index] = new Node();
		return children[index];
	}
	
	public static int getIndex(char letter)
	{
		return letter - characterOffset;
	}
}
