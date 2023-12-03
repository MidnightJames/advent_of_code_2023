package day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gear_Ratios_Part_1 {
	// Constants
	static final int INVALID_FILENAME = -1;
	
	public static int process_line(char[] before, char[] current, char[] after)
	{
		boolean isFirstLine = before.length == 0;
		boolean isLastLine = after.length == 0;
		int sizeOfLine = current.length;
		int sumOfLine = 0;
		
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
					if (current[i - 1] != '.' || (!isFirstLine && before[i - 1] != '.') || (!isLastLine && after[i - 1] != '.'))
					{
						isValid = true;
					}
				}
				
				if ((!isFirstLine && before[i] != '.') || (!isLastLine && after[i] != '.'))
				{
//					System.out.println("One " + current[i]);
					isValid = true;
				}
				
				// Loop until end of line or end of number
				while (i + 1 < sizeOfLine)
				{
					++i;
					// Keep going while character is a digit
					if (Character.isDigit(current[i]))
					{
						numberAsString += current[i];
						if ((!isFirstLine && before[i] != '.') || (!isLastLine && after[i] != '.'))
						{
//							System.out.println("Two " + current[i]);
							isValid = true;
						}
					}
					// End of number
					else
					{
						if (current[i] != '.' || (!isFirstLine && before[i] != '.') || (!isLastLine && after[i] != '.'))
						{
//							System.out.println("Three " + current[i]);
							isValid = true;
						}
						break;
					}
				}
				
				// Add the number if it's valid
				if (isValid)
				{
					System.out.println("Adding " + Integer.parseInt(numberAsString));
					sumOfLine += Integer.parseInt(numberAsString);
				}
			}
		}
		
		return sumOfLine;
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
				sum += process_line(new char[0], fileAsCharList.get(i), fileAsCharList.get(i + 1));
			}
			else if (i == fileAsCharList.size() - 1)
			{
				sum += process_line(fileAsCharList.get(i - 1), fileAsCharList.get(i), new char[0]);
			}
			else
			{
				sum += process_line(fileAsCharList.get(i - 1), fileAsCharList.get(i), fileAsCharList.get(i + 1));
			}			
		}
		
		System.out.println("Sum: " + sum);
	}
}
