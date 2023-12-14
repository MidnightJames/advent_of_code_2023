package _2023_day_13;

import java.util.Arrays;

public class MirrorFunctions
{
	public static int getTotalValue(Mirror m)
	{
		int value = 0;
		char[][] mirror = m.getMirror();
		char[][] mirrorTranspose = m.getMirrorTranspose();
		
		System.out.println("Mirror");
		value += getMirrorValue(mirror);
		System.out.println("Transpose");
		value += getMirrorValue(mirrorTranspose) * 100;
		
		return value;
	}
	
	public static int getMirrorValue(char[][] mirror)
	{
		int value = 0;
		
		for (int i = 0; i < mirror.length; ++i)
		{
			boolean validNext = (i + 1) < mirror.length;
			
			if (validNext && i != 0 && Arrays.equals(mirror[i], mirror[i + 1]))
			{
				value += getMatchValue(mirror, i);
			}
		}
		
		return value;
	}
	
	public static int getMatchValue(char[][] mirror, int index)
	{
		int leftIndex = index - 1;
		int rightIndex = index + 2;
		
		while (leftIndex >= 0)
		{
			if (rightIndex == mirror.length)
			{
				System.out.println("Returning " + index);
				return index;
			}
			
			if (!Arrays.equals(mirror[leftIndex], mirror[rightIndex]))
			{
				System.out.println("Returning 0");
				return 0;
			}
			
			--leftIndex;
			++rightIndex;
		}
		
		// If we reach this, we screwed something up
		System.out.println("Returning -1");
		return -1;
	}
}
