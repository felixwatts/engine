package com.monkeysonnet.engine;

import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.TweenAccessor;

public class ActorTweener implements TweenAccessor<Actor>
{
	public static final int VAL_COLOR_RGBA = 0;
	public static final int VAL_SCALE = 1;
	public static final int VAL_POS_XY = 2;
	public static final int VAL_SIZE = 3;

	@Override
	public int getValues(Actor target, int tweenType, float[] returnValues)
	{
		switch(tweenType)
		{
			case VAL_COLOR_RGBA:
				returnValues[0] = target.color.r;
				returnValues[1] = target.color.g;
				returnValues[2] = target.color.b;
				returnValues[3] = target.color.a;
				return 4;
			case VAL_SCALE:
				returnValues[0] = target.scaleX;
				return 1;
			case VAL_POS_XY:
				returnValues[0] = target.x;
				returnValues[1] = target.y;
				return 2;
			case VAL_SIZE:
				returnValues[0] = target.width;
				returnValues[1] = target.height;
				return 2;
			default:
				return -1;
		}
	}

	@Override
	public void setValues(Actor target, int tweenType, float[] newValues)
	{
		switch(tweenType)
		{
			case VAL_COLOR_RGBA:
				target.color.r = newValues[0];
				target.color.g = newValues[1];
				target.color.b = newValues[2];
				target.color.a = newValues[3];
				break;
			case VAL_SCALE:
				target.scaleX = target.scaleY = newValues[0];
				break;
			case VAL_POS_XY:
				target.x = newValues[0];
				target.y = newValues[1];
				break;
			case VAL_SIZE:
				target.width = newValues[0];
				target.height = newValues[1];
				break;
		}
	}

}
