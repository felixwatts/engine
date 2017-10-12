package com.monkeysonnet.engine;

import android.view.DragEvent;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

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
		setPosition(x, y);
		setSize(w, h);
        setOrigin(w / 2f, h / 2f);
		_tex = tex;
		_handler = handler;

        if(handler != null)
        {
            addListener(new ButtonEventListener(handler, this));
        }
	}

    class ButtonEventListener implements EventListener
    {
        private IButtonEventHandler _handler;
        private Actor _actor;

        ButtonEventListener(IButtonEventHandler handler, Actor actor) {
            _handler = handler;
            _actor = actor;
        }

        @Override
        public boolean handle(Event event) {

            if (event instanceof InputEvent) {
                InputEvent inputEvent = (InputEvent)event;
                if (inputEvent.getType() == InputEvent.Type.touchDown) {
                    touchDown(inputEvent.getStageX(), inputEvent.getStageY());
                    return true;
                }

                if (inputEvent.getType() == InputEvent.Type.touchUp) {
                    touchUp();
                    return true;
                }

                if (inputEvent.getType() == InputEvent.Type.touchDragged) {
                    touchDragged(inputEvent.getStageX(), inputEvent.getStageY());
                    return true;
                }

                if(inputEvent.getType() == InputEvent.Type.keyDown) {
                    keyDown(inputEvent.getKeyCode());
                    return true;
                }

                if(inputEvent.getType() == InputEvent.Type.keyUp) {
                    keyUp(inputEvent.getKeyCode());
                    return true;
                }
            }

            return false;
        }

        public boolean touchDown(float x, float y)
        {
            if(_handler != null)
            {
                _dragStart.set(x, y);
                return _handler.onButtonDown(_actor);
            }
            else return false;
        }

        public void touchUp()
        {
            if(_handler != null)
                _handler.onButtonUp(_actor);
        }

        public void touchDragged(float x, float y)
        {
            if(_handler != null)
            {
                Game.workingVector2a.set(x, y);
                Game.workingVector2a.sub(_dragStart);
                _handler.onButtonDragged(_actor, Game.workingVector2a);
            }
        }

        public boolean keyDown(int key)
        {
            if(key == _shortcutKey && _handler != null)
                return _handler.onButtonDown(_actor);
            else return false;
        }

        public boolean keyUp(int key)
        {
            if(key == _shortcutKey && _handler != null)
            {
                _handler.onButtonUp(_actor);
                return true;
            }
            else return false;
        }
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
	public void draw(Batch batch, float parentAlpha)
	{
		if(_tex != null)
		{
			batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
			batch.draw(_tex, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX()* (_flipX ? -1 : 1), getScaleY() * (_flipY ? -1f : 1f), getRotation());
		}
	}
}
