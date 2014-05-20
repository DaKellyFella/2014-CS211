package lab9.utils;

public class Utils
{
	public static void reverse(Object[] inArray)
	{
		for(int i = 0; i < inArray.length / 2; i++)
		{
			Object temp = inArray[i];
			inArray[i] = inArray[inArray.length - i - 1];
			inArray[inArray.length - i - 1] = temp;
		}		
	}
	
	public static double[] convertToDoubles(String[] inArray)
	{
		double[] outArray = new double[inArray.length];
		for(int i = 0; i < inArray.length; ++i)
		{
			outArray[i] = Double.parseDouble(inArray[i]);
		}
		return outArray;
	}
}
