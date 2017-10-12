package com.monkeysonnet.engine;

public interface IEntity
{
	void update(float dt);	
	void free();
	int layer();
}
