package com.douyang.rtype;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {

	private String enemyRes = "alien.png";
	private int x, y, width, height;
	private boolean visible;
	private Image image;

	public Enemy(int x, int y) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(enemyRes));
		image = ii.getImage();

		width = image.getWidth(null);
		height = image.getHeight(null);

		visible = true;
		this.x = x;
		this.y = y;

	}

	public Image getImage() {
		return image;
	}

	public void move() {
		if (x < 0)
			x = Rtype.BOARD_WIDTH;
		x -= 1;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
