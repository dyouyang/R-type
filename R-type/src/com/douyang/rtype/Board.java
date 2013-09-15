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

/**
 * @author yinglong Game board class
 * 
 *         Paints the game world and game objects. Also handles game status and
 *         collision logic.
 */
public class Board extends JPanel implements ActionListener {

	private Ship ship;
	private Timer timer;
	private ArrayList<Enemy> enemies;
	private boolean inGame;

	/**
	 * Initializes all the game objects
	 * 
	 * Ship - the player controlled space ship Enemies - enemy targets randomly
	 * spawned Starts the timer to update the game world
	 */
	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);

		setSize(Rtype.BOARD_WIDTH, Rtype.BOARD_HEIGHT);
		inGame = true;
		ship = new Ship(40, 60);
		initEnemies();
		timer = new Timer(5, this);
		timer.start();

	}

	/**
	 * Generates a random initial array of enemies within the game window's
	 * height, but to the right of the game window. Enemies travel left into the
	 * player's view.
	 */
	private void initEnemies() {
		enemies = new ArrayList<Enemy>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			int spawnX = rand.nextInt(2000) + Rtype.BOARD_WIDTH;
			int spawnY = rand.nextInt(Rtype.BOARD_HEIGHT - 40) + 20;
			enemies.add(new Enemy(spawnX, spawnY));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 * 
	 * Main drawing function to draw all game objects onto the scene.
	 */
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		ArrayList<Missile> missiles = ship.getMissiles();

		// draw player ship
		g2d.drawImage(ship.getImage(), ship.getX(), ship.getY(), this);

		// draw all missiles
		for (Missile missile : missiles) {
			g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(),
					this);
		}

		// draw all enemy ships
		for (Enemy enemy : enemies) {
			if (enemy.isVisible()) {
				g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(),
						this);
			}
		}

		// Count down still standing enemies
		g2d.setColor(Color.WHITE);
		g2d.drawString("Enemies left: " + enemies.size(), 10, 10);

		// if the game is over, stop drawing and display game over
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 * Update all game logic. Either move the game objects in their respective
	 * directions or remove objects that have recently collided. Then, check for
	 * new collisions after the moves and redraw the scene.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ship.move();

		ArrayList<Missile> missiles = ship.getMissiles();
		for (int i = 0; i < missiles.size(); i++) {

			// visible missiles haven't collided before, so they can move
			if (missiles.get(i).isVisible()) {
				missiles.get(i).move();
			} else {
				// missiles that are set invisible because of collision are
				// removed
				missiles.remove(i);
			}
		}

		// enemies follow the same logic as missiles above
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

	/**
	 * Update game objects if they collide. Missile and enemies will mutual
	 * destruct, but enemies and the player ship will end the game.
	 */
	private void checkCollisions() {
		Rectangle shipBounds = ship.getBounds();

		// Check if any enemies have collided with the player ship, and if so
		// end the game
		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			Rectangle enemyBounds = enemy.getBounds();
			if (enemyBounds.intersects(shipBounds)) {
				inGame = false;
			}
		}

		ArrayList<Missile> missiles = ship.getMissiles();

		// check if any missiles have collided with any enemies.
		for (int i = 0; i < missiles.size(); i++) {
			Missile missile = missiles.get(i);

			Rectangle missileBounds = missile.getBounds();

			for (int j = 0; j < enemies.size(); j++) {
				Rectangle enemyBounds = enemies.get(j).getBounds();

				// The collided ones are set to invisible to be later deleted.
				if (enemyBounds.intersects(missileBounds)) {
					missile.setVisible(false);
					enemies.get(j).setVisible(false);
				}
			}
		}

	}

	/**
	 * @author yinglong
	 * 
	 *         Send key press events to control the player ship
	 */
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
