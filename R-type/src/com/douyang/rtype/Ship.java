package com.douyang.rtype;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * @author yinglong
 * 
 *         The player controlled ship. Responds to key presses (up, down, left,
 *         right movement, and spacebar to fire missiles)
 * 
 */
public class Ship extends GameObject {

	private static final int SHIP_SIZE = 20;

	private String shipRes = "craft.png";

	// delta variables, set to -1,0,or 1 depending on arrow key pressed
	private int dx;
	private int dy;

	// missiles that are fired are stored here until collision or off-screen
	private ArrayList<Missile> missiles;

	private boolean notHeld;

	/**
	 * @param x
	 *            x-position of ship
	 * @param y
	 *            y-position of ship
	 * 
	 *            Initializes the ship and an array of missiles that the ship
	 *            will fire
	 */
	public Ship(int x, int y) {
		super(x, y);
		ImageIcon ii = new ImageIcon(this.getClass().getResource(shipRes));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		visible = true;

		notHeld = true;
		missiles = new ArrayList<Missile>();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.douyang.rtype.GameObject#move()
	 * 
	 * Add the current deltas for movement, and stop the ship from moving out of
	 * bounds
	 */
	public void move() {

		x += dx;
		y += dy;

		if (x <= 0)
			x = 1;
		if (x >= Rtype.BOARD_WIDTH - SHIP_SIZE)
			x = Rtype.BOARD_WIDTH - SHIP_SIZE - 1;
		if (y <= 0)
			y = 1;
		// if (y >= Rtype.BOARD_WIDTH - SHIP_SIZE)
		// y = Rtype.BOARD_WIDTH - SHIP_SIZE -1;
	}

	public ArrayList<Missile> getMissiles() {
		return missiles;
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {
		case KeyEvent.VK_LEFT:
			dx = -1;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 1;
			break;
		case KeyEvent.VK_UP:
			dy = -1;
			break;
		case KeyEvent.VK_DOWN:
			dy = 1;
			break;
		case KeyEvent.VK_SPACE:
			if(notHeld){
				fire();
				notHeld = false;
			}
			break;
		}
	}

	// FIRE ZE MISSILES
	public void fire() {
		missiles.add(new Missile(x + SHIP_SIZE, y + SHIP_SIZE / 2));

	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {
		case KeyEvent.VK_LEFT:
			dx = 0;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 0;
			break;
		case KeyEvent.VK_UP:
			dy = 0;
			break;
		case KeyEvent.VK_DOWN:
			dy = 0;
			break;
		case KeyEvent.VK_SPACE:
			notHeld = true;
			break;
		}
	}

}
