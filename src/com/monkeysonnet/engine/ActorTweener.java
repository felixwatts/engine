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
				returnValues[0] = target.getColor().r;
				returnValues[1] = target.getColor().g;
				returnValues[2] = target.getColor().b;
				returnValues[3] = target.getColor().a;
				return 4;
			case VAL_SCALE:
				returnValues[0] = target.getScaleX();
				return 1;
			case VAL_POS_XY:
				returnValues[0] = target.getX();
				returnValues[1] = target.getY();
				return 2;
			case VAL_SIZE:
				returnValues[0] = target.getWidth();
				returnValues[1] = target.getHeight();
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
                target.setColor(newValues[0], newValues[1], newValues[2], newValues[3]);
				break;
			case VAL_SCALE:
                target.setScale(newValues[0], newValues[0]);
				break;
			case VAL_POS_XY:
                target.setPosition(newValues[0], newValues[1]);
				break;
			case VAL_SIZE:
                target.setSize(newValues[0], newValues[1]);
				break;
		}
	}

}
