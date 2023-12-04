package day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Scratchcard_Part_2{
	// Constants
	static final int INVALID_FILENAME = -1;
	
	public static void process_game(String[] game, ArrayList<Integer> numberOfCards, int gameNumber)
	{
		String winningNumbers = game[0];
		String numbers = game[1];
		int numberOfMatches = 0;
		
		// Tokenize both
		String[] winningNumbersToken = winningNumbers.split(" ");
		String[] numbersToken = numbers.split(" ");
		
		for (String winningNumber : winningNumbersToken)
		{
			for (String number : numbersToken)
			{
				if (winningNumber.equals(number))
				{
					++numberOfMatches;
					break;
				}
			}
		}
		
		for (int i = 0; i < numberOfMatches; ++i)
		{
			numberOfCards.set((gameNumber + i + 1), numberOfCards.get(gameNumber + i + 1) + numberOfCards.get(gameNumber));
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
		ArrayList<Integer> numberOfCards = new ArrayList<Integer>();
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
		
		// Initialize number of cards
		numberOfCards.add(0);
		
		for (int i = 1; i <= games.size(); i++)
		{
			numberOfCards.add(1);
		}
	
		for (String[] game : games)
		{
			++gameNumber;
			System.out.println("Winning numbers: " + game[0] + " Numbers: " + game[1]);
			process_game(game, numberOfCards, gameNumber);
		}
		
		for (int number : numberOfCards)
		{
			sum += number;
		}
		
		System.out.println("Sum: " + sum);
	}
}
