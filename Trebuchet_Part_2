package day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trebuchet_Part_2 {
	static final int INVALID_FILENAME = -1;
	static final int FILE_NOT_FOUND = -2;
	
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
	
	public static int string_as_number(String line)
	{		
		if (line.contains("one"))
		{
			return 1;
		}
		if (line.contains("two"))
		{
			return 2;
		}
		if (line.contains("three"))
		{
			return 3;
		}
		if (line.contains("four"))
		{
			return 4;
		}
		if (line.contains("five"))
		{
			return 5;
		}
		if (line.contains("six"))
		{
			return 6;
		}
		if (line.contains("seven"))
		{
			return 7;
		}
		if (line.contains("eight"))
		{
			return 8;
		}
		if (line.contains("nine"))
		{
			return 9;
		}
		return 0;
	}
	
	public static int get_left_number(String line)
	{
		String word = "";
		
		for (int i = 0; i < line.length(); ++i)
		{
			if (Character.isDigit(line.charAt(i)))
			{
				System.out.println("Returning number: " + line.charAt(i));
				return Character.getNumericValue(line.charAt(i));
			}
			else
			{
				word += line.charAt(i);
				System.out.println("word: " + word);
				if (string_as_number(word) != 0)
				{
					System.out.println("Returning text: " + word);
					return string_as_number(word);
				}
			}
		}
		
		return -1;
	}
	
	public static int get_right_number(String line)
	{
		String word = "";
		
		for (int i = line.length() - 1; i > -1; --i)
		{
			if (Character.isDigit(line.charAt(i)))
			{
				return Character.getNumericValue(line.charAt(i));
			}
			else
			{
				String temp = String.valueOf(line.charAt(i));
				word = temp + word;
				if (string_as_number(word) != 0)
				{
					return string_as_number(word);
				}
			}
		}
		
		return -1;
	}
	
	public static int process_line(String line)
	{
		int numberToReturn = 0;
		
		int leftNumber = get_left_number(line);
		int rightNumber = get_right_number(line);
		
		numberToReturn = leftNumber * 10 + rightNumber;
		
		return numberToReturn;
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
				sum += process_line(line);
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
		System.out.println("Sum: " + sum);
	}

}
