package com.monkeysonnet.engine.editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.math.Vector2;

public class Point
{
	public String label;
	public String properties;
	public Vector2 point;
	
	public static void serialize(String label, String props, Vector2 point, ObjectOutputStream strm) throws IOException
	{
		strm.writeUTF(label == null ? "<null>" : label);
		strm.writeUTF(props == null ? "<null>" : props);
		strm.writeObject(point);
	}
	
	public static Point deserialize(ObjectInputStream strm, int version) throws IOException, ClassNotFoundException
	{
		Point p = new Point();
		
		switch(version)
		{
			case 1:
			case 0:
				p.label = strm.readUTF();
				if(p.label == "<null>")
					p.label = null;
				p.point = (Vector2)strm.readObject();
				break;
			case 2:
			case 3:
				p.label = strm.readUTF();
				if(p.label != null && p.label.equals("<null>"))
					p.label = null;
				p.properties = strm.readUTF();
				if(p.properties != null && p.properties.equals("<null>"))
					p.properties = null;
				p.point = (Vector2)strm.readObject();
				break;
			default:
				throw new IOException("unknown file version: " + version);
		}
		
		return p;
	}
	
	public void serialize(ObjectOutputStream strm) throws IOException
	{
		strm.writeUTF(label == null ? "<null>" : label);
		strm.writeUTF(properties == null ? "<null>" : properties);
		strm.writeObject(point);
	}
	
	public Point clone()
	{
		Point clone = new Point();
		clone.point = new Vector2(point);
		clone.properties = properties;
		clone.label = label;
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
