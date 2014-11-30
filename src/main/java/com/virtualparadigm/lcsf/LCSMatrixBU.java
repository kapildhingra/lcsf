package com.virtualparadigm.lcsf;

import java.util.Arrays;

public class LCSMatrixBU
{
	private LCSCell[][] lcsCells;
	
	
	
	
	
	public LCSMatrixBU(int xsize, int ysize, LCSCell defaultCell)
	{
		this.lcsCells = new LCSCell[xsize][ysize];
		if(defaultCell != null)
		{
			for(int x=0; x<xsize; x++)
			{
				for(int y=0; y<ysize; y++)
				{
					try
					{
						lcsCells[x][y] = (LCSCell)defaultCell.clone();
					}
					catch(CloneNotSupportedException cnse)
					{
					}
				}
			}
		}
	}
	
	public LCSMatrixBU(LCSCell[][] lcsCells)
	{
		this.lcsCells = lcsCells;
	}
	
	public LCSMatrixBU(int xsize, int ysize)
	{
		this(xsize, ysize, null);
	}
	
	public LCSCell getCell(int x, int y)
	{
		LCSCell lcsCell = null;
		if(this.lcsCells != null && this.isWthinBounds(x, y))
		{
			lcsCell = lcsCells[x][y];
		}
		return lcsCell;
	}
	
	public void setCell(int x, int y, LCSCell lcsCell)
	{
		if(this.lcsCells != null && this.isWthinBounds(x, y))
		{
			lcsCells[x][y] = lcsCell;
		}
	}
	
	public LCSCell[] getColumn(int x)
	{
		LCSCell[] lcsColumn = null;
		if(this.lcsCells != null && this.isWthinXBounds(x))
		{
			lcsColumn = lcsCells[x];
		}
		return lcsColumn;
	}
	public void setColumn(int x, LCSCell lcsCell)
	{
		if(this.lcsCells != null && this.isWthinXBounds(x))
		{
			for(int i=0; i<this.lcsCells[x].length; i++)
			{
				lcsCells[x][i] = lcsCell;
			}
		}
	}

	public LCSCell[] getRow(int y)
	{
		LCSCell[] lcsRow = null;
		if(this.lcsCells != null && this.isWthinYBounds(y))
		{
			lcsRow = new LCSCell[y];
			for(int i=0; i<lcsCells[0].length; i++)
			{
				lcsRow[i] = this.lcsCells[i][y];
			}
		}
		return lcsRow;
	}
	public void setRow(int y, LCSCell lcsCell)
	{
		if(this.lcsCells != null && this.isWthinYBounds(y))
		{
			for(int i=0; i<this.lcsCells[0].length; i++)
			{
				lcsCells[i][y] = lcsCell;
			}
		}
	}
	
	//inclusive to both from and to
	public LCSCell[][] getCells(Coordinate from, Coordinate to)
	{
		LCSCell[][] result = null;
		if(this.isWthinBounds(new Coordinate[]{from, to}))
		{
			result = new LCSCell[to.getX()-from.getX()+1][to.getY()-from.getY()+1];
			for(int x=0; x<=(to.getX()-from.getX()); x++)
			{
				for(int y=0; y<=(to.getY()-from.getY()); y++)
				{
					result[x][y] = this.getCell(from.getX()+x, from.getY()+y);
				}
			}
		}
		return result;
	}
	
	public LCSMatrixBU[] split(Coordinate[] coordinates)
	{
		LCSMatrixBU[] lcsMatrices = null;
		if(coordinates == null)
		{
			lcsMatrices = new LCSMatrixBU[1];
			lcsMatrices[0] = this;
		}
		else
		{
			Arrays.sort(coordinates, new CoordinateComparator());
			// if overlaps exist in the coordinates, then we wont get here due to 
			// runtime overlap exception thrown by CoordinateComparator
			
			
			lcsMatrices = new LCSMatrixBU[coordinates.length+1];
			Coordinate prevCoordinate = new Coordinate(0, 0);
			for(int i=0; i<coordinates.length; i++)
			{
				lcsMatrices[i] = new LCSMatrixBU(this.getCells(prevCoordinate, coordinates[i]));
				prevCoordinate = coordinates[i];
			}
			Coordinate lastCoordinate = new Coordinate(this.lcsCells.length-1, this.lcsCells[0].length-1);
			lcsMatrices[lcsMatrices.length-1] = new LCSMatrixBU(this.getCells(prevCoordinate, lastCoordinate));
		}
		return lcsMatrices;
	}
	
	
	
	public boolean isWthinBounds(Coordinate[] coordinates)
	{
		boolean status = false;
		if(coordinates != null)
		{
			status = true;
			for(Coordinate coordinate : coordinates)
			{
				status &= this.isWthinBounds(coordinate);
			}
		}
		return status;
	}
	public boolean isWthinBounds(Coordinate coordinate)
	{
		boolean status = false;
		if(coordinate != null)
		{
			status = this.isWthinBounds(coordinate.getX(), coordinate.getY());
		}
		return status;
	}
	public boolean isWthinBounds(int x, int y)
	{
		return (x >= 0 && x < this.lcsCells.length && y >= 0 && y <this.lcsCells[0].length);
	}
	public boolean isWthinXBounds(int x)
	{
		return (x >= 0 && x < this.lcsCells.length);
	}
	public boolean isWthinYBounds(int y)
	{
		return (y >= 0 && y <this.lcsCells[0].length);
	}
	
	
	public void printMatrix(int cellSize)
	{
		StringBuffer row = new StringBuffer();
		if(this.lcsCells != null)
		{
			String[] rowOutput = new String[this.lcsCells.length];
			for(int y=0; y<this.lcsCells[0].length; y++)
			{
				for(int x=0; x<this.lcsCells.length; x++)
				{
					row.append("%"+cellSize+"s");
					if(lcsCells[x][y] != null)
					{
						rowOutput[x] = (char)LCSGrid.getASCIIInheritDirection(lcsCells[x][y].getInheritDirection()) + String.valueOf(lcsCells[x][y].getValue());
					}
					else
					{
						rowOutput[x] = "nil";
					}
				}
				System.out.printf(row.toString() + "\n", (Object[])rowOutput);
				row.setLength(0);
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		LCSMatrixBU m1 = new LCSMatrixBU(10, 10, new LCSCell(1, InheritDirection.TOP));
		m1.printMatrix(5);
		
		System.out.println("");
		
		
//		LCSMatrix[] lcsMatrices = m1.split(new Coordinate[]{new Coordinate(0,0), new Coordinate(2,9)});
		LCSMatrixBU[] lcsMatrices = m1.split(new Coordinate[]{new Coordinate(2,9)});
		for(int i=0; i<lcsMatrices.length; i++)
		{
			lcsMatrices[i].printMatrix(5);
			System.out.println("");
		}
		
//		LCSMatrix m2 = new LCSMatrix(m1.getCells(new Coordinate(0,0), new Coordinate(2,4)));
//		m2.printMatrix(5);
		
	}
		
	
}