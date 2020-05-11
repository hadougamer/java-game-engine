package com.fortbite.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.fortbite.main.Camera;
import com.fortbite.images.Sprite;
import com.fortbite.images.Tile;

public class Level {

	private BufferedImage map;
	private String mapPath;
	private String spritePath;
	
	private int number;
	
	protected ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public Level( int number ) {
		this.spritePath = "/sprite01.png";
		this.mapPath = "/map05.png";
		
		loadMap(this.mapPath);
		
		scanMap();
		
		this.number = number;
	}

	// Scan Map Pixels
	private void scanMap() {
		// Load Map Pixels to array
		int[] imgPixels = new int[ map.getWidth() * map.getHeight() ];
		
		map.getRGB(
				0, 
				0, 
				map.getWidth(), 
				map.getHeight(), 
				imgPixels, 
				0, 
				map.getWidth()
		);
		
		int pixelPos=0, posX = 0, posY = 0;
		
		for ( int w=0; w<map.getWidth(); w++) {
			for ( int h=0; h<map.getHeight(); h++) {
				//System.out.println("X: " + posX + " - Y: " + posY);
				
				String tileType = "sky";
				switch( imgPixels[pixelPos] ) {
					case 0xFFFFFFFF:
						tileType = "sky";
					break;
					case 0xFF000000:
						tileType = "wall";
					break;
					case 0xFF49ff00:
						tileType = "floor";
					break;
					case 0xFF0058ff:
						Game.hero.setX(posX);
						Game.hero.setY(posY);
						
						// Sets the hero floor (physic)
						Game.hero.setFloorY(posY);
					break;
				}
				
				tiles.add(
					new Tile(
							spritePath, 
							posX,
							posY,
							16,
							16,
							tileType
						)
					);
				
				pixelPos++;
				posX += 16;
				
				if ( posX >= (map.getWidth()*16) )
					posX=0;				
			}
			posY += 16;
			if ( posY >= (map.getHeight()*16) )
				posY=0;
		}
	}
	
	public BufferedImage getMap() {
		return map;
	}
	
	public int getNumber() {
		return number;
	}
	
	private void loadMap( String mapPath ) {
		Sprite sprite = new Sprite( mapPath );
		map = sprite.getImage();
	}
	
	public void render( Graphics g ) {
		for( int i=0; i<tiles.size(); i++ ) {
			g.drawImage(
					tiles.get(i).getTile(), 
					(tiles.get(i).getX() - Camera.x), 
					(tiles.get(i).getY() - Camera.y), 
					null
				);

		}
	}
}
