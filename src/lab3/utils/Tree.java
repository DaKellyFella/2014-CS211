package lab3.utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tree implements Comparable<Tree>, Serializable {
	public Node root = null;
	public int frequency = 0;
	private String path = "";
	private Node traversingNode = null;
	public Tree(){}
	public Tree(Node inRoot, int inFrequency)
	{
		this.root = inRoot;
		this.frequency = inFrequency;
	}
	public int compareTo(Tree inTree)
	{
		if (frequency - inTree.frequency > 0) return 1;
		else if (frequency - inTree.frequency < 0) return -1;
		else return 0;
	}
	public String getCode(char letter)
	{
		inOrder(root, letter, "");
		return path;
	}
	// Move in the required direction (left = false, right = true)
	public boolean lookUp(boolean step)
	{
		if(traversingNode == null)
		{
			traversingNode = root;
		}
		if(step == true)
		{
			// Right on a 1
			traversingNode = traversingNode.rightChild;
		}else
		{
			traversingNode = traversingNode.leftChild;
		}
		// Leaf node
		if(traversingNode.rightChild == null && traversingNode.leftChild == null)
		{
			return true;
		}
		return false; // Not there yet..
	}
	public char getTraversedCharCode()
	{
		char value =  traversingNode.letter;
		traversingNode = root;
		return value;
	}
	private void inOrder(Node localRoot, char letter, String path)
	{
		if (localRoot != null)
		{
			if (localRoot.letter == letter) this.path = path;
			else
			{
				inOrder(localRoot.leftChild, letter, path + "0");
				inOrder(localRoot.rightChild, letter, path + "1");
			}
		}
	}
}