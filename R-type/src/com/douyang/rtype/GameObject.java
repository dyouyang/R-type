package com.douyang.rtype;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x, y, width, height;
	protected boolean visible;
	protected Image image;

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void move();
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Image getImage() {
		return image;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
