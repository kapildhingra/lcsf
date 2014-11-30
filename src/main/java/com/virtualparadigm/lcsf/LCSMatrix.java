package com.virtualparadigm.lcsf;

import java.util.HashMap;
import java.util.Map;

public class LCSMatrix extends Matrix<LCSCell>
{
	private static Map<InheritDirection, Integer> inheritDirectionMap;
	
	static
	{
		inheritDirectionMap = new HashMap<InheritDirection, Integer>();
		inheritDirectionMap.put(InheritDirection.TOP, 124);
		inheritDirectionMap.put(InheritDirection.LEFT, 45);
		inheritDirectionMap.put(InheritDirection.DIAGONAL, 92);
	}
	
	
	public LCSMatrix(int xsize, int ysize, LCSCell defaultCell)
	{
		super(xsize, ysize, LCSCell.class);
		if(defaultCell != null)
		{
			for(int x=0; x<xsize; x++)
			{
				for(int y=0; y<ysize; y++)
				{
					this.setCell(x, y, new LCSCell(defaultCell.getValue(), defaultCell.getInheritDirection()));
				}
			}
		}
	}
	
//	@Override
//	public void printMatrix(int cellSize)
//	{
//		StringBuffer row = new StringBuffer();
//		
//		String[] rowOutput = new String[this.getXSize() + 1];
//		
//		if(this.cells != null)
//		{
//			for(int y=0; y<this.cells[0].length; y++)
//			{
//				for(int x=0; x<this.cells.length; x++)
//				{
//					row.append("%"+cellSize+"s");
//					if(cells[x][y] != null)
//					{
//						rowOutput[x] = inheritDirectionMap.get(((LCSCell)cells[x][y]).getInheritDirection()) + " " + ((LCSCell)cells[x][y]).getValue();
//					}
//					else
//					{
//						rowOutput[x] = "nil";
//					}
//				}
//				System.out.printf(row.toString() + "\n", (Object[])rowOutput);
//				row.setLength(0);
//			}
//			
//		}

		
//		if(this.cells != null)
//		{
//			String[] rowOutput = new String[this.cells.length];
//			for(int y=0; y<this.cells[0].length; y++)
//			{
//				for(int x=0; x<this.cells.length; x++)
//				{
//					row.append("%"+cellSize+"s");
//					if(cells[x][y] != null)
//					{
//						rowOutput[x] = cells[x][y].toString();
//					}
//					else
//					{
//						rowOutput[x] = "nil";
//					}
//				}
//				System.out.printf(row.toString() + "\n", (Object[])rowOutput);
//				row.setLength(0);
//			}
//		}
//	}	
	
	public static void main(String[] args)
	{
		LCSMatrix m1 = new LCSMatrix(10, 10, new LCSCell(1, InheritDirection.TOP));
//		m1.printMatrix(5);
		
		System.out.println("");
		
		
//		LCSMatrix[] lcsMatrices = m1.split(new Coordinate[]{new Coordinate(0,0), new Coordinate(2,9)});
//		LCSMatrix[] lcsMatrices = (LCSMatrix[])m1.split(new Coordinate[]{new Coordinate(2,9)});

		Matrix<LCSCell>[] lcsMatrices = m1.split(new Coordinate[]{new Coordinate(2,9)});
		for(int i=0; i<lcsMatrices.length; i++)
		{
//			((LCSMatrix)lcsMatrices[i]).printMatrix(5);
			System.out.println("");
		}
		
//		LCSMatrix m2 = new LCSMatrix(m1.getCells(new Coordinate(0,0), new Coordinate(2,4)));
//		m2.printMatrix(5);
		
	}
	
		
	
}