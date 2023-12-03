package day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Gear_Ratios_Part_2 {
	// Constants
	static final int INVALID_FILENAME = -1;
	
	public static void process_line(char[] before, char[] current, char[] after, HashMap<String, Integer> numberMap, int lineIndex)
	{
		boolean isFirstLine = before.length == 0;
		boolean isLastLine = after.length == 0;
		int sizeOfLine = current.length;
		String coordinates = "";
		
		// Go through each char in current line and check if numbers are valid (aren't surrounded by .)
		for (int i = 0; i < sizeOfLine; ++i)
		{
			// When the character is a digit, get whole number and check if valid
			if (Character.isDigit(current[i]))
			{
				boolean isValid = false;
				String numberAsString = "" + current[i];
				
				// If we aren't on the left, check left
				if (i != 0)
				{
					if (current[i - 1] == '*')
					{
						isValid = true;
						coordinates = (i - 1) + " " + lineIndex;
					}
					if (!isFirstLine && before[i - 1] == '*')
					{
						isValid = true;
						coordinates = (i - 1) + " " + (lineIndex - 1);
					}
					if (!isLastLine && after[i - 1] == '*')
					{
						isValid = true;
						coordinates = (i - 1) + " " + (lineIndex + 1);
					}
				}
				
				if (!isFirstLine && before[i] == '*')
				{
					isValid = true;
					coordinates = i + " " + (lineIndex - 1);
				}
				if (!isLastLine && after[i] == '*')
				{
					isValid = true;
					coordinates = i + " " + (lineIndex + 1);
				}
				
				// Loop until end of line or end of number
				while (i + 1 < sizeOfLine)
				{
					++i;
					// Keep going while character is a digit
					if (Character.isDigit(current[i]))
					{
						numberAsString += current[i];
						if (!isFirstLine && before[i] == '*')
						{
							isValid = true;
							coordinates = i + " " + (lineIndex - 1);
						}
						if (!isLastLine && after[i] == '*')
						{
							isValid = true;
							coordinates = i + " " + (lineIndex + 1);
						}
						
					}
					// End of number
					else
					{
						if (current[i] == '*')
						{
							isValid = true;
							coordinates = i + " " + lineIndex;
						}
						if (!isFirstLine && before[i] == '*')
						{
							isValid = true;
							coordinates = i + " " + (lineIndex - 1);
						}
						if (!isLastLine && after[i] == '*')
						{
							isValid = true;
							coordinates = i + " " + (lineIndex + 1);
						}
						
						break;
					}
				}
				// Add the number if it's valid
				if (isValid)
				{
					if (numberMap.containsKey(coordinates))
					{
						System.out.println(coordinates + " = " + numberMap.get(coordinates) + " * " + numberAsString);
						numberMap.put(coordinates,  (-1 * numberMap.get(coordinates) * Integer.parseInt(numberAsString)));
					}
					else
					{
						System.out.println(coordinates + " = " + numberAsString);
						numberMap.put(coordinates, (-1 * Integer.parseInt(numberAsString)));
					}
				}
			}
		}
		
		return;
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
		ArrayList<char[]> fileAsCharList = new ArrayList<char[]>();
		File file = new File(filename);
		HashMap<String, Integer> numberMap = new HashMap<String, Integer>();
		int sum = 0;
		
		try 
		{
			Scanner fileScanner = new Scanner(file);
			// Get all the lines as char arrays to easily index
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				fileAsCharList.add(line.toCharArray());
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.print("File not found.");
		}
		
		
		for (int i = 0; i < fileAsCharList.size(); ++i)
		{
			// Edge cases for first and last line
			if (i == 0)
			{
				process_line(new char[0], fileAsCharList.get(i), fileAsCharList.get(i + 1), numberMap, i);
			}
			else if (i == fileAsCharList.size() - 1)
			{
				process_line(fileAsCharList.get(i - 1), fileAsCharList.get(i), new char[0], numberMap, i);
			}
			else
			{
				process_line(fileAsCharList.get(i - 1), fileAsCharList.get(i), fileAsCharList.get(i + 1), numberMap, i);
			}			
		}
		
		for (String key : numberMap.keySet())
		{
			if (numberMap.get(key) > 0)
			{
				sum += numberMap.get(key);
			}
		}
		
		System.out.println("Sum: " + sum);
	}
}
