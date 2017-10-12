package com.monkeysonnet.engine;

import com.badlogic.gdx.Preferences;

public interface IScreen
{
	void show();
	void focus();	
	void render();
	void blur();
	void hide();
	boolean isFullScreen();	
	void serialize(Preferences dict);
	void deserialize(Preferences dict);
	void pause();
}
