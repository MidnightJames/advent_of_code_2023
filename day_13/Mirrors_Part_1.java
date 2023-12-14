package _2023_day_13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Mirrors_Part_1
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
		
		Mirrors mirrors = new Mirrors();
		
		long startTime = System.nanoTime();
		
		try 
		{
			ArrayList<char[]> mirror = new ArrayList<char[]>();
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				char[] lineAsCharArray = line.toCharArray();
				
				if (line.isEmpty())
				{
					mirrors.addMirror(new Mirror(mirror));
					mirror.clear();
					continue;
				}
				
				if (!fileScanner.hasNextLine())
				{
					mirror.add(lineAsCharArray);
					mirrors.addMirror(new Mirror(mirror));
					continue;
				}
				
				mirror.add(lineAsCharArray);
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
		mirrors.printMirrors();
		
		int current = 1;
		for (Mirror mirror : mirrors.getMirrors())
		{
			System.out.println("Mirror " + current + " value = " + MirrorFunctions.getTotalValue(mirror));
			++current;
		}
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Time: " + duration + " ms");
	}
}
