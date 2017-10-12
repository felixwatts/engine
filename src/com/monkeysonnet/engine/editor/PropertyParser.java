package com.monkeysonnet.engine.editor;

public class PropertyParser
{
	public static boolean hasProperty(String prop, String properties)
	{
		if(properties == null)
			return false;
		
		String[] parts = properties.split(";\\s*");
		for(int n = 0; n < parts.length; n++)
		{
			String[] subParts = parts[n].split(":");
			if(subParts[0].equals(prop))
				return true;
		}
		
		return false;
	}
	
	public static String propertyValue(String prop, String properties)
	{
		if(properties == null)
			return null;
		
		String[] parts = properties.split(";\\s*");
		for(int n = 0; n < parts.length; n++)
		{
			String[] subParts = parts[n].split("\\s*:\\s*");
			if(subParts[0].equals(prop))
				return subParts[1];
		}
		
		return null;
	}
}
