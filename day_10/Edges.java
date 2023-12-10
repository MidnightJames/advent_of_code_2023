package _2023_day_10;

import java.util.ArrayList;

public class Edges
{
	ArrayList<Edge> edges;
	
	public Edges()
	{
		this.edges = new ArrayList<Edge>();
	}
	
	public void addEdge(Edge edge)
	{
		if (doesEdgeExist(edge))
		{
//			System.out.println("Edge from " + edge.node1 + " to " + edge.node2 + " already exists");
			return;
		}
		
//		System.out.println("Adding: " + edge);
		edges.add(edge);
	}
	
	public boolean doesEdgeExist(Edge edge)
	{
		for (Edge testEdge : edges)
		{
			if (testEdge.equals(edge))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Edge> getEdges()
	{
		return edges;
	}
}
