package _2023_day_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Camel_Part_2 {	
	static final int INVALID_FILENAME = -1;
	static final int FILE_NOT_FOUND = -2;
	static final int INVALID_GAME = -3;
	static final int FIVE_OF_A_KIND = 0;
	static final int FOUR_OF_A_KIND = 1;
	static final int FULL_HOUSE = 2;
	static final int THREE_OF_A_KIND = 3;
	static final int TWO_PAIR = 4;
	static final int ONE_PAIR = 5;
	static final int HIGH_CARD = 6;
	
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
	
	public static void add_line_to_hand(String line, HashMap<String, Integer> hands)
	{
		String[] tokens = line.split(" ");
		
		String hand = tokens[0];
		int value = Integer.parseInt(tokens[1]);
		
		hands.put(hand, value);
	}
	
	public static int process_game(String hand)
	{
		char[] handAsArray = hand.toCharArray();
		HashMap<Character, Integer> count = new HashMap<Character, Integer>();
		
		for (char card : handAsArray)
		{
			if (count.containsKey(card))
			{
				count.put(card, count.get(card) + 1);
			}
			else
			{
				count.put(card,  1);
			}
		}
		
		// If we have any J, convert them to something else
		if (count.containsKey('J') && count.get('J') != 5)
		{
			int amount = count.get('J');
			char toAdd = 'J';
			count.remove('J');
			// Go through each value and check where to put the j's
			for (char card: count.keySet())
			{
				// If anything other than J, put that card in
				if (toAdd == 'J')
				{
					toAdd = card;
				}
				// If # cards is equal, get higher value
				else if (count.get(toAdd) == count.get(card))
				{
					if (get_digit(card) > get_digit(toAdd))
					{
						toAdd = card;
					}
				}
				// If # cards > then current to add, update to add
				else if(count.get(toAdd) < count.get(card))
				{
					toAdd = card;
				}
			}
			count.put(toAdd, amount + count.get(toAdd));
		}
		
		int uniqueCards = count.keySet().size();
		System.out.println("Processing " + hand + " with " + uniqueCards + " unique cards.");
		
		// Only high card
		if (uniqueCards == 5)
		{
			return HIGH_CARD;
		}
		
		// Only one pair
		if (uniqueCards == 4)
		{
			return ONE_PAIR;
		}
		
		// Two pair or three of a kind
		if (uniqueCards == 3)
		{
			if (count.containsValue(3))
			{
				return THREE_OF_A_KIND;
			}
			return TWO_PAIR;
		}
		
		// Full house or four of a kind
		if (uniqueCards == 2)
		{
			if (count.containsValue(4))
			{
				return FOUR_OF_A_KIND;
			}
			return FULL_HOUSE;
		}
		
		if (uniqueCards == 1)
		{
			return FIVE_OF_A_KIND;
		}
		
		return -1;
	}
	
	public static int get_digit(char digit)
	{
		if (Character.isDigit(digit))
		{
			return Character.getNumericValue(digit);
		}
		
		if (digit == 'T')
		{
			return 10;
		}
		
		if (digit == 'J')
		{
			return 1;
		}
		
		if (digit == 'Q')
		{
			return 12;
		}
		
		if (digit == 'K')
		{
			return 13;
		}
		
		if (digit == 'A')
		{
			return 14;
		}
		
		return -1;
	}
	
	public static void get_order(ArrayList<String> games, ArrayList<String> orderedGames)
	{
		// Time for a terribly inefficient loop
		while (!games.isEmpty())
		{
			String lowest = games.get(0);
			int lowestIndex = 0;
			
			for (int i = 1; i < games.size(); ++i)
			{
				for (int j = 0; j < lowest.length(); ++j)
				{
					if (lowest.charAt(j) != games.get(i).charAt(j))
					{
						int lowestInt = get_digit(lowest.charAt(j));
						int testInt = get_digit(games.get(i).charAt(j));
						
						System.out.println("Testing lowest: " + lowestInt + " (" + lowest + ") vs test: " + testInt + " (" + games.get(i) + ")");
						if (testInt < lowestInt)
						{
							lowest = games.get(i);
							lowestIndex = i;
							break;
						}
						if (lowestInt < testInt)
						{
							break;
						}
					}
				}
			}
			
			orderedGames.add(lowest);
			games.remove(lowestIndex);
		}
	}
	
	public static void main(String[] args)
	{
		Scanner keyboardScanner = new Scanner(System.in);
		String filename = get_filename(keyboardScanner);
		String line = null;
		File file = new File(filename);
		HashMap<String, Integer> hands = new HashMap<String, Integer>();
		ArrayList<String> fiveOfAKind = new ArrayList<String>();
		ArrayList<String> fourOfAKind = new ArrayList<String>();
		ArrayList<String> fullHouse = new ArrayList<String>();
		ArrayList<String> threeOfAKind = new ArrayList<String>();
		ArrayList<String> twoPair = new ArrayList<String>();
		ArrayList<String> onePair = new ArrayList<String>();
		ArrayList<String> highCard = new ArrayList<String>();
		ArrayList<String> orderedGames = new ArrayList<String>();
		long index = 1;
		long total = 0;
		
		try 
		{
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				add_line_to_hand(line, hands);
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
		for (String hand : hands.keySet()) 
		{
			switch(process_game(hand))
			{
				case FIVE_OF_A_KIND: 
					System.out.println("Adding to FIVE_OF_A_KIND");
					fiveOfAKind.add(hand);
					break;
				case FOUR_OF_A_KIND:
					System.out.println("Adding to FOUR_OF_A_KIND");
					fourOfAKind.add(hand);
					break;
				case FULL_HOUSE:
					System.out.println("Adding to FULL_HOUSE");
					fullHouse.add(hand);
					break;
				case THREE_OF_A_KIND:
					System.out.println("Adding to THREE_OF_A_KIND");
					threeOfAKind.add(hand);
					break;
				case TWO_PAIR:
					System.out.println("Adding to TWO_PAIR");
					twoPair.add(hand);
					break;
				case ONE_PAIR:
					System.out.println("Adding to ONE_PAIR");
					onePair.add(hand);
					break;
				case HIGH_CARD:
					System.out.println("Adding to HIGH_CARD");
					highCard.add(hand);
					break;
				default:
					System.out.println("Something went wrong...");
					System.exit(INVALID_GAME);
			}
		}
		
		// Processing games to get order
		get_order(highCard, orderedGames);
		get_order(onePair, orderedGames);
		get_order(twoPair, orderedGames);
		get_order(threeOfAKind, orderedGames);
		get_order(fullHouse, orderedGames);
		get_order(fourOfAKind, orderedGames);
		get_order(fiveOfAKind, orderedGames);
		
		for (String hand : orderedGames)
		{
			System.out.println(hand);
			total += index * hands.get(hand);
			++index;
		}
		
		System.out.println("Total = " + total);
	}
}
