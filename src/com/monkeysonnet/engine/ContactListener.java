package com.monkeysonnet.engine;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListener implements
		com.badlogic.gdx.physics.box2d.ContactListener
{
	@Override
	public void beginContact(Contact contact)
	{
		if(contact.getFixtureA().getUserData() != null)
		{
			FixtureTag tag = (FixtureTag)contact.getFixtureA().getUserData();
			if(tag.contactHandler != null)
				tag.contactHandler.onBeginContact(contact, contact.getFixtureA(), contact.getFixtureB());
		}
		
		if(contact.getFixtureB().getUserData() != null)
		{
			FixtureTag tag = (FixtureTag)contact.getFixtureB().getUserData();
			if(tag.contactHandler != null)
				tag.contactHandler.onBeginContact(contact, contact.getFixtureB(), contact.getFixtureA());
		}
	}
	
	@Override
	public void endContact(Contact contact)
	{	
		if(contact.getFixtureA().getUserData() != null)
		{
			FixtureTag tag = (FixtureTag)contact.getFixtureA().getUserData();
			if(tag.contactHandler != null)
				tag.contactHandler.onEndContact(contact, contact.getFixtureA(), contact.getFixtureB());
		}
		
		if(contact.getFixtureB().getUserData() != null)
		{
			FixtureTag tag = (FixtureTag)contact.getFixtureB().getUserData();
			if(tag.contactHandler != null)
				tag.contactHandler.onEndContact(contact, contact.getFixtureB(), contact.getFixtureA());
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold)
	{
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse)
	{
		if(contact.getFixtureA().getUserData() != null)
		{
			FixtureTag tag = (FixtureTag)contact.getFixtureA().getUserData();
			if(tag.contactHandler != null)
				tag.contactHandler.postSolve(contact, impulse, contact.getFixtureA(), contact.getFixtureB());
		}
		
		if(contact.getFixtureB().getUserData() != null)
		{
			FixtureTag tag = (FixtureTag)contact.getFixtureB().getUserData();
			if(tag.contactHandler != null)
				tag.contactHandler.postSolve(contact, impulse, contact.getFixtureB(), contact.getFixtureA());
		}
	}
}
