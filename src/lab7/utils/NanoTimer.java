package lab7.utils;

public class NanoTimer 
{
	long snap;
	public void start()
	{
		snap = System.nanoTime();
	}
	
	public long end()
	{
		return System.nanoTime() - snap;
	}

}
