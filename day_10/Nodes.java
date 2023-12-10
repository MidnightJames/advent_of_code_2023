package _2023_day_10;

import java.util.ArrayList;

public class Nodes
{
	private ArrayList<Node> nodes;
	
	public Nodes()
	{
		nodes = new ArrayList<Node>();
	}
	
	public void addNode(Node node)
	{
		if (doesNodeExist(node))
		{
//			System.out.print("Node at " + node.getColumn() + ", " + node.getRow() + " already exists");
			return;
		}
		
		nodes.add(node);
	}
	
	public Node getNode(int column, int row)
	{
		for (Node node : nodes)
		{
			if (column == node.getColumn() && row == node.getRow())
			{
				return node;
			}
		}
		
//		System.out.println("No node found at [" + column + ", " + row + "], returning null.");
		return null;
	}
	
	public ArrayList<Node> getNodes()
	{
		return nodes;
	}
	
	public int getSize()
	{
		return nodes.size();
	}
	
	public boolean doesNodeExist(Node node)
	{
		for (Node testNode : nodes)
		{
			if (testNode.equals(node))
			{
				return true;
			}
		}
		
		return false;
	}
}
