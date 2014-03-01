package lab3.question1.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * Ported my C++ bitmap to Java.
 * Most significant bit is now the "first bit".
 */

public class BitMap
{
	private int numBytes;
	private long numBits;
	private byte[] array;
	public BitMap()
	{
		numBits = 8;
		numBytes = 1;
		array = new byte[numBytes];
	}
	public BitMap(int inSize)
	{
		numBits = inSize;
		numBytes = inSize / 8;
		if(inSize % 8 != 0) ++numBytes;
		array = new byte[numBytes];
	}
	public boolean getValue(int inIndex)
	{
		int byteIndex = inIndex / 8;
		int shiftOffset = inIndex % 8;
		byte mask = (byte) (128 >> shiftOffset);
		return (array[byteIndex] & mask) != 0;
	}
	public void setValue(int inIndex, int inValue)
	{
		int byteIndex = inIndex / 8;
		int shiftOffset = inIndex % 8;
		if(inValue == 1)
		{
			byte mask = (byte) (128 >> shiftOffset);
			array[byteIndex] |= mask;
		}else
		{
			byte mask = (byte) ~(128 >> shiftOffset);
			array[byteIndex] &= mask;
		}
	}
	public int getNumBytes()
	{
		return numBytes;
	}
	public long getNumBits()
	{
		return numBits;
	}
	public byte[] getArray()
	{
		return array;
	}
	public void toFile(File inFile)
	{
		try
		{
			RandomAccessFile binaryFile = new RandomAccessFile(inFile, "rw");
			binaryFile.writeLong(numBits);
			binaryFile.write(array);
			binaryFile.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static BitMap fromFile(File inFile)
	{
		try{
			RandomAccessFile binaryFile = new RandomAccessFile(inFile, "r");
			long bitSize = binaryFile.readLong();
			System.out.println(bitSize);
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
			return fromByteArray(fileData, bitSize);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static BitMap fromByteArray(byte[] inArray, long inNumBits)
	{
		BitMap myBitMap = new BitMap();
		myBitMap.array = inArray;
		myBitMap.numBytes = inArray.length;
		myBitMap.numBits = inNumBits;
		return myBitMap;
	}
}
