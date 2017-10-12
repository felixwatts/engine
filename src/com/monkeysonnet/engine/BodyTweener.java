package com.monkeysonnet.engine;

import com.badlogic.gdx.physics.box2d.Body;

import aurelienribon.tweenengine.TweenAccessor;

public class BodyTweener implements TweenAccessor<Body>
{
	public static final int VAL_POS_XY = 0;
	public static final int VAL_ANGLE = 1;
	public static final int VAL_POS_X = 2;
	public static final int VAL_POS_Y = 3;
	public static final int VAL_VEL_Y = 4;
	public static final int VAL_SPEED = 5;

	@Override
	public int getValues(Body target, int tweenType, float[] returnValues)
	{
		switch(tweenType)
		{
			case VAL_POS_XY:
				returnValues[0] = target.getPosition().x;
				returnValues[1] = target.getPosition().y;
				return 2;
			case VAL_POS_X:
				returnValues[0] = target.getPosition().x;
				return 1;
			case VAL_POS_Y:
				returnValues[0] = target.getPosition().y;
				return 1;
			case VAL_ANGLE:
				returnValues[0] = target.getAngle();
				return 1;
			case VAL_VEL_Y:
				returnValues[0] = target.getLinearVelocity().y;
				return 1;
			case VAL_SPEED:
				returnValues[0] = target.getLinearVelocity().len();
				return 1;
			default: 
				return -1;
		}
	}

	@Override
	public void setValues(Body target, int tweenType, float[] newValues)
	{
		switch(tweenType)
		{
			case VAL_POS_XY:
				target.setTransform(Game.workingVector2a.set(newValues[0], newValues[1]), target.getAngle());
				break;
			case VAL_POS_X:
				target.setTransform(Game.workingVector2a.set(newValues[0], target.getWorldCenter().y), target.getAngle());
				break;
			case VAL_POS_Y:
				target.setTransform(Game.workingVector2a.set(target.getWorldCenter().x, newValues[0]), target.getAngle());
				break;
			case VAL_ANGLE:
				target.setTransform(target.getWorldCenter(), newValues[0]);
				break;
			case VAL_VEL_Y:
				target.setLinearVelocity(target.getLinearVelocity().x, newValues[0]);
				break;
			case VAL_SPEED:
				target.setLinearVelocity(Game.workingVector2a.set(target.getLinearVelocity()).nor().scl(newValues[0]));
				break;
		}
	}

}
