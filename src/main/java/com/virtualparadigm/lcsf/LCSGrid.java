package com.virtualparadigm.lcsf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LCSGrid<T>
{
	private static Map<InheritDirection, Integer> inheritDirectionMap;
	
	static
	{
		inheritDirectionMap = new HashMap<InheritDirection, Integer>();
		inheritDirectionMap.put(InheritDirection.TOP, 124);
		inheritDirectionMap.put(InheritDirection.LEFT, 45);
		inheritDirectionMap.put(InheritDirection.DIAGONAL, 92);
	}
	
	private LCSElement<T>[] xSequence;
	private LCSElement<T>[] ySequence;
	private LCSCell[][] lcsCells;
	
	public LCSGrid(LCSElement<T>[] xSequence, LCSElement<T>[] ySequence)
	{
		this.xSequence = xSequence;
		this.ySequence = ySequence;
		
		if(xSequence != null && ySequence != null)
		{
			this.lcsCells = new LCSCell[xSequence.length][ySequence.length];
			for(int x=0; x<xSequence.length; x++)
			{
				for(int y=0; y<ySequence.length; y++)
				{
					this.lcsCells[x][y] = new LCSCell(0, null);
				}
			}
		}
	}
	
	public LCSGrid(LCSElement<T>[] xSequence, LCSElement<T>[] ySequence, LCSCell[][] lcsCells)
	{
		this.xSequence = xSequence;
		this.ySequence = ySequence;
		
//		if(xSequence != null && ySequence != null && lcsCells.length == xSequence.length && lcsCells[])
//		{
//			this.lcsCells = lcsCells;
//		}
	}
	
	
	public LCSCell getCell(int x, int y)
	{
		LCSCell lcsCell = null;
		if(this.isWthinBounds(x, y))
		{
			lcsCell = this.lcsCells[x][y];
		}
//		try
//		{
//			lcsCell = this.lcsCells[x][y];
//		}
//		catch(ArrayIndexOutOfBoundsException aiobe)
//		{
//		}
		return lcsCell;
	}
	
	public boolean setCell(int x, int y, LCSCell lcsCell)
	{
		boolean status = false;
		if(this.isWthinBounds(x, y))
		{
			this.lcsCells[x][y] = lcsCell;
			status = true;
		}
//		try
//		{
//			this.lcsCells[x][y] = lcsCell;
//			status = true;
//		}
//		catch(ArrayIndexOutOfBoundsException aiobe)
//		{
//		}
		
		return status;
	}
	
	public boolean isWthinBounds(int x, int y)
	{
		return (x >= 0 && x < this.xSequence.length && y >= 0 && y <this.ySequence.length);
	}
		
	
	public static LCSGrid[] split(Coordinate[] coordinates)
	{
		LCSGrid[] lcsGrids = null;
		if(coordinates != null)
		{
			try
			{
				Arrays.sort(coordinates, new CoordinateComparator());
				
				// if overlaps exist in the coordinates, then we wont get here due to 
				// runtime overlap exception thrown by CoordinateComparator
				
				lcsGrids = new LCSGrid[coordinates.length];
				Coordinate prevCoordinate = new Coordinate(0,0);
//				for(Coordinate coordinate : coordinates)
				
				for(int i=0; i<coordinates.length; i++)
				{
//					lcsGrids[i] = new LCSGrid()
					
					
				}
			}
			catch(OverlapException oe)
			{
			}
			
			
			
			
		}
		return lcsGrids;
	}
	
	public static LCSGrid merge(LCSGrid[] lcsGrids)
	{
		LCSGrid lcsGrid = null;
		
		return lcsGrid;
	}

	
	public static void main(String[] args)
	{
//		Coordinate[] coordinates = new Coordinate[]{new Coordinate(0, 0), new Coordinate(2, 2), new Coordinate(1, 1), new Coordinate(3, 1)};
		Coordinate[] coordinates = new Coordinate[]{new Coordinate(1, 3), new Coordinate(0, 0), new Coordinate(1, 1), new Coordinate(3, 1)};
		printCoordinates(coordinates);
		System.out.println("");
		System.out.println("");
		Arrays.sort(coordinates, new CoordinateComparator());
		printCoordinates(coordinates);
	}
	
	public static void printCoordinates(Coordinate[] coordinates)
	{
		if(coordinates != null)
		{
			for(Coordinate coordinate : coordinates)
			{
				System.out.println(coordinate.toString());
			}
		}
	}
	
	public void printGrid(int cellSize)
	{
		StringBuffer row = new StringBuffer();
		String[] rowOutput = new String[xSequence.length + 1];
		
		row.append("%"+cellSize+"s");
		rowOutput[0] = " ";
		for(int x=0; x<xSequence.length; x++)
		{
			row.append("%"+cellSize+"s");
			rowOutput[x+1] = xSequence[x].asString();
		}
		System.out.printf(row.toString() + "\n", (Object[])rowOutput);
		row.setLength(0);
		
		for(int y=0; y<ySequence.length; y++)
		{
			row.append("%"+cellSize+"s");
			rowOutput[0] = ySequence[y].asString();
			for(int x=0; x<xSequence.length; x++)
			{
				row.append("%"+cellSize+"s");
				rowOutput[x+1] = (char)LCSGrid.getASCIIInheritDirection(lcsCells[x][y].getInheritDirection()) + String.valueOf(lcsCells[x][y].getValue());
			}
			System.out.printf(row.toString() + "\n", (Object[])rowOutput);
			row.setLength(0);
		}
	}	
	
	
	public static int getASCIIInheritDirection(InheritDirection inheritDirection)
	{
		Integer ascii = LCSGrid.inheritDirectionMap.get(inheritDirection);
		return (ascii != null) ? ascii : 0;
		
	}
}