package day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Seeds_Part_2 {
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
	
	public static void process_line(String line, ArrayList<long[]> list)
	{
		// Tokenize the string
		String[] lineTokens = line.split(" ");
		long[] numbers = {Long.parseLong(lineTokens[0]),
				Long.parseLong(lineTokens[1]),
				Long.parseLong(lineTokens[2])};
		
		list.get(list.size() - 1)[0] = numbers[0];
		list.get(list.size() - 1)[1] = numbers[1];
		list.get(list.size() - 1)[2] = numbers[2];
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
	
	public static void get_seeds(String line, ArrayList<Long> seeds)
	{
		String seedNumbers[] = line.split(" ");
		
		for (int i = 1; i < seedNumbers.length; i += 2)
		{
			for (int j = 0; j < Long.parseLong(seedNumbers[i + 1]); ++j)
			{
				long toAdd = Long.parseLong(seedNumbers[i]) + j;
				if (!seeds.contains(toAdd))
				{
					seeds.add(toAdd);
				}
			}
		}
	}
	
	public static long update_seed(long seedLocation, ArrayList<long[]> list)
	{
		for (long[] range : list)
		{
			if ((range[1] <= seedLocation) && ((range[1] + range[2]) >= seedLocation))
			{
				long offset = seedLocation - range[1];
				return range[0] + offset;
			}
		}
		
		return seedLocation;
	}
	
	public static long get_min(String seedLine, ArrayList<long[]> seedToSoil,
			ArrayList<long[]> soilToFertilizer, ArrayList<long[]> fertilizerToWater,
			ArrayList<long[]> waterToLight, ArrayList<long[]> lightToTemp,
			ArrayList<long[]> tempToHumidity, ArrayList<long[]> humidityToLocation)
	{
		String seedNumbers[] = seedLine.split(" ");
		long min = -1;
		
		for (int i = 1; i < seedNumbers.length; i += 2)
		{
			for (int j = 0; j < Long.parseLong(seedNumbers[i + 1]); ++j)
			{
				long seedLocation = Long.parseLong(seedNumbers[i]) + j;
				seedLocation = update_seed(seedLocation, seedToSoil);
				seedLocation = update_seed(seedLocation, soilToFertilizer);
				seedLocation = update_seed(seedLocation, fertilizerToWater);
				seedLocation = update_seed(seedLocation, waterToLight);
				seedLocation = update_seed(seedLocation, lightToTemp);
				seedLocation = update_seed(seedLocation, tempToHumidity);
				seedLocation = update_seed(seedLocation, humidityToLocation);
				
				if (min == -1)
				{
					min = seedLocation;
					System.out.println("Initial min: " + min);
				}
				else
				{
					if (seedLocation < min)
					{
						System.out.println("Updating " + min + " to " + seedLocation);
						min = seedLocation;
					}
				}
			}
		}
		
		return min;
	}
	
	public static void main(String[] args)
	{
		Scanner keyboardScanner = new Scanner(System.in);
		String filename = get_filename(keyboardScanner);
		String line = null;
		String seeds = null;
		ArrayList<long[]> seedToSoil = new ArrayList<long[]>();
		ArrayList<long[]> soilToFertilizer = new ArrayList<long[]>();
		ArrayList<long[]> fertilizerToWater = new ArrayList<long[]>();
		ArrayList<long[]> waterToLight = new ArrayList<long[]>();
		ArrayList<long[]> lightToTemp = new ArrayList<long[]>();
		ArrayList<long[]> tempToHumidity = new ArrayList<long[]>();
		ArrayList<long[]> humidityToLocation = new ArrayList<long[]>();
		File file = new File(filename);
		int section_number = -1;
		long min = -1;
		
		try 
		{
			Scanner fileScanner = new Scanner(file);
			
			// Get seed numbers
			seeds = fileScanner.nextLine();
			
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				if (line.length() == 0)
				{
					continue;
				}
				if (get_section_number(line) != -1)
				{
					section_number = get_section_number(line);
					continue;
				}
				if (section_number == SEED_TO_SOIL)
				{
					seedToSoil.add(new long[3]);
					process_line(line, seedToSoil);
					continue;
				}
				
				if (section_number == SOIL_TO_FERTILIZER)
				{
					soilToFertilizer.add(new long[3]);
					process_line(line, soilToFertilizer);
					continue;
				}
				
				if (section_number == FERTILIZER_TO_WATER)
				{
					fertilizerToWater.add(new long[3]);
					process_line(line, fertilizerToWater);
					continue;
				}
				
				if (section_number == WATER_TO_LIGHT)
				{
					waterToLight.add(new long[3]);
					process_line(line, waterToLight);
					continue;
				}
				
				if (section_number == LIGHT_TO_TEMP)
				{
					lightToTemp.add(new long[3]);
					process_line(line, lightToTemp);
					continue;
				}
				
				if (section_number == TEMP_TO_HUMIDITY)
				{
					tempToHumidity.add(new long[3]);
					process_line(line, tempToHumidity);
					continue;
				}
				
				if (section_number == HUMIDITY_TO_LOCATION)
				{
					humidityToLocation.add(new long[3]);
					process_line(line, humidityToLocation);
					continue;
				}
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.print("File not found.");
		}
		
		min = get_min(seeds, seedToSoil, soilToFertilizer, fertilizerToWater, waterToLight, lightToTemp, tempToHumidity, humidityToLocation);
		
		System.out.println("Min: " + min);
	}
}
