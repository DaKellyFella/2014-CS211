package lab7.hashtable;

import lab7.hashtable.strategies.InsertionStragety;
import lab7.utils.Utils;

public class HashTable
{
	private static final String tombStone = "\0";
	private static final double loadFactorTolerance = 0.5;
	// Table size and contents
	private int m_tableSize;
	private int m_numItems = 0;
	private String[] m_tableContents;
	
	// Collision counter
	private int m_collisions = 0;
	private double m_loadFactor = 0.0;
	
	// Fuck Java
	private InsertionStragety m_insertionStragety;
	
	public HashTable(InsertionStragety givenStragety, int inSize)
	{
		m_insertionStragety = givenStragety;
		m_tableSize = Utils.nextPrime(inSize);
		m_tableContents = new String[m_tableSize];
	}
	
	public void insert(String inWord)
	{
		m_loadFactor = (float)++m_numItems / (float)m_tableSize;
		
		if(m_loadFactor > loadFactorTolerance) reallocate(m_tableSize * 2);
		
		int hashCode = m_insertionStragety.getHashCode(inWord) % m_tableSize;
		
		// Find an empty space or tombstone
		while(m_tableContents[hashCode] != null && m_tableContents[hashCode] != tombStone)
		{
			++m_collisions;
			hashCode = (hashCode + m_insertionStragety.computeStepSize()) % m_tableSize;
		}
		m_tableContents[hashCode] = inWord;
	}
	
	public boolean contains(String inWord)
	{
		int hashCode = m_insertionStragety.getHashCode(inWord) % m_tableSize;
		
		// Find an empty space or tombstone
		while(m_tableContents[hashCode] != null)
		{
			String currentWord = m_tableContents[hashCode];
			if(currentWord == tombStone) continue;
			if(currentWord.compareTo(inWord) == 0) return true;
			hashCode = (hashCode + m_insertionStragety.computeStepSize()) % m_tableSize;
		}
		return false;
	}
	
	public void delete(String inWord)
	{
		m_loadFactor = (float)--m_numItems / (float)m_tableSize;
		int hashCode = m_insertionStragety.getHashCode(inWord) % m_tableSize;
		
		// Find the item or an empty space
		while(m_tableContents[hashCode] != null && m_tableContents[hashCode] != tombStone)
		{
			// Found! Now delete
			if(m_tableContents[hashCode].compareTo(inWord) == 0)
			{
				m_tableContents[hashCode] = tombStone;
				break;
			}
			hashCode = (hashCode + m_insertionStragety.computeStepSize()) % m_tableSize;
		}
	}
	
	private void reallocate(int newSize)
	{
		m_numItems = m_collisions = 0;
		m_tableSize = Utils.nextPrime(newSize);
		String[] oldTable = m_tableContents;
		m_tableContents = new String[m_tableSize];
		for(String item : oldTable)
		{
			if(item == null || item.compareTo(tombStone) == 0) continue;
			insert(item);
		}
	}
	
	public int getInternalSize()
	{
		return m_tableSize;
	}
	
	public int getNumItems()
	{
		return m_numItems;
	}
	
	public int getCollisionCount()
	{
		return m_collisions;
	}
	
	public double getLoadFactor()
	{
		return m_loadFactor;
	}
	
	public void debugPrint()
	{
		for(String item : m_tableContents)
		{
			if(item == null) System.out.print("\"null\", ");
			else if(item == tombStone) System.out.print("\"Item is a tombstone\", ");
			else System.out.print(item + ", ");
		}
		System.out.println();
	}
}
