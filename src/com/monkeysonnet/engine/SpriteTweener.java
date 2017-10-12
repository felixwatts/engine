package com.monkeysonnet.engine;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

public class SpriteTweener implements TweenAccessor<Sprite>
{
	public static final int VAL_COLOR_RGBA = 0;
	public static final int VAL_SCALE = 1;
	public static final int VAL_POS_XY = 2;
	public static final int VAL_POS_X = 3;
	public static final int VAL_POS_Y = 4;
	public static final int VAL_ROTATION = 5;

	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues)
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
			case VAL_POS_X:
				returnValues[0] = target.getX();
				return 1;
			case VAL_POS_Y:
				returnValues[0] = target.getY();
				return 1;
			case VAL_ROTATION:
				returnValues[0] = target.getRotation();
				return 1;
			default:
				return -1;
		}
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues)
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
			case VAL_POS_X:
				target.setX(newValues[0]);
				break;
			case VAL_POS_Y:
				target.setY(newValues[0]);
				break;
			case VAL_ROTATION:
				target.setRotation(newValues[0]);
				break;
		}
	}

}
