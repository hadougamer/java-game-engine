package com.fortbite.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.fortbite.images.Sprite;
import com.fortbite.physic.Body;

public class Entity extends Body {
	
	// Sprite
	protected Sprite sprite;
	
	// Current frame index
	protected int frameNumber = 0;
	
	// Frame frequence control
	private int frameControl = 0;
	private int maxFrameControl = 4;
	
	// Lista of frames
	protected ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	
	protected void setSprite( String path ) {
		this.sprite = new Sprite( path );
	}
	
	public void setX( int x) {
		this.x = x;
	}
	
	public void setY( int y) {
		this.y = y;
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public void setWidth( int width) {
		this.width = width;
	}
	
	public void setHeight( int height) {
		this.height = height;
	}
	
	public void setFrameNumber( int frameNumber ) {
		this.frameNumber = frameNumber;
	}
	
	public BufferedImage getFrame() {	
		int maxFrame = ( frames.size() - 1 );
		
		if( frameNumber > maxFrame ) {
			frameNumber = 0;
		}
		
		BufferedImage frame = frames.get(frameNumber);
		
		frameControl++;
		if ( frameControl == maxFrameControl ) {
			frameControl = 0;
			frameNumber++;	
		}
		
		return frame;
	}
	
	public void doAction( String action ) {
		//Need to be implemented by child
	}
	
	public void render( Graphics g ) {
		g.drawImage(
			getFrame(), 
			(int) getX(), 
			(int) getY(), 
			null
		);
	}
	
	protected void cleanFrames() {
		frames.clear();
	}
}
