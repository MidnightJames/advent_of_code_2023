package _2023_day_10;

public class Edge
{
	Node node1;
	Node node2;
	
	public Edge(Node node1, Node node2)
	{
		this.node1 = node1;
		this.node2 = node2;
	}
	
	public Node getFirstNode()
	{
		return node1;
	}
	
	public Node getSecondNode()
	{
		return node2;
	}
	
	public boolean containsNode(Node node)
	{
		if (node1.equals(node) || node2.equals(node))
		{
			return true;
		}
		
		return false;
	}
	
	public Node getPairedNode(Node node)
	{
		if (!containsNode(node))
		{
			System.out.println("Node does not exist in edge, returning null.");
			return null;
		}
		
		if (node1.equals(node))
		{
			return node2;
		}
		
		return node1;
	}
	
	@Override
	public boolean equals(Object toCompare)
	{
		if (toCompare == this)
		{
			return true;
		}
		
		if (!(toCompare instanceof Edge))
		{
			return false;
		}
		
		Edge toCompareEdge = (Edge) toCompare;
		
		return (node1.equals(toCompareEdge.node1) && node2.equals(toCompareEdge.node2)) ||
				(node1.equals(toCompareEdge.node2) && node2.equals(toCompareEdge.node1));
	}
	
	@Override
	public String toString()
	{
		return "Edge from " + node1 + " to " + node2;
	}
}
