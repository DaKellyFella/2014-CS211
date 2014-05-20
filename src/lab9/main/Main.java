package lab9.main;

import java.util.Arrays;

import lab4.question1.FileIO;
import lab9.algorithm.Algorithm;
import lab9.algorithm.FallInfo;
import lab9.utils.Utils;

public class Main
{
	public static void main(String args[])
	{
		FileIO myFileIO = new FileIO();
		
		String[] dates = myFileIO.load("/home/rob/work/other/eclipseworkspace/2014-CS211/dates.txt");
		Utils.reverse(dates);
		String[] facebook = myFileIO.load("/home/rob/work/other/eclipseworkspace/2014-CS211/facebook.txt");
		Utils.reverse(facebook);
		String[] apple = myFileIO.load("/home/rob/work/other/eclipseworkspace/2014-CS211/apple.txt");
		Utils.reverse(apple);
		
		double[] applePrices = Utils.convertToDoubles(apple);
		double[] facebookPrices = Utils.convertToDoubles(facebook);
		FallInfo appleFallInfo = Algorithm.returnMaxFall(applePrices);
		System.out.println(Arrays.toString(applePrices));
		System.out.println(appleFallInfo.m_percentDrop + " " + appleFallInfo.m_startIndex + " "
				+ appleFallInfo.m_endIndex + " " + applePrices[appleFallInfo.m_startIndex] + " "
						+ applePrices[appleFallInfo.m_endIndex]);
		
		System.out.println(dates[appleFallInfo.m_startIndex] + " " + dates[appleFallInfo.m_endIndex]);
		
		FallInfo facebookFallInfo = Algorithm.returnMaxFall(facebookPrices);
		System.out.println(Arrays.toString(facebookPrices));
		System.out.println(facebookFallInfo.m_percentDrop + " " + facebookFallInfo.m_startIndex + " "
				+ facebookFallInfo.m_endIndex + " " + facebookPrices[facebookFallInfo.m_startIndex] + " "
				+ facebookPrices[facebookFallInfo.m_endIndex]);
		System.out.println(dates[facebookFallInfo.m_startIndex] + " " + dates[facebookFallInfo.m_endIndex]);
	}
}
