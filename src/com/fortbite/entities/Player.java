package com.fortbite.entities;

import java.awt.Graphics;

import com.fortbite.variables.Common;

public class Player extends Entity {
	public double speed = 3;
	public String action;
	private String spritePath = Common.SPR_01;
	
	public static boolean dirRight = true;
	
	public Player(int x, int y) {
		setVelY(6);
		setType("player");
		setSprite(spritePath);
		setX(x);
		setY(y);
		setWidth(16);
		setHeight(16);
		
		// Hitbox
		setHitbox(x, y, width, height);
		
		// Default action
		loadFrames("stop");
	}
	
	public void doAction( String action ) {
		this.action = action;
		
		// Filter action
		switch( action ) {
			case "walk-right":
				dirRight = true;
				setVelX(speed);
			break;			
			case "walk-left":
				dirRight = false;
				setVelX(-speed);
			break;
			case "stop":
				setVelX(0);
			break;
		}

		//Camera.x = getX() - (Game.width/2);
		//Camera.y = getY() - (Game.height/2);
		updateLocation();
		setHitbox(getX(), getY(), getWidth(), getHeight());
		
		loadFrames(action);
	}
	
	private void loadFrames( String act ) {
		cleanFrames();
		// Filter action
		switch( act ) {
			case "walk-right":
				frames.add(sprite.getSprite(0, 0, 16, 16));
				frames.add(sprite.getSprite(16, 0, 16, 16));
				frames.add(sprite.getSprite(32, 0, 16, 16));
				frames.add(sprite.getSprite(48, 0, 16, 16));
				frames.add(sprite.getSprite(62, 0, 16, 16));
				frames.add(sprite.getSprite(78, 0, 16, 16));
			break;
			case "walk-left":
				frames.add(sprite.getSprite(0, 16, 16, 16));
				frames.add(sprite.getSprite(16, 16, 16, 16));
				frames.add(sprite.getSprite(32, 16, 16, 16));
				frames.add(sprite.getSprite(48, 16, 16, 16));
				frames.add(sprite.getSprite(62, 16, 16, 16));
				frames.add(sprite.getSprite(78, 16, 16, 16));
			break;
			default:
				if( dirRight ) {
					frames.add(sprite.getSprite(32, 0, 16, 16));	
				} else {
					frames.add(sprite.getSprite(32, 16, 16, 16));
				}
			break;	
		}
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
