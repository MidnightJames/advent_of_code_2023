package day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cube_Part_1 {
	// Constants
	static final int INVALID_FILENAME = -1;
	static final int MAX_RED_CUBES = 12;
	static final int MAX_GREEN_CUBES = 13;
	static final int MAX_BLUE_CUBES = 14;
	
	public static boolean is_valid_cube_amount(int numberOfCubes, String cubeColor)
	{
		if (cubeColor.startsWith("r") && numberOfCubes <= MAX_RED_CUBES)
		{
			return true;
		}
		
		if (cubeColor.startsWith("g") && numberOfCubes <= MAX_GREEN_CUBES)
		{
			return true;
		}
		
		if (cubeColor.startsWith("b") && numberOfCubes <= MAX_BLUE_CUBES)
		{
			return true;
		}
		
		return false;
	}
	
	public static int process_game(String game)
	{
		Scanner gameScanner = new Scanner(game);
		
		// Throwing away 'Game'
		gameScanner.next();
		
		// Getting game number
		String gameNumberAsString = gameScanner.next();
		int gameNumber = Integer.parseInt(gameNumberAsString.substring(0, gameNumberAsString.length() - 1));
		
		while (gameScanner.hasNext())
		{
			// Pattern is "## <Color>"
			int numberOfCubes = Integer.parseInt(gameScanner.next());
			String cubeColor = gameScanner.next();
			
			// If we go over max cubes, the game is invalid
			if (!is_valid_cube_amount(numberOfCubes, cubeColor))
			{
				gameScanner.close();
				return 0;
			}
		}
		
		gameScanner.close();
		return gameNumber;
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
//				System.out.println(line);
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
