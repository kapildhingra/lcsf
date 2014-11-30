package com.virtualparadigm.lcsf;

public class LCSCell implements Cloneable
{
	private int value;
	private InheritDirection inheritDirection;
	public LCSCell()
	{
		super();
	}
	public LCSCell(int value, InheritDirection inheritDirection)
	{
		super();
		this.value = value;
		this.inheritDirection = inheritDirection;
	}
	public int getValue()
	{
		return value;
	}
	public void setValue(int value)
	{
		this.value = value;
	}
	public InheritDirection getInheritDirection()
	{
		return inheritDirection;
	}
	public void setInheritDirection(InheritDirection inheritDirection)
	{
		this.inheritDirection = inheritDirection;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		LCSCell clone = (LCSCell)super.clone();
		clone.setInheritDirection(this.inheritDirection);
		clone.setValue(this.value);
		return super.clone();
	}
}