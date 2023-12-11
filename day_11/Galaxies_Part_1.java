package _2023_day_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;

public class Galaxies_Part_1 {
	static final int INVALID_FILENAME = -1;
	static final int FILE_NOT_FOUND = -2;
	
	public static String get_filename()
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
	
	public static void process_line(String line, ArrayList<ArrayList<Character>> galaxy)
	{
		char[] lineAsCharArray = line.toCharArray();
		
		// Creating a new line and adding the characters
		galaxy.add(new ArrayList<Character>());
		int sizeOfGalaxy = galaxy.size();
		for (char point : lineAsCharArray)
		{
			galaxy.get(sizeOfGalaxy - 1).add(point);
		}
	}
	
	public static void check_empty_vertical(ArrayList<ArrayList<Character>> galaxy)
	{
		// Starting on right side, check for empty column
		for (int column = galaxy.get(0).size() - 1; column >= 0; --column)
		{
			// If column contains '#', it isn't empty
			boolean isEmpty = true;
			for (int row = 0; row < galaxy.size(); ++row)
			{
				if (galaxy.get(row).get(column) == '#')
				{
					isEmpty = false;
				}
			}
			
			// If we found no galaxies, add a column of '.'
			if (isEmpty)
			{
				for (int row = 0; row < galaxy.size(); ++row)
				{
					galaxy.get(row).add(column, '.');
				}
			}
		}
	}
	
	public static void check_empty_horizontal(ArrayList<ArrayList<Character>> galaxy)
	{
		// Starting on bottom, check for empty row
		for (int row = galaxy.size() - 1; row >= 0; --row)
		{
			// If row contains '#', it isn't empty
			boolean isEmpty = true;
			for (int column = 0; column < galaxy.get(row).size(); ++column) 
			{
				if (galaxy.get(row).get(column) == '#')
				{
					isEmpty = false;
				}
			}
			
			// If we found no galaxies, add a row of '.'
			if (isEmpty)
			{
				galaxy.add(row, new ArrayList<Character>());
				for (int column = 0; column < galaxy.get(0).size(); ++column)
				{
					galaxy.get(row).add('.');
				}
			}
		}
	}
	
	public static void get_galaxies(ArrayList<ArrayList<Character>> galaxy, ArrayList<int[]> galaxies)
	{
		for (int row = 0; row < galaxy.size(); ++row)
		{
			for (int column = 0; column < galaxy.get(row).size(); ++column)
			{
				if (galaxy.get(row).get(column) == '#')
				{
//					System.out.println("Found galaxy at [" + row + ", " + column + "]");
					galaxies.add(new int[2]);
					galaxies.get(galaxies.size() - 1)[0] = row;
					galaxies.get(galaxies.size() - 1)[1] = column;
				}
			}
		}
	}
	
	public static int calculate_galaxy_distances(ArrayList<int[]> galaxies)
	{
		int sumOfLengths = 0;
		
		for (int i = 0; i < galaxies.size(); ++i)
		{
			for (int j = i + 1; j < galaxies.size(); ++j)
			{
				// Get the difference of X and Y of galaxies and add to sum
				System.out.println("Adding default " + (Math.abs(galaxies.get(i)[0] - galaxies.get(j)[0]) + Math.abs(galaxies.get(i)[1] - galaxies.get(j)[1])));
				sumOfLengths += Math.abs(galaxies.get(i)[0] - galaxies.get(j)[0]) + Math.abs(galaxies.get(i)[1] - galaxies.get(j)[1]);
			}
		}
		
		return sumOfLengths;
	}
	
	public static void main(String[] args)
	{
		String filename = get_filename();
//		String filename = "input.txt";
		File file = new File(filename);
		String line = null;
		ArrayList<ArrayList<Character>> galaxy = new ArrayList<ArrayList<Character>>();
		
		long startTime = System.nanoTime();
		
		// To start, just put the file in a 2D array
		try 
		{
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				process_line(line, galaxy);
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
		// Check columns for empty space
		check_empty_vertical(galaxy);

		// Check rows for empty space
		check_empty_horizontal(galaxy);
		
		// Get a list of the galaxies
		ArrayList<int[]> galaxies = new ArrayList<int[]>();
		get_galaxies(galaxy, galaxies);
		
		int sumOfLengths = calculate_galaxy_distances(galaxies);
		
		System.out.println("Sum of lengths: " + sumOfLengths);
		
//		for (ArrayList<Character> galaxyLine : galaxy)
//		{
//			for (int index = 0; index < galaxyLine.size(); ++index)
//			{
//				System.out.print(galaxyLine.get(index));
//				if (index == galaxyLine.size() - 1) { System.out.println(); }
//			}
//		}
//		
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Time: " + duration + " ms");
	}
}
