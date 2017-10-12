package com.monkeysonnet.engine.editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.monkeysonnet.engine.ColorTools;
import com.monkeysonnet.engine.ITextureSource;

public class Graphic
{
	public String label;
	public String properties;
	public String textureName;
	public float tileSize;
	public float left;
	public float right;
	public float top;
	public float bottom;
	public boolean flipX;
	public boolean flipY;
	public float rotation;
	public int zIndex;

	public static Graphic deserialize(ObjectInputStream strm, int version) throws IOException
	{
		switch(version)
		{
			case 0:
				throw new IOException("unsupported file version: " + version);
			case 1:
			case 2:
			{
				Graphic result = new Graphic();
				if(strm.readBoolean())
					result.label = strm.readUTF();
				if(strm.readBoolean())
					result.properties = strm.readUTF();
				result.textureName = strm.readUTF();
				result.tileSize = strm.readFloat();
				result.left = strm.readFloat();
				result.right = strm.readFloat();
				result.top = strm.readFloat();
				result.bottom = strm.readFloat();
				result.flipX = strm.readBoolean();
				result.flipY = strm.readBoolean();
				result.rotation = strm.readFloat();
				
				return result;
			}
			case 3:
			{
				Graphic result = new Graphic();
				result.label = strm.readUTF();
				if(result.label.equals("<null>"))
					result.label = null;
				result.properties = strm.readUTF();
				if(result.properties.equals("<null>"))
					result.properties = null;
				result.textureName = strm.readUTF();
				result.tileSize = strm.readFloat();
				result.left = strm.readFloat();
				result.right = strm.readFloat();
				result.top = strm.readFloat();
				result.bottom = strm.readFloat();
				result.flipX = strm.readBoolean();
				result.flipY = strm.readBoolean();
				result.rotation = strm.readFloat();
				result.zIndex = strm.readInt();
				
				return result;
			}
			default: throw new IOException("unknown file version: " + version);
		}
	}
	
	public void serialize(ObjectOutputStream strm) throws IOException
	{
		strm.writeUTF(label == null ? "<null>" : label);
		strm.writeUTF(properties == null ? "<null>" : properties);
		strm.writeUTF(textureName);
		strm.writeFloat(tileSize);
		strm.writeFloat(left);
		strm.writeFloat(right);
		strm.writeFloat(top);
		strm.writeFloat(bottom);
		strm.writeBoolean(flipX);
		strm.writeBoolean(flipY);
		strm.writeFloat(rotation);
		strm.writeInt(zIndex);
	}
	
	public Sprite toSprite(ITextureSource textures)
	{
		Sprite spr = new Sprite(textures.get(textureName));
		
		spr.setBounds(left, bottom, right - left, top - bottom);	
		
		if(tileSize > 0)
		{
			spr.setRegion(
				(int)spr.getRegionX(), 
				(int)spr.getRegionY(),
				(int)((spr.getWidth() / tileSize) * spr.getRegionWidth()), 
				(int)((spr.getHeight() / tileSize) * spr.getRegionHeight()) * (spr.getRegionWidth() / spr.getRegionHeight()));
		}
		
		spr.flip(flipX, flipY);
		
		if(hasProperty("colour"))
		{
			Color c = ColorTools.parse(propertyValue("colour"));
			if(c != null)
				spr.setColor(c);
		}
		
		return spr;
	}
	
	public Graphic clone()
	{
		Graphic clone = new Graphic();
		
		clone.bottom = bottom;
		clone.flipX = flipX;
		clone.flipY = flipY;
		clone.label = label;
		clone.left = left;
		clone.properties = properties;
		clone.right = right;
		clone.rotation = rotation;
		clone.textureName = textureName;
		clone.tileSize = tileSize;
		clone.top = top;
		clone.zIndex = zIndex;
		
		return clone;
	}
	
	public boolean hasProperty(String property)
	{
		return PropertyParser.hasProperty(property, properties);
	}
	
	public String propertyValue(String property)
	{
		return PropertyParser.propertyValue(property, properties);
	}
}
