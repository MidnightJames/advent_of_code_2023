package _2023_day_10;

public class Node
{
	private int column;
	private int row;
	private char identifier;
	
	public Node(int column, int row, char identifier)
	{
		this.column = column;
		this.row = row;
		this.identifier = identifier;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public char getIdentifier()
	{
		return identifier;
	}
	
	@Override
	public boolean equals(Object toCompare)
	{
		if (toCompare == this)
		{
			return true;
		}
		
		if (!(toCompare instanceof Node))
		{
			return false;
		}
		
		Node toCompareNode = (Node) toCompare;
		
		return this.column == toCompareNode.column &&
				this.row == toCompareNode.row &&
				this.identifier == toCompareNode.identifier;
	}
	
	@Override
	public String toString()
	{
		return "[" + this.column + ", " + this.row + "]";
	}
}
