package com.monkeysonnet.engine;

import com.badlogic.gdx.input.GestureDetector;

public class GestureAndKeyDetector extends GestureDetector
{
	private GestureAndKeyListener _listener;
	
	public GestureAndKeyDetector(GestureAndKeyListener listener)
	{		
		super(listener);
		_listener = listener;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		return _listener.keyDown(keycode);
	}
	
	@Override
	public boolean keyUp(int keycode)
	{
		return _listener.keyUp(keycode);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		if(_listener.touchDown(x, y, pointer, button))
			return true;
		else return super.touchDown(x, y, pointer, button);
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		if(_listener.touchUp(x, y, pointer, button))
			return true;
		else return super.touchUp(x, y, pointer, button);
	}
}
