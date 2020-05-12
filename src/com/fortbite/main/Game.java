package com.fortbite.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.fortbite.entities.Entity;
import com.fortbite.entities.Player;
import com.fortbite.window.Screen;

public class Game extends Canvas implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	private String title = "Fortbite Adventures";
	private Screen screen;
	
	public static int width = 320;
	public static int height = 240;
	public static int scale = 3;
	
	// State configs
	private double FPS = 60.0;
	private boolean DEBUG = false;
	private Thread thread;
	private boolean isRunning;
	
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	// Game rendered image
	private BufferedImage image;
	
	// Hero Action
	public String heroAction = "stop";
	
	// Level
	public int levelNumber = 1;
	public Level level;
	public static Player hero;
	
	// Game constructor
	public Game() {
		// Running control
		isRunning = true;
		
		setPreferredSize(new Dimension( this.getWidth(), this.getHeight()));
		screen = new Screen(title);
		screen.load(this);
		
		// Add player
		hero = new Player(0, (height-32));
		this.addEntity( hero );

		// Loads the Level
		level = new Level(levelNumber);
		
		image = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);
		
		// Starts Key Listening
		this.addKeyListener(this);
	}
	
	public void tick() {
	}
	
	public void render() {
		// Image Buffer Strategy
		BufferStrategy bs = this.getBufferStrategy();
		if ( bs == null ) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		
		// Default background
		g.setColor(new Color(100, 100, 255));
		g.fillRect(0, 0, width, height);
		
		// Render the Level
		level.render(g);
		
		for( int i=0; i<entities.size(); i++ ) {
			if ( entities.get(i) instanceof Player ) {
				entities.get(i).doAction( heroAction );
			}
			
			/*
			if ( entities.get(i) instanceof Platform ) {
				if ( entities.get(i).isColliding( Game.hero ) ) {
					System.out.println("Colliding!");
					Game.hero.setY(
							entities.get(i).getY() - entities.get(i).getHeight()
					);
				}
			}
			*/
			
			entities.get(i).render(g);
		}
		
		// Garbage collector
		g.dispose();
		
		// Draw to stage
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(),null);
		bs.show();
	}
	
	public static void main( String[] args ) {
		System.out.println("Lets Start FORTBITE!");
		
		// Initialize the game
		Game game = new Game();
		game.start();
	}

	// Starts the game
	public void start() {
		// Starts the game thread
		thread = new Thread(this);
		thread.start();
	}
	
	// Stops the game
	public void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
	/* KeyListener methods */
	public void keyPressed(KeyEvent e) {
		switch( e.getKeyCode() ) {
			case KeyEvent.VK_RIGHT:
				heroAction = "walk-right";
			break;
			case KeyEvent.VK_LEFT:
				heroAction = "walk-left";
			break;
		}
		
		// Hero can jumping stopped or walkink
		if ( e.getKeyCode() == KeyEvent.VK_SPACE ) {
			Game.hero.setJumping(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// On Key Up
		switch( e.getKeyCode() ) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_LEFT:
				heroAction = "stop";
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// On Key Typed
	}
	
	/* Runnable method */	
	public void run() {
		//FPS
		long lastTime 		 = System.nanoTime();
		double amountOfTicks = FPS;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while( isRunning ) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if ( delta >= 1 ) {
				tick();
				render();
				// Frame test
				frames++;
				delta--;
			}
			
			if ((System.currentTimeMillis()-timer) >= 1000 ) {
				showDebug("FPS: " + frames);
				frames = 0;
				timer+=1000;
			}
		}
		stop();
	}
	
	private void addEntity( Entity entity ) {
		entities.add(entity);
	}
	
	public int getWidth() {
		return (int) (width * scale);
	}
	
	public int getHeight() {
		return (int) (height * scale);
	}
	
	/* Debug */
	private void showDebug( String message ) {
		if ( DEBUG ) {
			System.out.println( message );	
		}
	}
	private void debugBox( Graphics g, Rectangle box) {
		Graphics2D g2 = (Graphics2D) g;
		g2.draw( box );
	}
}
