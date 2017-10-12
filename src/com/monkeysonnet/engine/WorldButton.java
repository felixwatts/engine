package com.monkeysonnet.engine;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class WorldButton extends ButtonActor
{
	public static final int ALIGN_NEAR = 0, ALIGN_CENTRE = 1, ALIGN_FAR = 2;
	
	protected Vector2 _worldLoc;
	protected IProjection _renderer;
	private int _vAlign, _hAlign;
	
	public WorldButton(float worldCX, float worldCY, float w, float h, boolean flipX,
			boolean flipY, int shortcutKey, TextureRegion tex,
			IButtonEventHandler handler, IProjection renderer)
	{
		this(worldCX, worldCY, w, h, flipX,
			flipY, shortcutKey, tex,
			handler, renderer, ALIGN_CENTRE, ALIGN_CENTRE);
	}
	
	public WorldButton(float worldCX, float worldCY, float w, float h, boolean flipX,
			boolean flipY, int shortcutKey, TextureRegion tex,
			IButtonEventHandler handler, IProjection renderer, int hAlign, int vAlign)
	{
		super(0, 0, w, h, flipX, flipY, shortcutKey, tex, handler);
		_worldLoc = new Vector2(worldCX, worldCY);
		_renderer = renderer;
		_vAlign = vAlign;
		_hAlign = hAlign;
	}
	
	public void setWorldLocation(float x, float y)
	{
		_worldLoc.set(x, y);
		updatePosition();
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		updatePosition();
		super.draw(batch, parentAlpha);
	}
	
	protected void updatePosition()
	{
		Game.workingVector2a.set(_worldLoc);
		_renderer.worldToScreen(Game.workingVector2a);
		
		switch(_hAlign)
		{
			case ALIGN_NEAR:				
				setX(Game.workingVector2a.x);
				break;
			case ALIGN_CENTRE:
				setX(Game.workingVector2a.x - (getWidth()/2f));
				break;
			case ALIGN_FAR:
				setX(Game.workingVector2a.x - getWidth());
				break;
		}
		
		switch(_vAlign)
		{
			case ALIGN_NEAR:				
				setY(Game.workingVector2a.y);
				break;
			case ALIGN_CENTRE:
				setY(Game.workingVector2a.y - (getHeight()/2f));
				break;
			case ALIGN_FAR:
				setY(Game.workingVector2a.y - getHeight());
				break;
		}
	}
}
