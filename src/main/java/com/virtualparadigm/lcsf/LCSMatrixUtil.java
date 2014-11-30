package com.virtualparadigm.lcsf;

import java.util.HashMap;
import java.util.Map;

public class LCSMatrixUtil
{
//	private static Map<InheritDirection, Integer> inheritDirectionMap;
//	static
//	{
//		inheritDirectionMap = new HashMap<InheritDirection, Integer>();
//		inheritDirectionMap.put(InheritDirection.TOP, 124);
//		inheritDirectionMap.put(InheritDirection.LEFT, 45);
//		inheritDirectionMap.put(InheritDirection.DIAGONAL, 92);
//	}

	private static Map<InheritDirection, String> inheritDirectionMap;
	static
	{
		inheritDirectionMap = new HashMap<InheritDirection, String>();
		inheritDirectionMap.put(InheritDirection.TOP, "|");
		inheritDirectionMap.put(InheritDirection.LEFT, "--");
		inheritDirectionMap.put(InheritDirection.DIAGONAL, "\\");
	}
	
	
	public static void printMatrix(Matrix<LCSCell> matrix, int cellSize)
	{
		StringBuffer strRow = new StringBuffer();
		
		String[] rowOutput = new String[matrix.getXSize() + 1];
//		Object[] row = null;
		LCSCell[] row = null;
		for(int y=0; y<matrix.getYSize(); y++)
		{
			row = matrix.getRow(y);
			if(row != null)
			{
				for(int x=0; x<row.length; x++)
				{
					strRow.append("%"+cellSize+"s");
					if(row[x] != null)
					{
//						rowOutput[x] = inheritDirectionMap.get(((LCSCell)row[x]).getInheritDirection()) + " " + ((LCSCell)row[x]).getValue();
						rowOutput[x] = inheritDirectionMap.get(row[x].getInheritDirection()) + String.valueOf(row[x].getValue());
//						rowOutput[x] = String.valueOf(row[x].getValue());
					}
					else
					{
						rowOutput[x] = "nil";
					}
				}
			}
			System.out.printf(strRow.toString() + "\n", (Object[])rowOutput);
			strRow.setLength(0);
		}
	}	
	
	
	public static void fillCells(Matrix<LCSCell> matrix, Coordinate from, Coordinate to, LCSCell lcsCell)
	{
		if(matrix != null && lcsCell != null)
		{
			for(int x=0; x<=(to.getX()-from.getX()); x++)
			{
				for(int y=0; y<=(to.getY()-from.getY()); y++)
				{
					matrix.setCell(x, y, new LCSCell(lcsCell.getValue(), lcsCell.getInheritDirection()));
				}
			}
		}
	}
	
	public static void fillCells(Matrix<LCSCell> matrix, LCSCell lcsCell)
	{
		if(matrix != null && lcsCell != null)
		{
			LCSMatrixUtil.fillCells(matrix, new Coordinate(0,0), new Coordinate(matrix.getXSize(), matrix.getYSize()), lcsCell);
		}
	}
	
	
	
	public static void main(String[] args)
	{
//		LCSMatrix m1 = new LCSMatrix(10, 10, new LCSCell(1, InheritDirection.TOP));
//		Matrix<LCSCell> m1 = new Matrix<LCSCell>(10, 10, new LCSCell(1, InheritDirection.TOP));
		Matrix<LCSCell> m1 = new Matrix<LCSCell>(10, 10, LCSCell.class);
		
		int count = 1;
		for(int x=0; x<10; x++)
		{
			for(int y=0; y<10; y++)
			{
				m1.setCell(x, y, new LCSCell(count++, InheritDirection.LEFT));
			}
		}
		
//		LCSMatrixUtil.fillCells(m1, new LCSCell(1, InheritDirection.LEFT));
		LCSMatrixUtil.printMatrix(m1, 5);
		
		System.out.println("");
		
		
//		LCSMatrix[] lcsMatrices = m1.split(new Coordinate[]{new Coordinate(0,0), new Coordinate(2,9)});
//		LCSMatrix[] lcsMatrices = (LCSMatrix[])m1.split(new Coordinate[]{new Coordinate(2,9)});

//		Matrix<LCSCell>[] lcsMatrices = m1.split(new Coordinate[]{new Coordinate(2,9)});
		Matrix<LCSCell>[] lcsMatrices = m1.split(new Coordinate[]{new Coordinate(5,4)});
		for(int i=0; i<lcsMatrices.length; i++)
		{
			LCSMatrixUtil.printMatrix(lcsMatrices[i], 5);
//			((LCSMatrix)lcsMatrices[i]).printMatrix(5);
			System.out.println("");
		}
		
//		LCSMatrix m2 = new LCSMatrix(m1.getCells(new Coordinate(0,0), new Coordinate(2,4)));
//		m2.printMatrix(5);
		
	}
	
		
	
}