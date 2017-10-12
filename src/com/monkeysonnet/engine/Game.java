package com.monkeysonnet.engine;

import java.util.Random;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Game implements ApplicationListener
{
	public static ScreenManager ScreenManager;
	public static final TweenManager TweenManager = new TweenManager();	
	public static final Vector2 workingVector2a = new Vector2(), workingVector2b = new Vector2(), workingVector2c = new Vector2();
	public static final Vector3 workingVector3 = new Vector3();	
	public static com.badlogic.gdx.Preferences Preferences;
	public static final Random Dice = new Random();
	
	public static final float WORLD_STEP_TIME_MS = 1000f/60f;
	public static final float WORLD_STEP_TIME_S = 1f/60f;
	
	@Override
	public void create() 
	{
		Gdx.input.setCatchBackKey(true);
		Preferences = Gdx.app.getPreferences("preferences");
		registerTweenAccessors();		
		ScreenManager = new ScreenManager();
	}	

	protected void registerTweenAccessors()
	{
		Tween.setCombinedAttributesLimit(4);
		Tween.setWaypointsLimit(8);
		
		Tween.registerAccessor(Actor.class, new ActorTweener());
		Tween.registerAccessor(Sprite.class, new SpriteTweener());
		Tween.registerAccessor(Vector2.class, new Vector2Tweener());		
		Tween.registerAccessor(Body.class, new BodyTweener());
		Tween.registerAccessor(DistanceJoint.class, new DistanceJointTweener());
		Tween.registerAccessor(Color.class, new ColorTweener());
	}

	@Override
	public void render() 
	{
		if(ScreenManager.isEmpty())
		{
			Gdx.app.exit();
			return;
		}

		TweenManager.update(WORLD_STEP_TIME_MS);
		ScreenManager.render();
	}

	@Override
	public void resume() 
	{
	}
	
	@Override
	public void pause() 
	{
		ScreenManager.pause();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void dispose()
	{
	}
}
