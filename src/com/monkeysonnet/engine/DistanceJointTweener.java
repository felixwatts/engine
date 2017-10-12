package com.monkeysonnet.engine;

import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;

import aurelienribon.tweenengine.TweenAccessor;

public class DistanceJointTweener implements TweenAccessor<DistanceJoint>
{
	public static final int VAL_LENGTH = 0;
	public static final int VAL_FREQ = 1;
	
	@Override
	public int getValues(DistanceJoint target, int tweenType, float[] returnValues)
	{
		switch(tweenType)
		{
			case VAL_LENGTH:
				returnValues[0] = target.getLength();
				return 1;
			case VAL_FREQ:
				returnValues[0] = target.getFrequency();
				return 1;
			default:
				return -1;
		}
	}

	@Override
	public void setValues(DistanceJoint target, int tweenType, float[] newValues)
	{
		switch(tweenType)
		{
			case VAL_LENGTH:
				target.setLength(newValues[0]);
				break;
			case VAL_FREQ:
				target.setFrequency(newValues[0]);
				break;
		}
	}
}
