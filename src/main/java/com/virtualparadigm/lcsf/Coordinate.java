package com.virtualparadigm.lcsf;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Coordinate implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;
	
	public Coordinate()
	{
		super();
	}
	public Coordinate(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void incrementX()
	{
		this.x++;
	}
	public void decrementX()
	{
		this.x--;
	}
	
	public int getY()
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void incrementY()
	{
		this.y++;
	}
	public void decrementY()
	{
		this.y--;
	}
	
	// =============================================================
	// UTILITY METHODS
	// =============================================================
	@Override
	public String toString()
	{
		return "(" + this.getX() + "," + this.getY() + ")";
	}
	
    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.getX());
        builder.append(this.getY());
        return builder.toHashCode();        
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (obj == this)
        {
            return true;
        }
        if (obj.getClass() != getClass())
        {
            return false;
        }
        Coordinate that = (Coordinate)obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.getX(), that.getX());
        builder.append(this.getY(), that.getY());
        return builder.isEquals();
    }	
}