package GameEngine.Framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Player extends GameObject
{

	private static final long serialVersionUID = 1L;
	Graphics g;
	private int damage;
	private int health = 100;
	private String name;
	public Rectangle collisionRect;
	public int pW = 32, pH = 32;
	private boolean updateBounds = false;
	int pXX;
	int pYY;
	
	
	public Player(String name, Vector2D vec, Graphics g)
	{
		this.vec = vec;
		this.name = name;
		this.g = g;
		int pX = (int)vec.getX();
		int pY = (int)vec.getY();
		collisionRect = new Rectangle(pX, pY, pW, pH);
		collisionRect.setBounds(collisionRect);
		updateBounds = true;
		
	}
	
	public void move()
	{
		{
		vec = vec.add(new Vector2D ( 0 , getVelY() * 5 ));
		vec = vec.add(new Vector2D ( getVelX() * 5 , 0 ));
		}

	}
	
	public void collision()
	{
		LinkedList<Rectangle> blocks = Block.getLinkedList();
		
		for (Rectangle block : blocks)
			if (collisionRect.getBounds().intersects(block.getBounds()))
			{
				System.out.println("collision!");
				
			}
	}
	
	public void bounds(Rectangle rect)
	{
		collisionRect.setBounds(rect);

	}
	
	public Rectangle updateBounds()
	{
		pXX = (int)vec.getX();
		pYY = (int)vec.getY();
		collisionRect = new Rectangle(pXX, pYY, pW, pH);
		collisionRect.setBounds(collisionRect);
		return collisionRect;
	}
	
	public void update() 
	{		
		move();
		if(updateBounds){
			updateBounds();
			collision();
		}
	}

	public void render(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect((int)vec.getX(),(int)vec.getY(), 32, 32);
		g.setColor(Color.WHITE);
		g.drawString(this.name, (int)vec.getX(), (int)vec.getY());
		g.setColor(Color.RED);
		g.fillRect(30, 30, 200, 50);
		g.setColor(Color.GREEN);
		g.fillRect(30, 30, health*2, 50);
		g.setColor(Color.BLACK);
		g.drawString("HEALTH", 100, 60);
	}
	
	public int getDamage() 
	{
		return damage;
	}

	public void setDamage(int damage) 
	{
		this.damage = damage;
	}

	public int getHealth() 
	{
		return health;
	}

	public void setHealth(int health) 
	{
		this.health = health;
	}
	
}
