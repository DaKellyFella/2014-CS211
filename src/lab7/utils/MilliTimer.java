package lab7.utils;

public class MilliTimer 
{
	long snap;
	public void start()
	{
		snap = System.currentTimeMillis();
	}
	public long end()
	{
		return System.currentTimeMillis() - snap;
	}
}
