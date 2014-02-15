package lab1.question3;

//Remove all duplicates from an array.
public class Question3
{
	// O(n^2)
	public static int[] removeDuplicates(int[] inArray)
	{
		// Get length of new array
		int uniqueElems = 0;
		for(int i = 0; i < inArray.length; ++i)
		{
			// Increment if this element hasn't been "seen before"
			if(!seenBefore(i, inArray)) ++uniqueElems;
		}
		int[] uniqueArray = new int[uniqueElems];
		for(int i = 0, index = 0; i < inArray.length; ++i)
		{
			// Unconditionall place in with removal if seen before
			uniqueArray[index++] = inArray[i];
			if(seenBefore(i, inArray)) --index;
			if(index == uniqueArray.length) break;
		}
		return uniqueArray;
	}
	// Right to left scan
	private static boolean seenBefore(int index, int[] inArray)
	{
		// If this element came before the current
		for(int j = index - 1; j >= 0; --j)
		{
			if(inArray[index] == inArray[j]) return true;
		}
		return false;
	}
}
