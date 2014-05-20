package lab9.algorithm;

public class Algorithm
{
	public static FallInfo returnMaxFall(double[] inPrices)
	{
		int bestStartIndex = 0;
		int startIndex = 0;
		int endIndex = 0;
		double maxPercent = 0;
		double largestPrice = inPrices[startIndex];
		for(int i = 1; i < inPrices.length; ++i)
		{
			double currentPrice = inPrices[i];
			if(currentPrice > largestPrice)
			{
				startIndex = i;
				largestPrice = currentPrice;
			}
			
			double percentageLoss = ((largestPrice - currentPrice) / largestPrice);
			if(percentageLoss > maxPercent)
			{
				bestStartIndex = startIndex;
				maxPercent = percentageLoss;
				endIndex = i;
			}
		}
		
		FallInfo outInfo = new FallInfo();
		outInfo.m_startIndex = bestStartIndex;
		outInfo.m_endIndex = endIndex;
		outInfo.m_percentDrop = maxPercent;
		return outInfo;
	}
}
