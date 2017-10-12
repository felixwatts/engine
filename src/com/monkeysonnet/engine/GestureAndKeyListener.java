package com.monkeysonnet.engine;

import com.badlogic.gdx.input.GestureDetector.GestureListener;

public interface GestureAndKeyListener extends GestureListener
{
	boolean keyDown (int keycode);
	boolean keyUp (int keycode);
	boolean touchDown(int x, int y, int pointer, int button);
	boolean touchUp(int x, int y, int pointer, int button);
}
