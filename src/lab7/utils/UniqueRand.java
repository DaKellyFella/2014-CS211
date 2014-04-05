package lab7.utils;

/*
 * A unique random number generator stolen from
 * preshing of preshing.com
 * All credit goes to him, what a man.
 */

public class UniqueRand 
{
	private int m_prime = 2147483647;
	private int m_index;
	private int m_intermediateOffset;
	
	private int getRand(int inNum)
	{
		if (inNum >= m_prime) return inNum;
		int residue = (int)(((long) inNum * (long) inNum) % m_prime);
        return (inNum <= m_prime / 2) ? residue : m_prime - residue;
	}
	
	public UniqueRand(int inSeedBase, int inSeedOffset)
	{
		m_index = getRand(getRand(inSeedBase) + 0x682f0161);
        m_intermediateOffset = getRand(getRand(inSeedOffset) + 0x46790905);
	}
	
	public int getNext()
	{
		return getRand((getRand(m_index++) + m_intermediateOffset) ^ 0x5bf03635);
	}
}
