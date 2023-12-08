package _2023_day_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Wasteland_Part_1 {
	static final int INVALID_FILENAME = -1;
	static final int FILE_NOT_FOUND = -2;
	
	public static String get_filename(Scanner keyboardScanner)
	{
		System.out.print("Enter file name: ");
		String filename = keyboardScanner.nextLine();
		
		if (filename.isEmpty() || filename.isBlank() || filename == null)
		{
			System.out.println("Invalid filename entered.");
			System.exit(INVALID_FILENAME);;
		}
		
		return filename;
	}
	
	public static void add_line_to_map(String line, HashMap<String, String> left, HashMap<String, String> right)
	{
		String[] lineSplit = line.split(" ");
		left.put(lineSplit[0], lineSplit[2].substring(1, 4));
		right.put(lineSplit[0], lineSplit[3].substring(0, 3));
		
//		System.out.println("Left = " + lineSplit[2].substring(1, 4));
//		System.out.println("Right = " + lineSplit[3].substring(0, 3));
	}
	
	public static void main(String[] args)
	{
		Scanner keyboardScanner = new Scanner(System.in);
//		String filename = get_filename(keyboardScanner);
		String filename = "input.txt";
		String line = null;
		File file = new File(filename);
		String currentString = "AAA";
		char[] rules = {};
		HashMap<String, String> left = new HashMap<String, String>();
		HashMap<String, String> right = new HashMap<String, String>();
		int moves = 0;
		int rulesIndex = 0;
		
		long startTime = System.nanoTime();
		
		try 
		{
			Scanner fileScanner = new Scanner(file);
			
			// Get rule set and skipping empty line
			rules = fileScanner.nextLine().toCharArray();
			fileScanner.nextLine();
			
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
//				System.out.println(line);
				add_line_to_map(line, left, right);
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
		
		while (!currentString.equals("ZZZ"))
		{
			char currentRule = rules[rulesIndex];
			if (currentRule == 'L')
			{
				currentString = left.get(currentString);
			}
			else
			{
				currentString = right.get(currentString);
			}
			
			// Increase counters and check for bounds
			++moves;
			++rulesIndex;
			if (rulesIndex == rules.length)
			{
				rulesIndex = 0;
			}
		}
		
		System.out.println(currentString + " found after " + moves + " moves.");
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Time: " + duration + " ms");
	}
}
