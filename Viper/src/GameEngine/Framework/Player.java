package GameEngine.Framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player extends GameObject
{

	private static final long serialVersionUID = 1L;
	Graphics g;
	private int damage;
	private int health = 100;
	private String name;
	public ArrayList<Rectangle> playerBounds = new ArrayList<Rectangle>();
	public Rectangle collisionTop;
	public Rectangle collisionBot;
	public Rectangle collisionLeft;
	public Rectangle collisionRight;
	public float playerX, playerY = 200;
	public int playerW, playerH = 50;
	
	public Player(String name, Vector2D vec, ID id, Graphics g)
	{
		super(vec, id);
		this.name = name;
		this.g = g;
		collisionTop.setBounds(rect);
	}
	
	public void move()
	{
		//if(!collision())
		{
		vec = vec.add(new Vector2D ( 0 , getVelY() * 5 ));
		vec = vec.add(new Vector2D ( getVelX() * 5 , 0 ));
		}

	}
	
	public void collision()
	{
		ArrayList<Rectangle> blocks = Block.getArrayList();
		
		for (Rectangle block : blocks)
			if (collisionTop.getBounds().intersects(block.getBounds()))
			{
				System.out.println("gayThomas");
				
			}
	}
	
	public void bounds()
	{
		//collisionTop.setBounds(new Rectangle((int)vec.getX(), (int)vec.getY(), 50, 5));

	}
	
	public void update() 
	{
		
	
		move();
		collision();
	}

	public void render(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect((int)vec.getX(),(int)vec.getY(), playerH, playerW);
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
