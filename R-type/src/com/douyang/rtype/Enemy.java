package com.douyang.rtype;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

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

	public void move() {
		if (x < 0)
			x = Rtype.BOARD_WIDTH;
		x -= 1;
	}

}
