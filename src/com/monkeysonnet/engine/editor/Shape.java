package com.monkeysonnet.engine.editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.math.Vector2;

public class Shape
{
	public static final int TYPE_LOOP = 0;
	public static final int TYPE_CHAIN = 1;
	
	public int type;
	public String label;
	public String properties;
	public Vector2[] shape;
	
	public static void serialize(String label, String props, int type, Vector2[] shape, ObjectOutputStream strm) throws IOException
	{
		strm.writeInt(type);
		strm.writeUTF(label == null ? "<null>" : label);
		strm.writeUTF(props == null ? "<null>" : props);
		strm.writeInt(shape.length);
		for(int n = 0; n < shape.length; n++)
			strm.writeObject(shape[n]);
	}
	
	public static Shape deserialize(ObjectInputStream strm, int version) throws IOException, ClassNotFoundException
	{
		switch(version)
		{
			case 0:
			{
				Shape s = new Shape();
				s.type = strm.readInt();
				s.label = strm.readUTF();
				if(s.label.equals("<null>"))
					s.label = null;
				s.shape = new Vector2[strm.readInt()];
				for(int n = 0; n < s.shape.length; n++)
					s.shape[n] = (Vector2)strm.readObject();
				
				return s;
			}
			case 1:
			case 2:
			case 3:
			{
				Shape s = new Shape();
				s.type = strm.readInt();
				s.label = strm.readUTF();
				if(s.label.equals("<null>"))
					s.label = null;
				s.properties = strm.readUTF();
				if(s.properties.equals("<null>"))
					s.properties = null;
				s.shape = new Vector2[strm.readInt()];
				for(int n = 0; n < s.shape.length; n++)
					s.shape[n] = (Vector2)strm.readObject();
				
				return s;
			}
			default: throw new IOException("unknown file version: " + version);
		}
	}
	
	public void serialize(ObjectOutputStream strm) throws IOException
	{
		if(type != TYPE_CHAIN && type != TYPE_LOOP)
			type = TYPE_LOOP;
		
		strm.writeInt(type);
		strm.writeUTF(label == null ? "<null>" : label);
		strm.writeUTF(properties == null ? "<null>" : properties);
		strm.writeInt(shape.length);
		for(int n = 0; n < shape.length; n++)
			strm.writeObject(shape[n]);
	}
	
	public Shape clone()
	{
		Shape clone = new Shape();
		clone.label = label;
		clone.properties = properties;
		clone.shape = new Vector2[shape.length];
		for(int n = 0; n < shape.length; n++)
			clone.shape[n] = new Vector2(shape[n]);
		clone.type = type;
		
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
