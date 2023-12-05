package day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Seeds_Part_1 {
	static final int INVALID_FILENAME = -1;
	
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
	
	public static void get_seeds(String line, ArrayList<Integer> seeds)
	{
		Scanner lineScanner = new Scanner(line);
		String seedNumbers[] = line.split(" ");
		
		for (int i = 1; i < seedNumbers.length; ++i)
		{
			seeds.add(Integer.parseInt(seedNumbers[i]));
		}
		
		lineScanner.close();
	}
	
	public static void main(String[] args)
	{
		Scanner keyboardScanner = new Scanner(System.in);
		String filename = get_filename(keyboardScanner);
		String line = null;
		ArrayList<Integer> seeds = new ArrayList<Integer>();
		File file = new File(filename);
		
		try 
		{
			Scanner fileScanner = new Scanner(file);
			
			// Get seed numbers
			line = fileScanner.nextLine();
			get_seeds(line, seeds);
			
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				System.out.println("Line: " + line);
				get_seeds(line, seeds);
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.print("File not found.");
		}
		
		System.out.println("Seed numbers " + seeds);
	}
}
