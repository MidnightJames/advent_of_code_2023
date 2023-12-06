package _2023_day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Racing_Part_1 {
	
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
	
	public static int process_games(String timesString, String distancesString, ArrayList<Integer> times, ArrayList<Integer> distances)
	{
		// Create tokens from strings
		String[] timesArray = timesString.split(" ");
		String[] distancesArray = distancesString.split(" ");
		int product = 0;
		
		for (int i = 1; i < timesArray.length; ++i)
		{
			int currentGameTime = Integer.parseInt(timesArray[i]);
			int currentGameDistance = Integer.parseInt(distancesArray[i]);
			
			// Left side
			int secondsHeld = 0;
			while ((currentGameTime - secondsHeld) * secondsHeld <= currentGameDistance)
			{
				++secondsHeld;
			}
			System.out.println("Found " + secondsHeld + " gives distance " + ((currentGameTime - secondsHeld) * secondsHeld) + " > " + currentGameDistance);
			int left = secondsHeld;
			
			// Reset and check from right
			secondsHeld = currentGameTime;
			while ((currentGameTime - secondsHeld) * secondsHeld <= currentGameDistance)
			{
				--secondsHeld;
			}
			System.out.println("Found " + secondsHeld + " gives distance " + ((currentGameTime - secondsHeld) * secondsHeld) + " > " + currentGameDistance);
			int right = secondsHeld;
			
			System.out.println("Difference = " + (right - left + 1));
			
			if (product == 0)
			{
				product = right - left + 1;
			}
			else
			{
				product *= (right - left + 1);
			}
		}
		
		return product;
	}
	
	public static void main(String[] args)
	{
		String filename = get_filename();
		File file = new File(filename);
		ArrayList<Integer> times = new ArrayList<Integer>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		int product = 0;
		
		try 
		{
			Scanner fileScanner = new Scanner(file);
			String timesString = fileScanner.nextLine();
			String distancesString = fileScanner.nextLine();
			fileScanner.close();
			product = process_games(timesString, distancesString, times, distances);
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
		System.out.println("Product = " + product);
	}
}
