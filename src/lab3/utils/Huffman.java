package lab3.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Huffman{
	private static int AsciiRange = 256;
	public static void compressFile(File filePath)
    {
		int size = (int)filePath.length();
		char[] fileContents = new char[size];
    	String contents = new String();
		try{
			FileReader myFile = new FileReader(filePath);
			BufferedReader myReader = new BufferedReader(myFile);
			myReader.read(fileContents, 0, size);
			contents = new String(fileContents);
			myReader.close();
			myFile.close();
		}catch(IOException e)
		{
			e.printStackTrace();
			return;
		}
		File compressedName = new File(filePath.getName() + ".comp");
		CompressionPackage compressedSentence = encodeAsBytes(contents);
		System.out.println("Writing out compressed string and tree");
		compressedSentence.writePackage(compressedName);
    }
	
	public static StringBuilder decompressFile(File inCompressedFile)
    {
		CompressionPackage myPackage = CompressionPackage.retrivePackage(inCompressedFile);
		return decodeFromBytes(myPackage);
    }
	
    public static CompressionPackage encodeAsBytes(String sentence)
    {
    	printStars();
    	System.out.println("Calculating huffman tree");
    	long huffmanStart = System.currentTimeMillis();
    	Object[] huffmanTreeAndFrequencies = calculateHuffmanTree(sentence);
    	Tree huffmanCode = (Tree)huffmanTreeAndFrequencies[0];
    	int[] frenquencies = (int[])huffmanTreeAndFrequencies[1];
    	System.out.println("Huffman calculated. Time take: " + (System.currentTimeMillis() - huffmanStart));
    	printStars();
    	
    	printStars();
    	System.out.println("Populating hashmap with character codes");
    	long hashMapStart = System.currentTimeMillis();
    	HashMap<Character, String> codeLookups = new HashMap<>(AsciiRange * 4);
    	for(char i = 0; i < AsciiRange; ++i) codeLookups.put(i, huffmanCode.getCode(i));
    	System.out.println("Finished populating hashmap with character codes. Time taken: " 
    	+ (System.currentTimeMillis() - hashMapStart));
    	printStars();
    	
    	printStars();
    	System.out.println("Calculating bits needed");
    	long bitsNeededStart = System.currentTimeMillis();
    	int bitsNeeded = 0;
    	for(char i = 0; i < AsciiRange; ++i) bitsNeeded += codeLookups.get(i).length() * frenquencies[i];
    	System.out.println("Bits needed calculated. Answer is - " + bitsNeeded +
    			" time taken: " + (System.currentTimeMillis() - bitsNeededStart));
    	printStars();
    	
    	printStars();
    	System.out.println("Setting bits now");
    	long setBitsStart = System.currentTimeMillis();
    	BitVector compressedString = new BitVector(bitsNeeded);
    	
    	int currentBit = 0;
    	for(int i = 0; i < sentence.length(); ++i)
		{
    		String code = codeLookups.get(sentence.charAt(i));
			for(int j = 0; j < code.length(); ++j) compressedString.setValue(currentBit++, code.charAt(j) - '0');
		}
    	System.out.println("Bits have been set. Time taken: " + (System.currentTimeMillis() - setBitsStart));
    	printStars();
    	CompressionPackage returnLoad = new CompressionPackage(compressedString, huffmanCode);
    	return returnLoad;
    }
    
    public static StringBuilder decodeFromBytes(CompressionPackage inPackage)
    {
    	BitVector compressedString = inPackage.compressedString;
    	Tree huffmanTree = inPackage.huffmanTree;
    	StringBuilder unCompressedString = new StringBuilder();
    	for(int i = 0; i < compressedString.getNumBits(); ++i)
    	{
    		boolean currentStep = compressedString.getValue(i);
    		boolean hitLeaf = huffmanTree.lookUp(currentStep);
    		if(hitLeaf == true)
    		{
    			unCompressedString.append(huffmanTree.getTraversedCharCode());
    		}
    	}
    	return unCompressedString;
    }
    
    private static Object[] calculateHuffmanTree(String sentence)
    {
    	PriorityQueue<Tree> PQ = new PriorityQueue<Tree>();
    	int[] frequency = getFrequency(sentence);
    	for (char i = 0; i < frequency.length; i++)
		{
			if (frequency[i] > 0)
			{
				Node rootNode = new Node(i);
				Tree letterTree = new Tree(rootNode, frequency[i]);
				PQ.add(letterTree);
			}
		}
    	while (PQ.size() > 1)
		{
			// Get the last 2 trees
			Tree tree1 = PQ.poll();
			Tree tree2 = PQ.poll();
			// Make a new node that points to both of them. Its character isn't important.
			Node newRoot = new Node();
			newRoot.leftChild = tree1.root;
			newRoot.rightChild = tree2.root;
			// Calculate new frequency value and add it back into the queue
			int newFrequency = tree1.frequency + tree2.frequency;
			Tree newTree = new Tree(newRoot, newFrequency);
			PQ.add(newTree);
		}
    	Tree HuffmanTree = PQ.poll();
    	return new Object[]{HuffmanTree, frequency};
    }
    
    private static int[] getFrequency(String sentence)
    {
    	int[] frequency = new int[AsciiRange];
		for(int index = 0; index < sentence.length(); ++index)
		{
			int intValue = Character.valueOf(sentence.charAt(index));
			++frequency[intValue];
		}
		return frequency;
    }
    private static void printStars()
    {
    	System.out.println("********************");
    }
 }