package day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trebuchet_Part_1 {
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
	
	public static int get_left_number(String line)
	{
		for (int i = 0; i < line.length(); ++i)
		{
			if (Character.isDigit(line.charAt(i)))
			{
				return Character.getNumericValue(line.charAt(i));
			}
		}
		
		return -1;
	}
	
	public static int get_right_number(String line)
	{
		for (int i = line.length() - 1; i > -1; --i)
		{
			if (Character.isDigit(line.charAt(i)))
			{
				return Character.getNumericValue(line.charAt(i));
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
