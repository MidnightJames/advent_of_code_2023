package _2023_day_13;

import java.util.ArrayList;

public class Mirrors
{
	private ArrayList<Mirror> mirrors;
	
	public Mirrors()
	{
		mirrors = new ArrayList<Mirror>();
	}
	
	public void addMirror(Mirror mirror)
	{
		mirrors.add(mirror);
	}
	
	public ArrayList<Mirror> getMirrors()
	{
		return mirrors;
	}
	
	public void printMirrors()
	{
		int currentMirror = 1;
		
		for (Mirror mirror : mirrors)
		{
			System.out.println("Mirror " + currentMirror);
			System.out.println(mirror);
			++currentMirror;
		}
	}
}
