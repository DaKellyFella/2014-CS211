package lab7.main;

import lab4.question1.FileIO;
import lab7.hashtable.HashTable;
import lab7.hashtable.strategies.DoubleHashing;
import lab7.hashtable.strategies.LinearProbing;
import lab7.hashtable.strategies.QuadraticProbing;
import lab7.utils.MilliTimer;
import lab7.utils.Utils;

public class Main
{
	public static void main(String args[])
	{
		MilliTimer myTimer = new MilliTimer();
		FileIO myFile = new FileIO();
		String[] words = myFile.load("./dictionary.txt");
		Utils.shuffleArray(words);
		
		HashTable linearProbingTable = new HashTable(new LinearProbing(), words.length * 2);
		
		myTimer.start();
		for(int i = 0; i < words.length; i++)
		{
			String word = words[i];
			linearProbingTable.insert(word);
		}
		
		
		System.out.println("Linear probing took: " + myTimer.end() + "ms");
		System.out.println("Internal Size: " + linearProbingTable.getInternalSize()
				+ " current load factor: " + linearProbingTable.getLoadFactor()
				+ " collision count: " + linearProbingTable.getCollisionCount());
		
		HashTable quadraticProbingTable = new HashTable(new QuadraticProbing(), words.length * 2);
		
		myTimer.start();
		for(int i = 0; i < words.length; i++)
		{
			String word = words[i];
			quadraticProbingTable.insert(word);
		}
		
		System.out.println("Quadratic probing took: " + myTimer.end() + "ms");
		System.out.println("Internal Size: " + quadraticProbingTable.getInternalSize()
				+ " current load factor: " + quadraticProbingTable.getLoadFactor()
				+ " collision count: " + quadraticProbingTable.getCollisionCount());
		
		
		HashTable doubleHashingTable = new HashTable(new DoubleHashing(), words.length * 2);
		
		myTimer.start();
		for(int i = 0; i < words.length; i++)
		{
			String word = words[i];
			doubleHashingTable.insert(word);
		}
		
		System.out.println("Double hashing took: " + myTimer.end() + "ms");
		System.out.println("Internal Size: " + doubleHashingTable.getInternalSize()
				+ " current load factor: " + doubleHashingTable.getLoadFactor()
				+ " collision count: " + doubleHashingTable.getCollisionCount());
	}
}
