package exam.question3;

public class Question3
{
	public static int[] findLargestSubsequence(int[] inArray)
	{
		int bestBegin = 0, bestEnd = 0;
		int begin = 0, end = 0;
		
		int largestSum = Integer.MIN_VALUE, currentSum = inArray[0], nextSum = 0;
		
		for(int current = 0; current < inArray.length; ++current)
		{
			nextSum += inArray[current];
			if(nextSum < 0) // No longer viable
			{
				// Reset new range, since a 0 crossing means we can't include it.
				begin = current + 1;
				end = begin;
				currentSum = inArray[begin];
				nextSum = 0;
			}
			else if(nextSum >= currentSum) // Beaten the current pair
			{
				end = current;
				currentSum = nextSum;
				if(currentSum > largestSum) // Beat global best
				{
					bestBegin = begin;
					bestEnd = end;
					largestSum = currentSum;
				}
			}			
		}
		
		int[] resultArray = new int[bestEnd - bestBegin + 1];
		for(int i = bestBegin; i <= bestEnd; ++i)
		{
			resultArray[i - bestBegin] = inArray[i];
		}
		return resultArray;
	}
	
	public static int sum(int[] inArray)
	{
		int sum = 0;
		for(int item : inArray) sum += item;
		return sum;
	}
}
