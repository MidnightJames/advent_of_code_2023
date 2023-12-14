package _2023_day_14;

import java.util.ArrayList;

public class Dish
{
	public char[][] dish;
	public char[][] lastDish;
	
	public Dish(ArrayList<char[]> dish)
	{
		this.dish = new char[dish.size()][dish.get(0).length];
		this.lastDish = new char[dish.size()][dish.get(0).length];
		
		for (int i = 0; i < this.dish.length; ++i)
		{
			for (int j = 0; j < this.dish[0].length; ++j)
			{
				this.dish[i][j] = dish.get(i)[j];
			}
		}
	}
	
	public void printDish()
	{
		for (int i = 0; i < dish.length; ++i)
		{
			for (int j = 0; j < dish[0].length; ++j)
			{
				System.out.print(dish[i][j]);
			}
			System.out.println();
		}
	}
	
	public void cycleDish()
	{
		copyCurrent();
		tipNorth();
		tipWest();
		tipSouth();
		tipEast();
	}
	
	private void copyCurrent()
	{
		for (int i = 0; i < this.dish.length; ++i)
		{
			for (int j = 0; j < this.dish[0].length; ++j)
			{
				lastDish[i][j] = dish[i][j];
			}
		}
	}
	
	public boolean dishSameAsLast()
	{
		for (int i = 0; i < this.dish.length; ++i)
		{
			for (int j = 0; j < this.dish[0].length; ++j)
			{
				if (lastDish[i][j] != dish[i][j])
				{
					System.out.println("false");
					return false;
				}
			}
		}	
		
		return true;
	}
	
	public void tipNorth()
	{
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		for (int i = 1; i < dish.length; ++i)
		{
			for (int j = 0; j < dish[i].length; ++j)
			{
				// Check for O's on this line and grab indexes
				if (dish[i][j] == 'O')
				{
					indexes.add(j);
				}
			}
			
			if (indexes.size() > 0)
			{
				shiftNorth(indexes, i);
				indexes.clear();
			}
		}
	}
	
	private void shiftNorth(ArrayList<Integer> indexes, int i)
	{
		while (i > 0)
		{
			for (int j = (indexes.size() - 1); j >= 0; --j)
			{
				if (dish[i - 1][indexes.get(j)] == '.')
				{
					dish[i][indexes.get(j)] = '.';
					dish[i - 1][indexes.get(j)] = 'O';
				}
				else
				{
					indexes.remove(j);
				}
			}
			--i;
		}
	}
	
	public void tipSouth()
	{
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		for (int i = (dish.length - 2); i >= 0; --i)
		{
			for (int j = 0; j < dish[i].length; ++j)
			{
				// Check for O's on this line and grab indexes
				if (dish[i][j] == 'O')
				{
					indexes.add(j);
				}
			}
			
			if (indexes.size() > 0)
			{
				shiftSouth(indexes, i);
				indexes.clear();
			}
		}
	}
	
	private void shiftSouth(ArrayList<Integer> indexes, int i)
	{
		while (i < dish.length - 1)
		{
			for (int j = (indexes.size() - 1); j >= 0; --j)
			{
				if (dish[i + 1][indexes.get(j)] == '.')
				{
					dish[i][indexes.get(j)] = '.';
					dish[i + 1][indexes.get(j)] = 'O';
				}
				else
				{
					indexes.remove(j);
				}
			}
			++i;
		}
	}
	
	public void tipWest()
	{
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		for (int j = 1; j < dish[0].length; ++j)
		{
			for (int i = 0; i < dish.length; ++i)
			{
				// Check for O's on this line and grab indexes
				if (dish[i][j] == 'O')
				{
					indexes.add(i);
				}
			}
			
			if (indexes.size() > 0)
			{
				shiftWest(indexes, j);
				indexes.clear();
			}
		}
	}

	private void shiftWest(ArrayList<Integer> indexes, int j)
	{
		while (j > 0)
		{
			for (int i = (indexes.size() - 1); i >= 0; --i)
			{
				if (dish[indexes.get(i)][j - 1] == '.')
				{
					dish[indexes.get(i)][j] = '.';
					dish[indexes.get(i)][j - 1] = 'O';
				}
				else
				{
					indexes.remove(i);
				}
			}
			--j;
		}
	}
	
	public void tipEast()
	{
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		for (int j = (dish[0].length - 2); j >= 0; --j)
		{
			for (int i = 0; i < dish.length; ++i)
			{
				// Check for O's on this line and grab indexes
				if (dish[i][j] == 'O')
				{
					indexes.add(i);
				}
			}
			
			if (indexes.size() > 0)
			{
				shiftEast(indexes, j);
				indexes.clear();
			}
		}
	}

	private void shiftEast(ArrayList<Integer> indexes, int j)
	{
		while (j < dish[0].length - 1)
		{
			for (int i = (indexes.size() - 1); i >= 0; --i)
			{
				if (dish[indexes.get(i)][j + 1] == '.')
				{
					dish[indexes.get(i)][j] = '.';
					dish[indexes.get(i)][j + 1] = 'O';
				}
				else
				{
					indexes.remove(i);
				}
			}
			++j;
		}
	}
	
	public int getLoad()
	{
		int total = 0;
		int currentMultiplier = 1;
		int currentLineCount = 0;
		
		for (int i = (dish.length - 1); i >= 0; --i)
		{
			for (int j = 0; j < dish[i].length; ++j)
			{
				if (dish[i][j] == 'O')
				{
					++currentLineCount;
				}
			}
			total += currentMultiplier * currentLineCount;
			++currentMultiplier;
			currentLineCount = 0;
		}
		
		return total;
	}
}
