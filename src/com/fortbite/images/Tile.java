package com.fortbite.images;

import java.awt.image.BufferedImage;

public class Tile {
	
	private String type;
	private int x, y, width, height;
	
	private BufferedImage tileFloor, tileSky, tileWall;
	
	public Tile( String path, int x, int y, int width, int height, String type) {
		// Sets props
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setType(type);
		
		Sprite sprite = new Sprite(path);
		
		tileWall   = sprite.getSprite(0, 32, getWidth(), getHeight()); 
		tileSky    = sprite.getSprite(16, 32, getWidth(), getHeight());
		tileFloor  = sprite.getSprite(32, 32, getWidth(), getHeight());
	}

	public BufferedImage getTile() {
		switch( type ) {
			case "wall":
				return tileWall;
			case "sky":
				return tileSky;
			default:
			case "floor":
				return tileFloor;
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
