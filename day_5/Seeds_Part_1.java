package day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Seeds_Part_1 {
	static final int INVALID_FILENAME = -1;
	static final int SEED_TO_SOIL = 1;
	static final int SOIL_TO_FERTILIZER = 2;
	static final int FERTILIZER_TO_WATER = 3;
	static final int WATER_TO_LIGHT = 4;
	static final int LIGHT_TO_TEMP = 5;
	static final int TEMP_TO_HUMIDITY = 6;
	static final int HUMIDITY_TO_LOCATION = 7;
	
	public static int get_section_number(String line)
	{
		if (line.contains("seed-to-soil"))
		{
			return SEED_TO_SOIL;
		}
		if (line.contains("soil-to-fertilizer"))
		{
			return SOIL_TO_FERTILIZER;
		}
		if (line.contains("fertilizer-to-water"))
		{
			return FERTILIZER_TO_WATER;
		}
		if (line.contains("water-to-light"))
		{
			return WATER_TO_LIGHT;
		}
		if (line.contains("light-to-temperature"))
		{
			return LIGHT_TO_TEMP;
		}
		if (line.contains("temperature-to-humidity"))
		{
			return TEMP_TO_HUMIDITY;
		}
		if (line.contains("humidity-to-location"))
		{
			return HUMIDITY_TO_LOCATION;
		}
		
		return -1;
	}
	
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
		int section_number = -1;
		
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
				if (get_section_number(line) != -1)
				{
					section_number = get_section_number(line);
					continue;
				}
				System.out.println("Section Number: " + section_number);
				if (section_number == SEED_TO_SOIL)
				{
					
				}
				
				if (section_number == SOIL_TO_FERTILIZER)
				{
					
				}
				
				if (section_number == FERTILIZER_TO_WATER)
				{
					
				}
				
				if (section_number == WATER_TO_LIGHT)
				{
					
				}
				
				if (section_number == LIGHT_TO_TEMP)
				{
					
				}
				
				if (section_number == TEMP_TO_HUMIDITY)
				{
					
				}
				
				if (section_number == HUMIDITY_TO_LOCATION)
				{
					
				}
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
