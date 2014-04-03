package lab6.bonus.secondyear;

import java.math.BigInteger;

public class BabyStepsGiantSteps
{
	// I'll find a way to change this later, have to make a square root finder for big integers...
	static int important = 292838;
	static BigInteger[] workSpace = new BigInteger[important];
	public static void main(String args[])
	{
		/*
		System.out.print("Please enter in your value for p: ");
		String part1 = sc.nextLine();
		BigInteger p = new BigInteger("" + part1);
		*/
		BigInteger p = new BigInteger("85754635859");
		/*
		System.out.print("Please enter in your value for g: ");
		String part2 = sc.nextLine();
		BigInteger g = new BigInteger("" + part2);
		*/
		BigInteger g = new BigInteger("34109547043");
		/*
		System.out.print("Please enter in your value for y: ");
		String part3 = sc.nextLine();
		BigInteger y = new BigInteger("" + part3);
		*/
		BigInteger y = new BigInteger("181673");
		//System.out.println(keyFinder(p, g, y));
		BigInteger privateKey = keyFinder(p, g, y);

		// Now bob's cipher
		BigInteger c1 = new BigInteger("26398053246");
		BigInteger c2 = new BigInteger("72955071594");


		BigInteger one = new BigInteger("1");
		BigInteger c1x = c1.modPow(p.subtract(privateKey.add(one)), p);
		System.out.println("c1x is: " + c1x);
		BigInteger message = c2.multiply(c1x);
		message = message.mod(p);
		System.out.println("The private key is: " + privateKey);
		System.out.println("The message is: " + message);
	}


	public static BigInteger keyFinder(BigInteger p, BigInteger g, BigInteger y)
	{
		BigInteger m = new BigInteger("" + important);
		BigInteger[] stuff = new BigInteger[important];
		int k = 0;
		workSpace = new BigInteger[important];

		// All these things have to be kept in big integers for handyness
		BigInteger j = new BigInteger("0");
		BigInteger one = new BigInteger("1");
		// Calculate the initial g ^ j for all j
		while(k < important)
		{
			stuff[k] = g.modPow(j, p);
			k ++;
			// Need to keep everything in BigInts for it to bloody work, which is why I'm adding one like this
			j = j.add(one);
		}

		// Also need to copy the array in it's original form. This is handy for when trying to find the power of j used
		BigInteger[] copy = new BigInteger[important];
		// Copying into this array
		for(int z = 0; z < stuff.length; z ++)
		{
			copy[z] = stuff[z];
		}

		// This just sorts it, from highest to lowest
		stuff = mergeSort(stuff, 0, stuff.length - 1);
		// I didn't like highest to lowest so I just reversed it :)
		stuff = reverse(stuff);


		// Key is the private key, which I shall now find
		BigInteger key = new BigInteger("0");
		// In the notes you need c, which is defined as c = g^-m mod p
		BigInteger c = (g.modPow(m.negate(), p));
		// You also need t, which is just y
		BigInteger t = y;


		// Now this loop basically finds the power of i and stuff
		for(int i = 0; i < important; i ++)
		{
			// If we find t in the sorted stuff array
			if(recFind(stuff, t, 0, stuff.length - 1) != -1)
			{
				// Then we find the power of j used in that value by searching the copy of the original array, whatever index it finds it as will be the power of j
				for(int find = 0; find < copy.length; find ++)
				{
					// So if it finds it it then uses these values to work out x
					if(copy[find].equals(t))
					{

						// x = m.i + j
						BigInteger part1 = new BigInteger("" + i);
						BigInteger part2 = new BigInteger("" + find);
						key =(m.multiply(part1)).add(part2);
						// Then we need to break out of this nested loop
						break;
					}

				}
				// And break again to get out of it's parent
				break;
			}
			else
			{
				// If the value for t wasn't in the array, redefine it and search again
				// When you redefine t it will be t = t.c mod p
				t = (t.multiply(c)).mod(p);
			}
		}
		// Found that shit!
		return key;
	}


	// Modified Phils' merge sort code to take in and RETURN a sorted array, one
	// catch though... You need a global array called workSpace
	public static BigInteger[] mergeSort(BigInteger[] array, int left, int right) {
		int mid = (left + right) / 2; // computes midpoint
		if (left == right) // base case
			return null;
		mergeSort(array, left, mid);
		mergeSort(array, mid + 1, right);
		for (int i = left; i <= right; i++)
			workSpace[i] = array[i]; // copies entire array into workspace
		int i1 = left;

		int i2 = mid + 1;

		// The only line I changed in this is the third condition, I changed it
		// so it could work on big integers. That's all
		for (int curr = left; curr <= right; curr++) { // merge workspace
			if (i1 > mid) { // copies all remnants in
				array[curr] = workSpace[i2++];
			} else if (i2 > right) { // copies all remnants in
				array[curr] = workSpace[i1++];
			} else if (workSpace[i1].compareTo(workSpace[i2]) < 0) {
				array[curr] = workSpace[i1++]; // merge
			} else {
				array[curr] = workSpace[i2++]; // merge
			}
		}
		return array;
	}

	// Didn't want to clutter the main with this code, so I cluttered down here!
	public static BigInteger[] reverse(BigInteger[] array) {
		BigInteger[] temp = new BigInteger[array.length];

		for (int i = 0; i < array.length; i++) {
			temp[i] = array[i];
		}

		int reversing = 0;

		for (int i = array.length - 1; i >= 0; i--) {
			array[i] = temp[reversing];
			reversing++;
		}
		return array;
	}

	// Again taken from Phil's notes, just changing it's parameters and how it
	// judges size of items. The 2nd "if" statement is where it was changed.
	public static int recFind(BigInteger[] array, BigInteger searchKey,
			int lowerBound, int upperBound) {

		int middle = (lowerBound + upperBound) / 2;

		if (array[middle].compareTo(searchKey) == 0)
			return middle; // found it
		else if (lowerBound > upperBound)
			return -1; // can't find it
		else {
			// divide range
			if (array[middle].compareTo(searchKey) > 0) // it's in upper half
				return recFind(array, searchKey, middle + 1, upperBound);
			else
				// it's in lower half
				return recFind(array, searchKey, lowerBound, middle - 1);
		} // end else divide range
	}
}
