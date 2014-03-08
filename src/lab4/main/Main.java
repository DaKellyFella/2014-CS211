package lab4.main;

import java.util.Scanner;

import lab4.question1.FileIO;
import lab4.question1.Tree;

public class Main 
{
	public static void main(String args[])
	{
		FileIO myFile = new FileIO();
		Tree dictionaryTree = new Tree(myFile.load("./bin/dictionary.txt"));
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter in a word: ");
		String lookUp = sc.nextLine();
		while(lookUp.compareTo("") != 0)
		{
			long start = System.currentTimeMillis();
			String answer = dictionaryTree.getLargestWord(lookUp.toLowerCase());
			long end = System.currentTimeMillis() - start;
			System.out.println("Answer is: " + answer + " and it took: " + end + "ms");
			System.out.print("Enter in a new word: ");
			lookUp = sc.nextLine();
		}
		sc.close();
	}
}
