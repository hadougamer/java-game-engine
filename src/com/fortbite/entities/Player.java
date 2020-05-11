package com.fortbite.entities;

import java.awt.Graphics;

import com.fortbite.main.Camera;
import com.fortbite.main.Game;

public class Player extends Entity {
	public double speed = 3;
	public String action;
	private String spritePath = "/sprite01.png";
	
	public static boolean dirRight = true;
	
	public Player(int x, int y) {
		setVelY(5); // Jump velocity
		setSprite(spritePath);
		setX(x);
		setY(y);
		setWidth(16);
		setWidth(16);
		
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
				updateLocation();
			break;			
			case "walk-left":
				dirRight = false;
				setVelX(-speed);
				updateLocation();
			break;
			case "stop":
				if ( !isJumping() && !isFalling() ) {
					setVelX(0);
				}
				updateLocation();
			break;
		}
		loadFrames(action);
		Camera.x = (int) (x - (Game.width/2));
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
			(int) (getX() - Camera.x), 
			(int) (getY() - Camera.y), 
			null
		);
	}
}
