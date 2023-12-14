package _2023_day_13;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class Mirror
{
	private char[][] mirror;
	private char[][] mirrorTranspose;
	
	public Mirror(ArrayList<char[]> mirror)
	{
		this.mirror = new char[mirror.size()][];
		this.mirrorTranspose = new char[mirror.get(0).length][];
		initializeMirror(mirror);
		createTranspose();
	}
	
	public int getNumberLinesMirror()
	{
		return mirror.length;
	}
	
	public int getLineLengthMirror()
	{
		return mirror[0].length;
	}
	
	public int getNumberLinesMirrorTranspose()
	{
		return mirrorTranspose.length;
	}
	
	public int getLineLengthMirrorTranspose()
	{
		return mirrorTranspose[0].length;
	}
	
	public char[][] getMirror()
	{
		return mirror;
	}
	
	public char[][] getMirrorTranspose()
	{
		return mirrorTranspose;
	}
	
	private void initializeMirror(ArrayList<char[]> mirror)
	{
		for (int i = 0; i < mirror.size(); ++i)
		{
			this.mirror[i] = mirror.get(i);
		}
	}
	
	private void createTranspose()
	{
		for (int i = 0; i < mirror[0].length; ++i)
		{
			mirrorTranspose[i] = new char[mirror.length];
		}
		
		for (int i = 0; i < mirror[0].length; ++i)
		{
			for (int j = 0; j < mirror.length; ++j)
			{
				mirrorTranspose[i][j] = mirror[j][i];
			}
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		for (char[] line : mirror)
		{
			stringBuilder.append(line).append("\n");
		}
		stringBuilder.append("\nTranspose\n");
		for (char[] line : mirrorTranspose)
		{
			stringBuilder.append(line).append("\n");
		}
		
		return stringBuilder.toString();
	}
}
