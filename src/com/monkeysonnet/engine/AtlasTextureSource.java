package com.monkeysonnet.engine;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

public class AtlasTextureSource implements ITextureSource
{
	private TextureAtlas _atlas;
	
	public AtlasTextureSource(TextureAtlas atlas)
	{
		_atlas = atlas;
	}

	@Override
	public TextureRegion get(String textureName)
	{
		return _atlas.findRegion(textureName);
	}

	@Override
	public Iterable<AtlasRegion> getAll()
	{
		return _atlas.getRegions();
	}
	
	public Array<AtlasRegion> getFrames(String name)
	{
		return _atlas.findRegions(name);
	}
}
