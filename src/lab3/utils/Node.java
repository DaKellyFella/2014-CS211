package lab3.utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Node implements Serializable
{
	public char letter = '\0'; // Default is 0
	public Node leftChild = null;
	public Node rightChild = null;
	public Node(){}
	public Node(char inLetter)
	{
		this.letter = inLetter;
	}
}