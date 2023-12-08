package _2023_day_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Wasteland_Part_2 {
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
		char[] rules = {};
		HashMap<String, String> left = new HashMap<String, String>();
		HashMap<String, String> right = new HashMap<String, String>();
		ArrayList<String> nodes = new ArrayList<String>();
		ArrayList<Integer> cycles = new ArrayList<Integer>();
		ArrayList<Integer> cycleCounter = new ArrayList<Integer>();
		boolean gamesAreValid = false;
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
		
		// Get all valid starting points
		for (String start : left.keySet())
		{
			if (start.charAt(2) == 'A')
			{
				nodes.add(start);
				cycles.add(0);
				cycleCounter.add(0);
				System.out.println(start);
			}			
		}
		
		
		
		while (moves < 100000)
		{
			++moves;
			char currentRule = rules[rulesIndex];
			if (currentRule == 'L')
			{
				for (int i = 0; i < nodes.size(); ++i)
				{
//					System.out.println("Setting " + nodes.get(i) + " to " + left.get(nodes.get(i)));
					nodes.set(i, left.get(nodes.get(i)));
					if (nodes.get(i).charAt(2) == 'Z')
					{
						cycles.set(i,  cycleCounter.get(i));
						System.out.println(cycles);
						cycleCounter.set(i,  0);
					}
				}
			}
			else
			{
				for (int i = 0; i < nodes.size(); ++i)
				{
//					System.out.println("Setting " + nodes.get(i) + " to " + right.get(nodes.get(i)));
					nodes.set(i, right.get(nodes.get(i)));
					if (nodes.get(i).charAt(2) == 'Z')
					{
						cycles.set(i,  cycleCounter.get(i));
						System.out.println(cycles);
						cycleCounter.set(i,  0);
					}
				}
			}
			
			// Increase all counters by 1
			for (int i = 0; i < cycleCounter.size(); ++i)
			{
				cycleCounter.set(i, cycleCounter.get(i) + 1);
			}
			
			// Increase counters and check for bounds
			
			++rulesIndex;
			if (rulesIndex == rules.length)
			{
				rulesIndex = 0;
			}
		}
		
		System.out.println("Nodes ending with 'Z' found after " + moves + " moves.");
		
		// Manually find LCM using array values because coding is hard :((
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Time: " + duration + " ms");
	}
}
