package com.monkeysonnet.engine;

public class FixtureTag
{
	public Object owner;
	public IContactHandler contactHandler;

	public FixtureTag(Object owner, IContactHandler ch)
	{
		contactHandler = ch;
		this.owner = owner;
	}
}
