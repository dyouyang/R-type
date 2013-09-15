/**
 * 
 */
package com.douyang.rtype;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * @author yinglong
 * 
 */
public class Missile extends GameObject {

	private final int MISSILE_SPEED = 2;

	/**
	 * 
	 */
	public Missile(int x, int y) {
		super(x, y);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("missile.png"));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);

		visible = true;

	}

	public void move() {
		x += MISSILE_SPEED;
		if (x >= Rtype.BOARD_WIDTH)
			visible = false;
	}

}
