package exam.main;

import java.util.Arrays;

import exam.question3.Question3;

public class Main
{
	public static void main(String args[])
	{
		int[] testArray1 = {4000, -2000, 4, -6, -2, 8, 2, 7, -2, 10, -50, 100, -10, -1000, 2000};
		int[] res1 = Question3.findLargestSubsequence(testArray1);
		System.out.println(Arrays.toString(testArray1) + " returns - " + Arrays.toString(res1) + " whose sum is: " + Question3.sum(res1));
		
		int[] testArray2 = {-4, -6, -2, 8, 2, 7, -2, 10};
		int[] res2 = Question3.findLargestSubsequence(testArray2);
		System.out.println(Arrays.toString(testArray2) + " returns - " + Arrays.toString(res2) + " whose sum is: " + Question3.sum(res2));
		
		int[] testArray3 = {-4, -6, -2000, 10000, -2000, 8, 2, 7, -2, 10};
		int[] res3 = Question3.findLargestSubsequence(testArray3);
		System.out.println(Arrays.toString(testArray3) + " returns - " + Arrays.toString(res3) + " whose sum is: " + Question3.sum(res3));
		
		int[] testArray4 = {10};
		int[] res4 = Question3.findLargestSubsequence(testArray4);
		System.out.println(Arrays.toString(testArray4) + " returns - " + Arrays.toString(res4) + " whose sum is: " + Question3.sum(res4));
	}
}
