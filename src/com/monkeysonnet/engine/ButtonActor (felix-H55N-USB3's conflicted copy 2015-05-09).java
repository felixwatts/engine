package com.monkeysonnet.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ButtonActor extends Actor
{
	protected TextureRegion _tex;
	private IButtonEventHandler _handler;
	protected boolean _flipX, _flipY;
	private int _shortcutKey = -2;
	private Vector2 _dragStart = new Vector2();
	public Object userData;
	
	public ButtonActor(TextureRegion tex, IButtonEventHandler handler)
	{
		this(0, 0, 0, 0, false, false, tex, handler);
	}
	
	public ButtonActor(float x, float y, float w, float h, TextureRegion tex, IButtonEventHandler handler)
	{
		this(x, y, w, h, false, false, tex, handler);
	}
	
	public ButtonActor(float x, float y, float w, float h, boolean flipX, boolean flipY, TextureRegion tex, IButtonEventHandler handler)
	{
		this(x, y, w, h, flipX, flipY, -2, tex, handler);
	}
	
	public ButtonActor(float x, float y, float w, float h, boolean flipX, boolean flipY, int shortcutKey, TextureRegion tex, IButtonEventHandler handler)
	{
		_flipX = flipX;
		_flipY = flipY;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		originX = w / 2f;
		originY = h / 2f;
		_tex = tex;
		_handler = handler;
	}
	
	public void setTexture(TextureRegion tex)
	{
		_tex = tex;
	}
	
	public TextureRegion getTexture()
	{
		return _tex;
	}
	
	public void setFlipY(boolean flip)
	{
		_flipY = flip;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		if(_tex != null)
		{
			batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			batch.draw(_tex, x, y, originX, originY, width, height, scaleX * (_flipX ? -1 : 1), scaleY * (_flipY ? -1f : 1f), rotation);
		}
	}

	@Override
	public Actor hit(float x, float y)
	{
//		if(_handler == null)
//			return null;
		
		Actor result = x > 0 && x < width && y > 0 && y < height ? this : null;
		return result;
	}

	@Override
	public boolean touchDown(float x, float y, int cursor)
	{		
		if(_handler != null)
		{
			_dragStart.set(x, y);
			return _handler.onButtonDown(this);
		}
		else return false;
	}
	
	@Override
	public void touchUp(float x, float y, int cursor)
	{
		if(_handler != null)
			_handler.onButtonUp(this);
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer)
	{
		if(_handler != null)
		{
			Game.workingVector2a.set(x, y);
			Game.workingVector2a.sub(_dragStart);
			_handler.onButtonDragged(this, Game.workingVector2a);
		}
	}
	
	@Override
	public boolean keyDown(int key)
	{
		if(key == _shortcutKey && _handler != null)
			return _handler.onButtonDown(this);
		else return false;
	}
	
	@Override
	public boolean keyUp(int key)
	{
		if(key == _shortcutKey && _handler != null)
		{ 
			_handler.onButtonUp(this);
			return true;
		}
		else return false;
	}
}
