package _2023_day_14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dish_Part_1
{
	static final int INVALID_FILENAME = -1;
	static final int FILE_NOT_FOUND = -2;
	
	public static String getFilename()
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
	
	public static void main(String[] args)
	{
		String filename = getFilename();
//		String filename = "input.txt";
		File file = new File(filename);
		String line = null;
		Dish dish = null;
		
		long startTime = System.nanoTime();
		
		try 
		{
			ArrayList<char[]> dishArrayList = new ArrayList<char[]>();
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				char[] lineAsCharArray = line.toCharArray();
				dishArrayList.add(lineAsCharArray);
			}
			dish = new Dish(dishArrayList);
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
		System.out.println("Initial");
		dish.printDish();
		System.out.println();
		
		dish.tipNorth();
		
		int northLoad = dish.getLoad();
		System.out.println("North Load = " + northLoad);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Time: " + duration + " ms");
	}
}
