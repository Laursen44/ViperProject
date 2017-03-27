package GameEngine;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject
{
	Graphics g;
	private int damage;
	private int health = 100;
	private String name;
	
	public Player(String name, Vector2D vec, ID id, Graphics g)
	{
		super(vec, id);
		this.name = name;
		this.g = g;
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)vec.getX(),(int)vec.getY(),100,100);
		g.setColor(Color.WHITE);
		g.drawString(this.name, (int)vec.getX(), (int)vec.getY());
	}
}
