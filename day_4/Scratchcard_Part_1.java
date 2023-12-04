package day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Scratchcard_Part_1{
	// Constants
	static final int INVALID_FILENAME = -1;
	
	public static int process_game(String[] game)
	{
		String winningNumbers = game[0];
		String gameNumbers = game[1];
		int numberOfMatches = 0;
		
		// Tokenize both
		String[] winningNumbersToken = winningNumbers.split(" ");
		String[] gameNumbersToken = gameNumbers.split(" ");
		
		for (String winningNumber : winningNumbersToken)
		{
			for (String gameNumber : gameNumbersToken)
			{
				if (winningNumber.equals(gameNumber))
				{
					++numberOfMatches;
					break;
				}
			}
		}
		
		System.out.println("# matches = " + numberOfMatches);
		
		if (numberOfMatches == 0)
		{
			return 0;
		}
		else
		{
			return (int) Math.pow(2, (numberOfMatches - 1));
		}
	}
	
	public static String[] add_game_to_list(String line, ArrayList<String[]> games)
	{
		String[] splitGame = line.split("\\|");
		
		// Process winning numbers
		Scanner numberScanner = new Scanner(splitGame[0]);
		String winningNumbers = "";
		
		// Throw first to tokens for 'Card' and card#
		numberScanner.next();
		numberScanner.next();
		
		while (numberScanner.hasNext())
		{
			winningNumbers += numberScanner.next() + " ";
		}
		splitGame[0] = winningNumbers;
		
		numberScanner.close();		
		return splitGame;
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
		ArrayList<String[]> games = new ArrayList<String[]>();
		File file = new File(filename);
		int sum = 0;
		int gameNumber = 0;
		
		try 
		{
			Scanner fileScanner = new Scanner(file);
			// Get all the lines as char arrays to easily index
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				games.add(add_game_to_list(line, games));
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.print("File not found.");
		}
		
	
		for (String[] game : games)
		{
			++gameNumber;
			System.out.println("Winning numbers: " + game[0] + " Numbers: " + game[1]);
			sum += process_game(game);
		}
		
		System.out.println("Sum: " + sum);
	}
}
