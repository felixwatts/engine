package com.monkeysonnet.engine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public interface IButtonEventHandler
{
	boolean onButtonDown(Actor sender);
	void onButtonUp(Actor sender);
	void onButtonDragged(Actor sender, Vector2 delta);
}
