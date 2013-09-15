package com.douyang.rtype;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private Ship ship;
	private Timer timer;
	private ArrayList<Enemy> enemies;
	private boolean inGame;

	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);

		setSize(Rtype.BOARD_WIDTH, Rtype.BOARD_HEIGHT);
		inGame = true;
		ship = new Ship();
		initEnemies();
		timer = new Timer(5, this);
		timer.start();

	}

	private void initEnemies() {
		enemies = new ArrayList<Enemy>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			int spawnX = rand.nextInt(2000) + Rtype.BOARD_WIDTH;
			int spawnY = rand.nextInt(Rtype.BOARD_HEIGHT-40) + 20;
			enemies.add(new Enemy(spawnX, spawnY));
		}

	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		ArrayList<Missile> missiles = ship.getMissiles();

		g2d.drawImage(ship.getImage(), ship.getX(), ship.getY(), this);
		for (Missile missile : missiles) {
			g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(),
					this);
		}

		for (Enemy enemy : enemies) {
			if (enemy.isVisible()) {
				g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(),
						this);
			}
		}
		g2d.setColor(Color.WHITE);
		g2d.drawString("Enemies left: " + enemies.size(), 10, 10);

		if (!inGame) {
			timer.stop();
			String gameOver = "Game Over";
			g.setColor(Color.WHITE);
			g.drawString(gameOver, Rtype.BOARD_WIDTH / 2,
					Rtype.BOARD_HEIGHT / 2);
		}
		g.dispose();

	}

	@Override
	public void addNotify() {
		super.addNotify();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ship.move();

		ArrayList<Missile> missiles = ship.getMissiles();
		for (int i = 0; i < missiles.size(); i++) {
			if (missiles.get(i).isVisible()) {
				missiles.get(i).move();
			} else {
				missiles.remove(i);
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).isVisible()) {
				enemies.get(i).move();
			} else {
				enemies.remove(i);
			}
		}
		checkCollisions();

		repaint();

	}

	private void checkCollisions() {
		Rectangle shipBounds = ship.getBounds();

		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			Rectangle enemyBounds = enemy.getBounds();
			if (enemyBounds.intersects(shipBounds)) {
				// setvisibles for ship, enemy TODO
				inGame = false;
			}
		}

		ArrayList<Missile> missiles = ship.getMissiles();
		for (int i = 0; i < missiles.size(); i++) {
			Missile missile = missiles.get(i);

			Rectangle missileBounds = missile.getBounds();

			for (int j = 0; j < enemies.size(); j++) {
				Rectangle enemyBounds = enemies.get(j).getBounds();

				if (enemyBounds.intersects(missileBounds)) {
					missile.setVisible(false);
					enemies.get(j).setVisible(false);
				}
			}
		}

	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			ship.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			ship.keyReleased(e);
		}

	}
}
