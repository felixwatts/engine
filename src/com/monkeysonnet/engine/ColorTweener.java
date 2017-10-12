package com.monkeysonnet.engine;

import com.badlogic.gdx.graphics.Color;

import aurelienribon.tweenengine.TweenAccessor;

public class ColorTweener implements TweenAccessor<Color>
{
	public static final int VAL_RGBA = 0;
	public static final int VAL_HSLA = 1;
	
	@Override
	public int getValues(Color target, int tweenType, float[] returnValues)
	{
		switch(tweenType)
		{
			case VAL_RGBA:
				returnValues[0] = target.r;
				returnValues[1] = target.g;
				returnValues[2] = target.b;
				returnValues[3] = target.a;
				return 4;
			case VAL_HSLA:
				Color c = ColorTools.rgbToHsl(target.r, target.g, target.b);
				returnValues[0] = c.r;
				returnValues[1] = c.g;
				returnValues[2] = c.b;
				returnValues[3] = target.a;
				return 4;
			default:
				return -1;
		}
	}

	@Override
	public void setValues(Color target, int tweenType, float[] newValues)
	{
		switch(tweenType)
		{
			case VAL_RGBA:
				target.r = newValues[0];
				target.g = newValues[1];
				target.b = newValues[2];
				target.a = newValues[3];
				break;
			case VAL_HSLA:
				Color c = ColorTools.hslToColor(
						newValues[0], 
						newValues[1], 
						newValues[2], 
						newValues[3]);

				target.set(c);
				break;
		}
	}
}
