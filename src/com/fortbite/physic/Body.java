package com.fortbite.physic;

import java.awt.Rectangle;

public class Body {
	// Body States
	private boolean jumping=false;
	private boolean falling=false;
	private boolean walking=false;
	
	// Gravity
	protected double gravity=0.38;
	
	// Velocity
	private double velX, velY; 
	
	// Position
	protected double x, y;
	
	// Floor Position
	private int floorY;
	
	// Size
	protected int width, height;
	
	// Hitbox
	private Rectangle hitbox;
	
	public boolean colliding = false;
	
	// Updates the body location
	public void updateLocation() {
		if ( jumping && velY <= 0 ){
            jumping = false;
            falling = true;
        }
        else if( jumping ){
            velY = velY - gravity;
            y = y - velY;
        }

        if( falling ){
            if ( y < floorY ) {
            	y = y + velY;
            	velY = velY + gravity;
            } else {
            	jumping=false;
            	falling=false;
            }
        }

        x = x + velX;
	}

	public boolean isColliding( Body body ) {
		colliding = isHitboxColliding( body.hitbox );
		
		return colliding;
	}
	
	public boolean isHitboxColliding( Rectangle hitbox ) {
		return this.hitbox.intersects( hitbox );
	}
	
	public void setHitbox(int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height );
	}
	
	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		if ( !isFalling() )
			this.jumping = jumping;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public int getFloorY() {
		return floorY;
	}

	public void setFloorY(int floorY) {
		this.floorY = floorY;
	}

	public boolean isWalking() {
		return walking;
	}

	public void setWalking(boolean walking) {
		this.walking = walking;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	
}
