package _2023_day_15;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.StringBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Hash_Part_2
{
	static final int INVALID_FILENAME = -1;
	static final int FILE_NOT_FOUND = -2;
	static final int MULTIPLIER = 17;
	static final int MOD = 256;
	
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
	
	public static void getHash(String s, HashMap<Integer, ArrayList<String[]>> boxes)
	{	
		if (s.contains("="))
		{
			String[] sequence = s.split("=");
			int value = Integer.parseInt(sequence[1]);
			int currentBox = 0;
			
			for (char character : sequence[0].toCharArray())
			{
				currentBox += (int) character;
				currentBox *= MULTIPLIER;
				currentBox %= MOD;
			}
			
			if (!boxes.containsKey(currentBox))
			{
				boxes.put(currentBox, new ArrayList<String[]>());
			}
			
			for (int i = 0; i < boxes.get(currentBox).size(); ++i)
			{
				 if (boxes.get(currentBox).get(i)[0].equals(sequence[0]))
				 {
					 boxes.get(currentBox).get(i)[1] = sequence[1];
					 return;
				 }
			}
			
			boxes.get(currentBox).add(sequence);
		}
		else
		{
			String sequence = s.split("-")[0];
			
			for (int key : boxes.keySet())
			{
				for (int i = (boxes.get(key).size() - 1); i >= 0; --i)
				{
					if (boxes.get(key).get(i)[0].equals(sequence))
					{
						boxes.get(key).remove(i);
					}
				}
			}
		}
	}
	
	public static void printHashTable(HashMap<Integer, ArrayList<String[]>> boxes)
	{
		for (int key : boxes.keySet())
		{
			System.out.print("Box " + key + " ");
			for (String[] string : boxes.get(key))
			{
				System.out.print("[" + string[0] + " " + string[1] + "]");
			}
			System.out.println();
		}
	}
	
	public static int getFocusPower(HashMap<Integer, ArrayList<String[]>> boxes)
	{
		int focusPower = 0;
		
		for (int key : boxes.keySet())
		{
			for (int i = 0; i < boxes.get(key).size(); ++i)
			{
				focusPower += (key + 1) * (i + 1) * Integer.parseInt(boxes.get(key).get(i)[1]);
			}
		}
		
		return focusPower;
	}
	
	public static void main(String[] args)
	{
		String filename = getFilename();
		File file = new File(filename);
		String line = null;
		String[] sequences = null;
		HashMap<Integer, ArrayList<String[]>> boxes = new HashMap<Integer, ArrayList<String[]>>();
		
		long startTime = System.nanoTime();
		
		try 
		{	
			StringBuffer stringBuffer = new StringBuffer();
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				stringBuffer.append(line);
			}
			sequences = stringBuffer.toString().split(",");
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
		for (String sequence : sequences)
		{
			getHash(sequence, boxes);
//			System.out.println("After " + sequence);
//			printHashTable(boxes);
		}
		
		int sumFocusPower = getFocusPower(boxes);
		
		System.out.println("Focus Power = " + sumFocusPower);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Time: " + duration + " ms");
	}
}
