package com.monkeysonnet.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

public class ScreenManager
{
	private Array<IScreen> _screenStack, _fullscreenStack;
	
	public ScreenManager()
	{
		_screenStack = new Array<IScreen>();
		_fullscreenStack = new Array<IScreen>();
	}
	
	public boolean isEmpty()
	{
		return _screenStack.size == 0;
	}
	
	public void push(IScreen screen)
	{
		if(_screenStack.size != 0)
			_screenStack.peek().blur();
		
		if(screen.isFullScreen())
		{
			boolean hidden = false;
			for(IScreen below : _screenStack)
			{
				if(below == _fullscreenStack.peek())
					hidden = true;
				
				if(hidden)
				{
					below.blur();
					below.hide();
				}
			}
					
			_fullscreenStack.add(screen);
		}
		
		_screenStack.add(screen);		
		screen.show();
		
		if(_screenStack.peek() == screen)
			screen.focus();
	}
	
	public void pop()
	{
		IScreen s = _screenStack.pop();		
		s.blur();
		s.hide();
		
		if(s.isFullScreen())
		{
			_fullscreenStack.pop();
			
			boolean visible = false;
			for(IScreen revealed : _screenStack)
			{
				if(revealed == _fullscreenStack.peek())
					visible = true;
				
				if(visible)
					revealed.show();
			}
		}
		
		if(_screenStack.size > 0)
		{
			_screenStack.peek().focus();
		}
	}
	
	public void clear()
	{
		while(!isEmpty())
			pop();
	}
	
	public void render()
	{
		boolean visible = false;
		for(IScreen screen : _screenStack)
		{
			if(screen == _fullscreenStack.peek())
				visible = true;
			
			if(visible)
				screen.render();
		}
	}
	
	public void serialize()
	{
		Preferences state = Gdx.app.getPreferences("state");
		state.clear();
		
		if(_screenStack.size > 0)
			_screenStack.peek().pause();
		
		state.putInteger("num-screens", _screenStack.size);
		
		for(int n = _screenStack.size-1; n >= 0; n--)
		{
			IScreen s = _screenStack.get(n);
			
			Preferences dict = Gdx.app.getPreferences("state-screen-" + n);
			dict.clear();	
			dict.putString("screen-type", s.getClass().getName());
			s.serialize(dict);
			dict.flush();
		}
		
		state.flush();
	}
	
	public void deserialize() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Preferences state = Gdx.app.getPreferences("state");
		int sNum = state.getInteger("num-screens", 0);
		for(int n = 0; n < sNum; n++)
		{
			Preferences dict = Gdx.app.getPreferences("state-screen-" + n);			
			IScreen s = (IScreen)Class.forName(dict.getString("screen-type")).newInstance();
			s.deserialize(dict);
			push(s);
		}
		
		clearState();
	}
	
	public void clearState()
	{
		Preferences state = Gdx.app.getPreferences("state");
		state.clear();
		state.flush();
	}

	public void pause() 
	{
		if(_screenStack.size > 0)
			_screenStack.peek().pause();
	}
	
//	public IScreen createScreen(Preferences dict)
//	{
//		if(dict.getString("screen-type") == "TitleScreen")
//		{
//			return new TitleScreen();
//		}
//		else if(dict.getString("screen-type") == "ChapterSelectScreen")
//		{
//			return new ChapterSelectScreen();
//		}
//		else if(dict.getString("screen-type") == "MapSelectScreen")
//		{
//			return new MapSelectScreen();
//		}
//		else if(dict.getString("screen-type") == "PlayScreen")
//		{
//			return new PlayScreen(dict);
//		}
//		else if(dict.getString("screen-type") == "Caption")
//		{
//			return new Caption();
//		}
//		else if(dict.getString("screen-type") == "PlayMenuScreen")
//		{
//			return new PlayMenuScreen();
//		}
//		else
//		{
//			return null;
//		}
//	}
}
