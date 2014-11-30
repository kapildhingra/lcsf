package com.virtualparadigm.lcsf;

public class Cell<T extends Cloneable> implements Cloneable
{
	private T value;
	public Cell()
	{
		super();
	}
	public Cell(T value)
	{
		super();
		this.value = value;
	}
	public T getValue()
	{
		return value;
	}
	public void setValue(T value)
	{
		this.value = value;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		Cell<T> clone = (Cell<T>)super.clone();
//		clone.setValue(((T)this.value).);
		return super.clone();
	}
}