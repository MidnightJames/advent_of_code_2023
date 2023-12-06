package _2023_day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Racing_Part_2 {
	
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
	
	public static long process_games(String timesString, String distancesString, ArrayList<Integer> times, ArrayList<Integer> distances)
	{
		// Create tokens from strings and combine into one race
		String[] timesArray = timesString.split(" ");
		String[] distancesArray = distancesString.split(" ");
		
		String timeString = "";
		String distanceString = "";
		
		for (int i = 1; i < timesArray.length; ++i)
		{
			timeString += timesArray[i];
			distanceString += distancesArray[i];
		}
		
		long time = Long.parseLong(timeString);
		long distance = Long.parseLong(distanceString);
		
		// Left side
		long secondsHeld = 0;
		while ((time - secondsHeld) * secondsHeld <= distance)
		{
			++secondsHeld;
		}
//		System.out.println("Found " + secondsHeld + " gives distance " + ((time - secondsHeld) * secondsHeld) + " > " + distance);
		long left = secondsHeld;
		
		// Reset and check from right
		secondsHeld = time;
		while ((time - secondsHeld) * secondsHeld <= distance)
		{
			--secondsHeld;
		}
//		System.out.println("Found " + secondsHeld + " gives distance " + ((time - secondsHeld) * secondsHeld) + " > " + distance);
		long right = secondsHeld;
		
		return (right - left + 1);
	}
	
	public static void main(String[] args)
	{
		String filename = "input.txt";
		File file = new File(filename);
		ArrayList<Integer> times = new ArrayList<Integer>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		long possibilities = 0;
		
		try 
		{
			Scanner fileScanner = new Scanner(file);
			String timesString = fileScanner.nextLine();
			String distancesString = fileScanner.nextLine();
			fileScanner.close();
			long startTime = System.nanoTime();
			possibilities = process_games(timesString, distancesString, times, distances);
			long endTime = System.nanoTime();
			// Divide by 1000000 for milliseconds
			long duration = (endTime - startTime) / 1000000;
			System.out.println("Time: " + duration + " ms");
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
		System.out.println("Possibilities = " + possibilities);
	}
}
