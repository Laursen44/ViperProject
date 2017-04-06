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
	public Rectangle collisionRectTop;
	public Rectangle collisionRectBot;
	public Rectangle collisionRectLeft;
	public Rectangle collisionRectRight;
	public int pW = 32, pH = 32;
	private boolean updateBounds = false;
	int pXX, pYY;
	int hDir, vDir;
	private KeyboardManager input;
	
	public Player(String name, Vector2D vec, Graphics g)
	{
		this.vec = vec;
		this.name = name;
		this.g = g;
		int pX = (int)vec.getX();
		int pY = (int)vec.getY();
		collisionRectTop = new Rectangle(pX + 6, pY, pW - 12, pH - 30);
		collisionRectTop.setBounds(collisionRectTop);
		collisionRectBot = new Rectangle(pX + 6, pY + 30, pW - 12, pH - 30);
		collisionRectBot.setBounds(collisionRectBot);
		collisionRectLeft = new Rectangle(pX, pY + 6 , pW - 30, pH - 12);
		collisionRectLeft.setBounds(collisionRectLeft);
		collisionRectRight = new Rectangle(pX + 30, pY + 6 , pW - 30, pH - 12);
		collisionRectRight.setBounds(collisionRectRight);
		updateBounds = true;
		
	}
	
	public void move()
	{
		if (KeyboardManager.up && !checkCollisionTop()) 	 vec = vec.add(new Vector2D (  0 , -1 * 5 ));
		if (KeyboardManager.down && !checkCollisionBot())	 vec = vec.add(new Vector2D ( 0 , 1 * 5 ));
		if (KeyboardManager.left && !checkCollisionLeft())	 vec = vec.add(new Vector2D ( - 1 * 5 , 0 ));
		if (KeyboardManager.right && !checkCollisionRight()) vec = vec.add(new Vector2D (  1 * 5 , 0 ));
	}
	
	
	public boolean checkCollisionTop()
	{
		LinkedList<Rectangle> blocks = Block.getLinkedList();
		for (Rectangle block : blocks)
			if (collisionRectTop.getBounds().intersects(block.getBounds()))
			{
				System.out.println("collision!");
				return true;
			}
		return false;
	}
	
	public boolean checkCollisionBot()
	{
		LinkedList<Rectangle> blocks = Block.getLinkedList();
		for (Rectangle block : blocks)
			if (collisionRectBot.getBounds().intersects(block.getBounds()))
			{
				System.out.println("collision!");
				return true;
			}
		return false;
	}
	
	public boolean checkCollisionLeft()
	{
		LinkedList<Rectangle> blocks = Block.getLinkedList();
		for (Rectangle block : blocks)
			if (collisionRectLeft.getBounds().intersects(block.getBounds()))
			{
				System.out.println("collision!");
				return true;
			}
		return false;
	}
	
	public boolean checkCollisionRight()
	{
		LinkedList<Rectangle> blocks = Block.getLinkedList();
		for (Rectangle block : blocks)
			if (collisionRectRight.getBounds().intersects(block.getBounds()))
			{
				System.out.println("collision!");
				return true;
			}
		return false;
	}
	
	public Rectangle updateBoundsTop()
	{
		pXX = (int)vec.getX();
		pYY = (int)vec.getY();
		collisionRectTop = new Rectangle(pXX + 6, pYY, pW - 12, pH - 30);
		collisionRectTop.setBounds(collisionRectTop);
		return collisionRectTop;
	}
	
	public Rectangle updateBoundsBot()
	{
		pXX = (int)vec.getX();
		pYY = (int)vec.getY();
		collisionRectBot = new Rectangle(pXX + 6, pYY + 30, pW - 12, pH - 30);
		collisionRectBot.setBounds(collisionRectBot);
		return collisionRectBot;
	}
	
	public Rectangle updateBoundsLeft()
	{
		pXX = (int)vec.getX();
		pYY = (int)vec.getY();
		collisionRectLeft = new Rectangle(pXX, pYY + 6 , pW - 30, pH - 12);
		collisionRectLeft.setBounds(collisionRectLeft);
		return collisionRectLeft;
	}
	
	public Rectangle updateBoundsRight()
	{
		pXX = (int)vec.getX();
		pYY = (int)vec.getY();
		collisionRectRight = new Rectangle(pXX + 30, pYY + 6 , pW - 30, pH - 12);
		collisionRectRight.setBounds(collisionRectRight);
		return collisionRectRight;
	}
	
	public void update() 
	{		
		move();
		if(updateBounds)
		{
			updateBoundsTop();
			updateBoundsBot();
			updateBoundsLeft();
			updateBoundsRight();
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
