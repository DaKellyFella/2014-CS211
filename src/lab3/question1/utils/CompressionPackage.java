package lab3.question1.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

/*
 * Will contain the tree and the bitmap encoded string.
 */
public class CompressionPackage 
{
	public BitVector compressedString;
	public Tree huffmanTree;
	public CompressionPackage(){}
	public CompressionPackage(BitVector inCompressedString, Tree inHuffmanTree)
	{
		this.compressedString = inCompressedString;
		this.huffmanTree = inHuffmanTree;
	}
	public void writePackage(File compressedFile)
	{
		try{
			// Hopefully all I need to do.
			RandomAccessFile outputBinaryFile = new RandomAccessFile(compressedFile, "rw");
			int treeSize = sizeof(huffmanTree);
			outputBinaryFile.writeInt(treeSize);
			byte[] treeInBytes = serialize(huffmanTree);
			outputBinaryFile.write(treeInBytes);
			outputBinaryFile.writeLong(compressedString.getNumBits());
			outputBinaryFile.write(compressedString.getArray());
			outputBinaryFile.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static CompressionPackage retrivePackage(File compressedFile)
	{
		CompressionPackage retrivedPackage = new CompressionPackage();
		try 
		{
			RandomAccessFile binaryFile = new RandomAccessFile(compressedFile, "r");
			int treeSize = binaryFile.readInt();
			byte[] treeBytes = new byte[treeSize];
			binaryFile.readFully(treeBytes, 0, treeSize);
			retrivedPackage.huffmanTree = (Tree)deserialize(treeBytes);
			
			// Length of BitMap's array
			long bitSize = binaryFile.readLong();
			long longLength = binaryFile.length() - binaryFile.getFilePointer();
			int arrayLength = (int)longLength;
			if(arrayLength != longLength)
			{
				binaryFile.close();
				return null;
			}
			byte[] fileData = new byte[arrayLength];
			binaryFile.readFully(fileData, 0, arrayLength);
			binaryFile.close();
			retrivedPackage.compressedString = BitVector.fromByteArray(fileData, bitSize);
		}catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return retrivedPackage;
	}
	private static int sizeof(Object obj)
	{
		try{
			ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
			objectOutputStream.close();
			return byteOutputStream.toByteArray().length;
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	    return -1;
	}
	
	private static byte[] serialize(Object obj) throws IOException
	{
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	private static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
	}
}
