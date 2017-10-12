package com.monkeysonnet.engine;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.utils.Array;

public class Sim
{
	public final Array<IEntity> entities = new Array<IEntity>();
	public float timeMultiplier = 1f;	
	public ISimulationEventHandler handler;	
	public final TweenManager tweens = new TweenManager();
	public boolean paused;
	
	public void fireEvent(int ev, Object arg)
	{
		if(handler != null)
			handler.onSimulationEvent(ev, arg);
	}
	
	public void update(float dt)
	{
		if(paused)
			return;
		
		dt *= timeMultiplier;
		
		tweens.update(dt*1000f);
		
		for(IEntity e: entities)
			e.update(dt);
	}
}
