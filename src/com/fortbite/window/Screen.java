package com.fortbite.window;

import javax.swing.JFrame;

import com.fortbite.main.Game;

public class Screen extends JFrame {
	// JFrame ID
	private static final long serialVersionUID = 1L;

	private static final boolean RESIZABLE = false;
	
	// Configs
	public Screen ( String title ) {
		super( title );
		this.setResizable( RESIZABLE );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// Loads the Game Window
	public void load( Game game ) {
		this.add(game); 
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
