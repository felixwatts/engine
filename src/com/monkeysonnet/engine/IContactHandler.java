package com.monkeysonnet.engine;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;

public interface IContactHandler
{
	void onBeginContact(Contact c, Fixture me, Fixture other);
	void onEndContact(Contact c, Fixture me, Fixture other);
	void postSolve(Contact c, ContactImpulse impulse, Fixture me, Fixture other);
}
