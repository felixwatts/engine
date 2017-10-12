package com.monkeysonnet.engine.editor;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Map
{
	private Point[] _points;
	private Shape[] _shapes;	
	private Graphic[] _gfxBg;
	private Graphic[] _gfxFg;
	private float _scale;
	private float _rotation;
	
	protected Map()
	{
		this(null);
	}
	
	public Map(String filename)
	{
		this(filename, 1f, 0f, true);
	}
	
	public Map(String filename, float scale, float rotation)
	{
		this(filename, scale, rotation, true);
	}
	
	public Map(String filename, float scale, float rotation, boolean internal)
	{
		_scale = scale;
		_rotation = rotation;
		
		if(filename != null)
		{
			FileHandle f = internal ? Gdx.files.internal(filename) :  Gdx.files.absolute(filename);
			if(f.exists())
			{
				try
				{
					deserialize(new ObjectInputStream(f.read()));
				} catch (IOException e)
				{
					e.printStackTrace();
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				_points = new Point[0];
				_shapes = new Shape[0];
				_gfxFg = new Graphic[0];
				_gfxBg = new Graphic[0];
			}
		}
		else
		{
			_points = new Point[0];
			_shapes = new Shape[0];
			_gfxFg = new Graphic[0];
			_gfxBg = new Graphic[0];
		}
	}

	private void deserialize(ObjectInputStream strm) throws IOException, ClassNotFoundException
	{
		while(true)
		{
			try
			{
				String sectionType = strm.readUTF();
				int version = strm.readInt();
				deserializeSection(sectionType, version, strm);
			}
			catch(EOFException ex)
			{
				break;
			}
		}
	}

	protected void deserializeSection(String sectionType, int version, ObjectInputStream strm) throws IOException, ClassNotFoundException
	{
		if(sectionType.equals("points"))
		{
			int num = strm.readInt();
			_points = new Point[num];
			for(int n = 0; n < num; n++)
			{
				_points[n] = Point.deserialize(strm, version);
				_points[n].point.mul(_scale).rotate(_rotation);
			}
			
			strm.readObject(); // end marker
		}
		else if(sectionType.equals("shapes"))
		{
			int num = strm.readInt();
			_shapes = new Shape[num];
			for(int n = 0; n < num; n++)
			{
				_shapes[n] = Shape.deserialize(strm, version);
				for(int x = 0; x < _shapes[n].shape.length; x++)
					_shapes[n].shape[x].mul(_scale).rotate(_rotation);
			}
			
			strm.readObject(); // end marker
		}
		else if(sectionType.equals("gfx-fg"))
		{
			int num = strm.readInt();
			_gfxFg = new Graphic[num];
			for(int n = 0; n < num; n++)
			{
				_gfxFg[n] = Graphic.deserialize(strm, version);
				
				_gfxFg[n].bottom *= _scale;
				_gfxFg[n].top *= _scale;
				_gfxFg[n].left *= _scale;
				_gfxFg[n].right *= _scale;
				_gfxFg[n].tileSize *= _scale;
			}
			
			strm.readObject();
		}
		else if(sectionType.equals("gfx-bg"))
		{
			int num = strm.readInt();
			_gfxBg = new Graphic[num];
			for(int n = 0; n < num; n++)
			{
				_gfxBg[n] = Graphic.deserialize(strm, version);
				
				_gfxBg[n].bottom *= _scale;
				_gfxBg[n].top *= _scale;
				_gfxBg[n].left *= _scale;
				_gfxBg[n].right *= _scale;
				_gfxBg[n].tileSize *= _scale;
			}
			
			strm.readObject();
		}
		else
		{
			while(!(strm.readObject() instanceof SectionEndMarker)){}
		}
	}
	
	public Point point(String label)
	{
		if(_points == null)
			return null;
		for(int n = 0; n < _points.length; n++)
			if(_points[n].label != null && _points[n].label.equals(label))
				return _points[n];
		return null;
	}
	
	public Shape shape(String label)
	{
		if(_shapes == null)
			return null;
		for(int n = 0; n < _shapes.length; n++)
			if(_shapes[n].label != null && _shapes[n].label.equals(label))
				return _shapes[n];
		return null;
	}
	
	public Shape shape(int n)
	{
		return _shapes[n];
	}
	
	public Shape[] shapes()
	{
		return _shapes;
	}
	
	public int numShapes()
	{
		return _shapes.length;
	}
	
	public Point point(int n)
	{
		return _points[n];
	}
	
	public Point[] points()
	{
		return _points;
	}
	
	public int numPoints()
	{
		return _points.length;
	}
	
	public Graphic[] gfxFg()
	{
		return _gfxFg;
	}
	
	public Graphic[] gfxBg()
	{
		return _gfxBg;
	}
	
	public void scale(float factor)
	{
		float relScale = (1f/_scale) * factor;
		_scale = factor;
		
		for(int n = 0; n < _points.length; n++)
		{
			_points[n].point.mul(relScale);			
		}
		
		for(int n = 0; n < _shapes.length; n++)
		{
			Shape sh = _shapes[n];
			for(int p = 0; p < sh.shape.length; p++)
			{
				sh.shape[p].mul(relScale);
			}		
		}
		
		for(int n = 0; n < _gfxBg.length; n++)
		{
			Graphic g = _gfxBg[n];
			
			g.left *= relScale;
			g.bottom *= relScale;
			g.right *= relScale;
			g.top *= relScale;
		}
		
		for(int n = 0; n < _gfxFg.length; n++)
		{
			Graphic g = _gfxFg[n];
			
			g.left *= relScale;
			g.bottom *= relScale;
			g.right *= relScale;
			g.top *= relScale;
		}
	}
}
