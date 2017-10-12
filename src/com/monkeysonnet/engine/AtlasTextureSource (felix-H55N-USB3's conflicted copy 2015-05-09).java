package com.monkeysonnet.engine;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

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
	
	public List<AtlasRegion> getFrames(String name)
	{
		return _atlas.findRegions(name);
	}
}
