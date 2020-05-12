package com.fortbite.entities;

import java.awt.Graphics;
import com.fortbite.variables.Common;

public class Platform extends Entity {
	private String spritePath = Common.SPR_01;
	
	public Platform(int x, int y) {
		setType("platform");
		setSprite(spritePath);
		setX(x);
		setY(y);
		setWidth(16);
		setHeight(16);
		
		// Hitbox
		setHitbox(x, y, width, height);
		
		//Add the platform frame
		frames.clear();
		frames.add(sprite.getSprite(32, 32, 16, 16));
	}
	
	public void render( Graphics g ) {
		g.drawImage(
			getFrame(), 
			(int) getX(), 
			(int) getY(), 
			null
		);
	}
}
