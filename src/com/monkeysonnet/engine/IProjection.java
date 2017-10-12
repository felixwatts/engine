package com.monkeysonnet.engine;

import com.badlogic.gdx.math.Vector2;

public interface IProjection
{
	void worldToScreen(Vector2 v);
	void screenToWorld(Vector2 v);
}
