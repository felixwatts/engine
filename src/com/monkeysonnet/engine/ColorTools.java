package com.monkeysonnet.engine;

import com.badlogic.gdx.graphics.Color;

public class ColorTools
{
	private static final Color _workingColor = new Color();
	
	public static Color parse(String str)
	{
		if(str.startsWith("#"))
		{
			if(str.length() != 9)
				return null;
		
			String r = str.substring(1, 3);
			String g = str.substring(3, 5);
			String b = str.substring(5, 7);
			String a = str.substring(7, 9);
			
			try
			{
				_workingColor.set(
						((float)Integer.parseInt(r, 16))/255f,
						((float)Integer.parseInt(g, 16))/255f,
						((float)Integer.parseInt(b, 16))/255f,
						((float)Integer.parseInt(a, 16))/255f);
				
				return _workingColor;
			}
			catch(NumberFormatException ex)
			{
				return null;
			}
		}
		else return null;
	}
	
	public static Color desaturate(Color c, float amt)
	{
		if(c == null)
			return null;
		
		if(amt == 0)
			return c;
		else
		{
			float l = toLuminosity(c);
			
			_workingColor.set(
					(((1 - amt) * c.r) + (amt * l)), 
					(((1 - amt) * c.g) + (amt * l)), 
					(((1 - amt) * c.b) + (amt * l)),  
					c.a);
			
			return _workingColor;
		}
	}
	
	public static Color darken(Color c)
	{
		if(c == null)
			return null;
		else
		{
			_workingColor.set(
					c.r * 0.2f, 
					c.g * 0.2f, 
					c.b * 0.2f,  
					c.a);
			
			return _workingColor;
		}
	}
	
	public static Color tint(Color c, Color tint, float amt)
	{
		if(c == null)
			return null;
		
		if(amt == 0)
			return c;
		else
		{
			float l = toLuminosity(c);
			
			_workingColor.set(
					(((1 - amt) * c.r) + (amt * l * tint.r)), 
					(((1 - amt) * c.g) + (amt * l * tint.g)), 
					(((1 - amt) * c.b) + (amt * l * tint.b)),  
					c.a);
			
			return _workingColor;
		}
	}
	
	public static Color blend(Color cFrom, Color cTo, float amt)
	{
		_workingColor.set(
				cFrom.r + ((cTo.r - cFrom.r) * amt),
				cFrom.g + ((cTo.g - cFrom.g) * amt),
				cFrom.b + ((cTo.b - cFrom.b) * amt),
				cFrom.a + ((cTo.a - cFrom.a) * amt));
		
		return _workingColor;
	}
	
	public static float toLuminosity(Color c)
	{
		return  0.21f * c.r + 0.71f * c.g + 0.07f * c.b;
	}
	
	public static Color combineAlpha(Color c, float parentAlpha)
	{
		_workingColor.set(c);
		_workingColor.a *= parentAlpha;
		return _workingColor;
	}
	
	public static Color hslToColor(float h, float s, float l, float a)
	{
		float r, g, b;
		if(s == 0)
		{
	        r = g = b = l; // achromatic
	    }else
	    {
	    	float q = l < 0.5f ? l * (1f + s) : l + s - l * s;
	    	float p = 2f * l - q;
	        r = hueToRgb(p, q, h + 1f/3f);
	        g = hueToRgb(p, q, h);
	        b = hueToRgb(p, q, h - 1f/3f);
	    }

	    _workingColor.set(r, g, b, a);
	    
	    return _workingColor;
	}
	
	private static float hueToRgb(float p, float q, float t)
    {
        if(t < 0f) t += 1f;
        if(t > 1f) t -= 1f;
        if(t < 1f/6f) return p + (q - p) * 6f * t;
        if(t < 1f/2f) return q;
        if(t < 2f/3f) return p + (q - p) * (2f/3f - t) * 6f;
        return p;
    }
	
	public static Color rgbToHsl(float r, float g, float b)
	{
	    float max = Math.max(Math.max(r, g), b), min = Math.min(Math.min(r, g), b);
	    float h, s, l = (max + min) / 2;

	    if(max == min)
	    {
	        h = s = 0; // achromatic
	    }else
	    {
	        float d = max - min;
	        s = l > 0.5f ? d / (2f - max - min) : d / (max + min);
	        
	        if(max == r)
	        	h = (g - b) / d + (g < b ? 6f : 0f);
	        else if(max == g)
	        	h = (b - r) / d + 2f;
	        else
	        	h = (r - g) / d + 4f;

	        h /= 6f;
	    }
	    
	    _workingColor.set(h, s, l, 1);

	    return _workingColor;
	}
}
