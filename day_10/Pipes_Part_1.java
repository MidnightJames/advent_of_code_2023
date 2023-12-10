package _2023_day_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Pipes_Part_1 {
	static final int INVALID_FILENAME = -1;
	static final int FILE_NOT_FOUND = -2;
	
	public static String get_filename()
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
	
	public static void process_nodes(String line, int row, Nodes nodes)
	{
		char[] lineCharArray = line.toCharArray();
		
		for (int column = 0; column < lineCharArray.length; ++column)
		{
			// '.' indicates no pipe
			if (lineCharArray[column] == '.')
			{
				continue;
			}
			
			// Otherwise create a node at the location
			nodes.addNode(new Node(column, row, lineCharArray[column]));
//			System.out.println(nodes.getNode(column, row));
		}
	}
	
	public static void process_edges(Node node, Nodes nodes, Edges edges)
	{
		char identifier = node.getIdentifier();
		
		// Don't do anything if S, we will find the edges from the other nodes
		if (identifier == 'S')
		{
			return;
		}
		
		Node nodeNorth = nodes.getNode(node.getColumn(), node.getRow() - 1);
		Node nodeSouth = nodes.getNode(node.getColumn(), node.getRow() + 1);
		Node nodeEast = nodes.getNode(node.getColumn() + 1, node.getRow());
		Node nodeWest = nodes.getNode(node.getColumn() - 1, node.getRow());
		
		// | connects north and south
		if (identifier == '|')
		{
			if (nodeSouth == null || nodeNorth == null)
			{
				return;
			}
			
			if (nodeSouth.getIdentifier() == 'L' || nodeSouth.getIdentifier() == 'J' || nodeSouth.getIdentifier() == '|' || nodeSouth.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn(), node.getRow() + 1)));
			}
			
			if (nodeNorth.getIdentifier() == '7' || nodeNorth.getIdentifier() == 'F' || nodeNorth.getIdentifier() == '|' || nodeNorth.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn(), node.getRow() - 1)));
			}
			
			return;
		}
		
		// - connects east and west
		if (identifier == '-')
		{
			if (nodeEast == null || nodeWest == null)
			{
				return;
			}
			
			if (nodeEast.getIdentifier() == '-' || nodeEast.getIdentifier() == 'J' || nodeEast.getIdentifier() == '7' || nodeEast.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn() + 1, node.getRow())));
			}
			
			if (nodeWest.getIdentifier() == '-' || nodeWest.getIdentifier() == 'L' || nodeWest.getIdentifier() == 'F' || nodeWest.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn() - 1, node.getRow())));
			}
			
			return;
		}
		
		// L connects north and east
		if (identifier == 'L')
		{
			if (nodeNorth == null || nodeEast == null)
			{
				return;
			}
			
			if (nodeNorth.getIdentifier() == '7' || nodeNorth.getIdentifier() == 'F' || nodeNorth.getIdentifier() == '|' || nodeNorth.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn(), node.getRow() - 1)));
			}
			
			if (nodeEast.getIdentifier() == '-' || nodeEast.getIdentifier() == 'J' || nodeEast.getIdentifier() == '7' || nodeEast.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn() + 1, node.getRow())));
			}
			
			return;
		}
		
		// J connects north and west
		if (identifier == 'J')
		{
			if (nodeNorth == null || nodeWest == null)
			{
				return;
			}
			
			if (nodeNorth.getIdentifier() == '7' || nodeNorth.getIdentifier() == 'F' || nodeNorth.getIdentifier() == '|' || nodeNorth.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn(), node.getRow() - 1)));
			}
			
			if (nodeWest.getIdentifier() == '-' || nodeWest.getIdentifier() == 'L' || nodeWest.getIdentifier() == 'F' || nodeWest.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn() - 1, node.getRow())));
			}
			
			return;
		}
		
		// 7 connects south and west
		if (identifier == '7')
		{
			if (nodeSouth == null || nodeWest == null)
			{
				return;
			}
			
			if (nodeWest.getIdentifier() == '-' || nodeWest.getIdentifier() == 'L' || nodeWest.getIdentifier() == 'F' || nodeWest.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn() - 1, node.getRow())));
			}
			
			if (nodeSouth.getIdentifier() == 'L' || nodeSouth.getIdentifier() == 'J' || nodeSouth.getIdentifier() == '|' || nodeSouth.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn(), node.getRow() + 1)));
			}
			
			return;
		}
		
		// F connects south and east
		if (identifier == 'F')
		{
			if (nodeSouth == null || nodeEast == null)
			{
				return;
			}
			
			if (nodeSouth.getIdentifier() == 'L' || nodeSouth.getIdentifier() == 'J' || nodeSouth.getIdentifier() == '|' || nodeSouth.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn(), node.getRow() + 1)));
			}
			
			if (nodeEast.getIdentifier() == '-' || nodeEast.getIdentifier() == 'J' || nodeEast.getIdentifier() == '7' || nodeEast.getIdentifier() == 'S')
			{
				edges.addEdge(new Edge(nodes.getNode(node.getColumn(), node.getRow()), nodes.getNode(node.getColumn() + 1, node.getRow())));
			}
			
			return;
		}
	}
	
	public static int breadth_first_search(Nodes nodes, Edges edges)
	{
		HashMap<Node, Integer> distances = new HashMap<Node, Integer>();
		ArrayList<Node> queue = new ArrayList<Node>();
		
		for (Node node : nodes.getNodes())
		{
			if (node.getIdentifier() == 'S')
			{
				queue.add(node);
				distances.put(node,  0);
			}
			else
			{
				distances.put(node, -1);
			}
		}
		
		while (queue.size() > 0)
		{
			Node currentNode = queue.getFirst();
			queue.remove(0);
			for (Edge edge : edges.getEdges())
			{
				if (edge.containsNode(currentNode))
				{
					Node pairedNode = edge.getPairedNode(currentNode);
					if (distances.get(pairedNode) == -1)
					{
//						System.out.println("Adding " + pairedNode);
						queue.add(pairedNode);
						distances.put(pairedNode, distances.get(currentNode) + 1);
					}
				}
			}
		}
		
		int max = -1;
		for (Node node : distances.keySet())
		{
			System.out.println(node + " " + distances.get(node));
			if (distances.get(node) > max)
			{
				max = distances.get(node);
			}
		}
		
		return max;
	}
	
	public static void main(String[] args)
	{
		String filename = get_filename();
//		String filename = "input.txt";
		File file = new File(filename);
		String line = null;
		ArrayList<String> fileArray = new ArrayList<String>();
		Nodes nodes = new Nodes();
		Edges edges = new Edges();
		
		long startTime = System.nanoTime();
		
		try 
		{
			int row = 0;
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				fileArray.add(line);
				process_nodes(line, row, nodes);
				
				++row;
			}
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
//		System.out.println("Edging");
		for (Node node : nodes.getNodes())
		{
			process_edges(node, nodes, edges);
		}
		
//		System.out.println("EDGESSSSSS");
//		for (Edge edge : edges.getEdges())
//		{
//			System.out.println(edge);
//		}
		
		int max = breadth_first_search(nodes, edges);
		System.out.println("Max = " + max);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Time: " + duration + " ms");
	}
}
