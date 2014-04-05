package lab3.compress;

import java.io.File;

import lab3.utils.Huffman;

public class Main
{
	public static void main(String args[])
	{
		if(args.length != 1)
		{
			System.err.println("Needs a filename");
			System.exit(1);
		}
		File inputFile = new File(args[0]);
		if(inputFile.exists() == false || inputFile.isFile() == false)
		{
			System.err.println("File doesn't exist");
			System.exit(1);
		}
		System.out.println("Beginning compression");
		long start = System.currentTimeMillis();
		Huffman.compressFile(inputFile);
		long timeTaken = System.currentTimeMillis() - start;
		System.out.println("Finished! Compressing took: " + timeTaken + "ms");
	}
}
