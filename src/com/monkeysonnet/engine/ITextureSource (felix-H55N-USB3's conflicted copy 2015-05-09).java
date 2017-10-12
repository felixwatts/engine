package com.monkeysonnet.engine;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public interface ITextureSource
{
	TextureRegion get(String textureName);
	Iterable<AtlasRegion> getAll();
	List<AtlasRegion> getFrames(String name);
}
