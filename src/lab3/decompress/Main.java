package lab3.decompress;

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
		long start = System.currentTimeMillis();
		System.out.println(Huffman.decompressFile(inputFile).toString());
		long timeTaken = System.currentTimeMillis() - start;
		System.out.println("Finished! Decomperssion took: " + timeTaken + "ms");
	}
}
