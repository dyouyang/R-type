/**
 * 
 */
package com.douyang.rtype;

import javax.swing.ImageIcon;

/**
 * @author yinglong Missiles are fired from the player ship and move right
 */
public class Missile extends GameObject {

	private final int MISSILE_SPEED = 2;

	/**
	 * Generate a new missile
	 */
	public Missile(int x, int y) {
		super(x, y);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("missile.png"));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);

		visible = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.douyang.rtype.GameObject#move()
	 * 
	 * Missiles will move right until they are off screen (or collide with an
	 * enemy)
	 */
	public void move() {
		x += MISSILE_SPEED;
		if (x >= Rtype.BOARD_WIDTH)
			visible = false;
	}

}
