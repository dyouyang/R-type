package com.douyang.rtype;

import javax.swing.ImageIcon;

/**
 * @author yinglong
 * 
 *         Enemies will spawn and travel left towards the player. They can be
 *         destroyed by missiles, and will destroy the player if collided
 */
public class Enemy extends GameObject {

	private String enemyRes = "alien.png";

	public Enemy(int x, int y) {
		super(x, y);
		ImageIcon ii = new ImageIcon(this.getClass().getResource(enemyRes));
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
	 * Enemies that are not destroyed will move back to the right of the board
	 * to attack the player again
	 */
	public void move() {
		if (x < 0)
			x = Rtype.BOARD_WIDTH;
		x -= 1;
	}

}
