package com.monkeysonnet.engine;

public interface ISimulationEventHandler
{
	void onSimulationEvent(int eventType, Object argument);
}
