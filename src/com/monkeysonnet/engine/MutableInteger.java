package com.monkeysonnet.engine;

public class MutableInteger
{
	private int _value;
	
	public MutableInteger(int val)
	{
		_value = val;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return ((MutableInteger)obj)._value == this._value;
	}
	
	@Override
	public int hashCode()
	{
		return _value;
	}
	
	public MutableInteger set(int val)
	{
		_value = val;
		return this;
	}
	
	public int value()
	{
		return _value;
	}
}
