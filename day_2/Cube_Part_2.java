package day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cube_Part_2 {
	// Constants
	static final int INVALID_FILENAME = -1;
	
	public static void update_cubes(int[] numberOfColorsNeeded, int numberOfCubes, String cubeColor)
	{
		if (cubeColor.startsWith("r") && numberOfCubes > numberOfColorsNeeded[0])
		{
			numberOfColorsNeeded[0] = numberOfCubes;
			return;
		}
		
		if (cubeColor.startsWith("g") && numberOfCubes > numberOfColorsNeeded[1])
		{
			numberOfColorsNeeded[1] = numberOfCubes;
			return;
		}
		
		if (cubeColor.startsWith("b") && numberOfCubes > numberOfColorsNeeded[2])
		{
			numberOfColorsNeeded[2] = numberOfCubes;
			return;
		}
		
		return;
	}
	
	public static int process_game(String game)
	{
		Scanner gameScanner = new Scanner(game);
		
		// Throwing away 'Game' and game number
		gameScanner.next();
		gameScanner.next();
		
		int[] numberOfColorsNeeded = {0, 0, 0};
		
		while (gameScanner.hasNext())
		{
			// Pattern is "## <Color>"
			int numberOfCubes = Integer.parseInt(gameScanner.next());
			String cubeColor = gameScanner.next();
			
			update_cubes(numberOfColorsNeeded, numberOfCubes, cubeColor);
		}
		
		gameScanner.close();
		return numberOfColorsNeeded[0] * numberOfColorsNeeded[1] * numberOfColorsNeeded[2];
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
	
	public static void main(String[] args)
	{
		Scanner keyboardScanner = new Scanner(System.in);
		String filename = get_filename(keyboardScanner);
		String line = null;
		File file = new File(filename);
		int sum = 0;
		
		try 
		{
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				sum += process_game(line);
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.print("File not found.");
		}
		
		System.out.println("Sum: " + sum);
	}
}
