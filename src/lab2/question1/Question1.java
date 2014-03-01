package lab2.question1;

public class Question1
{
	public static final int AsciiRange = 256; // Apparently
	public static int[] frequencyCounter(String sentence)
	{
		int[] frequency = new int[AsciiRange];
		for(int index = 0; index < sentence.length(); ++index)
		{
			int intValue = Character.valueOf(sentence.charAt(index));
			System.out.print(returnBinaryString(intValue) + " ");
			++frequency[intValue];
		}
		return frequency;
	}
	private static String returnBinaryString(int number)
	{
		// Missing leading zeros
		String binString = Integer.toBinaryString(number);
		int length = binString.length();
		switch(length)
		{
		case 6: return "0" + binString;
		case 5: return "00" + binString;
		case 4: return "000" + binString;
		case 3: return "0000" + binString;
		case 2: return "00000" + binString;
		case 1: return "000000" + binString;
		}
		return binString;
	}
}
