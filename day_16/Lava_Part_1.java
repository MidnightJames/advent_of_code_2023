import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Lava_Part_1
{
    static final int INVALID_FILENAME = -1;
	static final int FILE_NOT_FOUND = -2;

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
	
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
		File file = new File(filename);
        ArrayList<String> puzzleArrayList = new ArrayList<String>();

        // Starting timer
		long startTime = System.nanoTime();
		
		try 
		{
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine())
			{
                puzzleArrayList.add(fileScanner.nextLine());
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}

        // Creating puzzle as a 2D array
        char[][] puzzle = new char[puzzleArrayList.size()][];

        int index = 0;
        for (String line : puzzleArrayList)
        {
            puzzle[index] = line.toCharArray();
            ++index;
        }

        // Test print
        for (int i = 0; i < puzzle.length; ++i)
        {
            for (int j = 0; j < puzzle[0].length; ++j)
            {
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }

        // Start at upper left, travelling right, and traverse until out of bounds
        ArrayList<int[]> currentPositions = new ArrayList<int[]>();
		// HashMap<String, Set positionsEnergized = new HashSet<String>();
        currentPositions.add(new int[] {0, 0, RIGHT});
		// positionsEnergized.add(currentPositions.get(0)[0] + " " + currentPositions.get(0)[1] + " " + );
        int maxY = puzzle.length - 1;
        int maxX = puzzle[0].length - 1;

		while (currentPositions.size() != 0)
		{

		}

        // Ending timer
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Time: " + duration + " ms");
	}
}
