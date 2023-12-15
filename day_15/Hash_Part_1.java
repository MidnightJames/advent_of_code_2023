package _2023_day_15;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.StringBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class Hash_Part_1
{
	static final int INVALID_FILENAME = -1;
	static final int FILE_NOT_FOUND = -2;
	static final int MULTIPLIER = 17;
	static final int MOD = 256;
	
	public static String getFilename()
	{
		Scanner keyboardScanner = new Scanner(System.in);
		System.out.print("Enter file name: ");
		String filename = keyboardScanner.nextLine();
		
		if (filename.isEmpty() || filename.isBlank() || filename == null)
		{
			System.out.println("Invalid filename entered.");
			System.exit(INVALID_FILENAME);;
		}
		
		keyboardScanner.close();
		return filename;
	}
	
	public static int getHash(String s)
	{
		char[] sequence = s.toCharArray();
		int currentValue = 0;
		
		for (char character : sequence)
		{
			currentValue += (int) character;
			currentValue *= MULTIPLIER;
			currentValue %= MOD;
		}
		
//		System.out.println("After " + s + ": " + currentValue);
		return currentValue;
	}
	
	public static void main(String[] args)
	{
		String filename = getFilename();
		File file = new File(filename);
		String line = null;
		String[] sequences = null;
		
		long startTime = System.nanoTime();
		
		try 
		{	
			StringBuffer stringBuffer = new StringBuffer();
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				stringBuffer.append(line);
			}
			sequences = stringBuffer.toString().split(",");
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
		int sum = 0;
		for (String sequence : sequences)
		{
//			System.out.println("Processing " + sequence);
			sum += getHash(sequence);
		}
		
		System.out.println("Sum = " + sum);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Time: " + duration + " ms");
	}
}
