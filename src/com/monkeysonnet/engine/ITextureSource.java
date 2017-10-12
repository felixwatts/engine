package com.monkeysonnet.engine;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

public interface ITextureSource
{
	TextureRegion get(String textureName);
	Iterable<AtlasRegion> getAll();
	Array<AtlasRegion> getFrames(String name);
}
