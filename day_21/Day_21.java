package _2023.day_21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day_21
{
    static final int INVALID_FILENAME = -1;
    static final int FILE_NOT_FOUND = -2;
    static final int NUMBER_OF_STEPS = 26501365;

    public static String getFilename()
    {
        Scanner keyboardScanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String filename = keyboardScanner.nextLine();

        if (filename.isEmpty() || filename.isBlank())
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
        Set<String> validLocations = new HashSet<>();
        Set<String> nodesVisited = new HashSet<>();
        Set<String> activeNodes = new HashSet<>();
        String startingNode = null;

        int colLength = 0;
        int rowLength = 0;

        long startTime = System.nanoTime();

        try
        {
            Scanner fileScanner = new Scanner(file);
            int row = 0;
            while (fileScanner.hasNextLine())
            {
                char[] lineCharArray = fileScanner.nextLine().toCharArray();

                // Get every valid node and starting position
                for (int column = 0; column < lineCharArray.length; ++column)
                {
                    String node = column + "," + row;

                    if (lineCharArray[column] != '#')
                    {
                        validLocations.add(node);
                    }

                    if (lineCharArray[column] == 'S')
                    {
                        activeNodes.add(node);
                        nodesVisited.add(node);
                        startingNode = node;
                    }
                }

                ++row;
                rowLength = row;
                colLength = lineCharArray.length;
            }
            fileScanner.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found.");
            System.exit(FILE_NOT_FOUND);
        }

        // Part 1
        for (int i = 0; i < NUMBER_OF_STEPS; ++i)
        {
            ArrayList<String> tempArray = new ArrayList<>(activeNodes);
            activeNodes.clear();

            for (int index = (tempArray.size() - 1); index >= 0; --index)
            {
                String[] current = tempArray.get(index).split(",");
                int currentCol = Integer.parseInt(current[0]);
                int currentRow = Integer.parseInt(current[1]);

                String left = (currentCol - 1) + "," + currentRow;
                String right = (currentCol + 1) + "," + currentRow;
                String up = currentCol + "," + (currentRow - 1);
                String down = currentCol + "," + (currentRow + 1);

                // Checking left
                if (validLocations.contains(left))
                {
                    activeNodes.add((currentCol - 1) + "," + currentRow);
                }

                // Checking right
                if (validLocations.contains(right))
                {
                    activeNodes.add((currentCol + 1) + "," + currentRow);
                }

                // Checking up
                if (validLocations.contains(up))
                {
                    activeNodes.add(currentCol + "," + (currentRow - 1));
                }

                // Checking down
                if (validLocations.contains(down))
                {
                    activeNodes.add(currentCol + "," + (currentRow + 1));
                }
            }
        }

        System.out.println("Part 1: " + activeNodes.size());

        activeNodes.clear();
        activeNodes.add(startingNode);

        // Part 2
        for (int i = 0; i < NUMBER_OF_STEPS; ++i)
        {
            ArrayList<String> tempArray = new ArrayList<>(activeNodes);
            activeNodes.clear();

            for (int index = (tempArray.size() - 1); index >= 0; --index)
            {
                String[] current = tempArray.get(index).split(",");
                int currentCol = Integer.parseInt(current[0]);
                int currentRow = Integer.parseInt(current[1]);
                int currentColMod = currentCol % colLength;
                int currentRowMod = currentRow % rowLength;

                String left = ((currentColMod - 1) % colLength) + "," + currentRowMod;
                String right = ((currentColMod + 1) % colLength) + "," + currentRowMod;
                String up = currentColMod + "," + ((currentRowMod - 1) % rowLength);
                String down = currentColMod + "," + ((currentRowMod + 1) % rowLength);

                // Checking left
                if (validLocations.contains(left))
                {
                    activeNodes.add((currentCol - 1) + "," + currentRow);
                }

                // Checking right
                if (validLocations.contains(right))
                {
                    activeNodes.add((currentCol + 1) + "," + currentRow);
                }

                // Checking up
                if (validLocations.contains(up))
                {
                    activeNodes.add(currentCol + "," + (currentRow - 1));
                }

                // Checking down
                if (validLocations.contains(down))
                {
                    activeNodes.add(currentCol + "," + (currentRow + 1));
                }
            }
        }

        System.out.println("Part 2: " + activeNodes.size());

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time: " + duration + " ms");
    }
}
