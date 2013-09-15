package com.douyang.rtype;

import javax.swing.JFrame;

/**
 * @author yinglong
 * Main class, sets up game frame
 */
public class Rtype extends JFrame{

	static final int BOARD_WIDTH = 400;
	static final int BOARD_HEIGHT = 400;
	
	/**
	 * Game constructor
	 * 
	 * Add a new game board and game window settings
	 */
	public Rtype()
	{
		add(new Board());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(BOARD_WIDTH, BOARD_HEIGHT);
		setLocationRelativeTo(null);
		setTitle("R-type");
		setResizable(false);
		setVisible(true);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Rtype();

	}

}
