package com.monkeysonnet.engine;

import com.badlogic.gdx.math.Vector2;

import aurelienribon.tweenengine.TweenAccessor;

public class Vector2Tweener implements TweenAccessor<Vector2>
{

	@Override
	public int getValues(Vector2 target, int tweenType, float[] returnValues)
	{
		returnValues[0] = target.x;
		returnValues[1] = target.y;
		return 2;
	}

	@Override
	public void setValues(Vector2 target, int tweenType, float[] newValues)
	{
		target.x = newValues[0];
		target.y = newValues[1];
	}
}
