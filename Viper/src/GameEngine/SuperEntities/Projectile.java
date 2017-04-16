package GameEngine.SuperEntities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import Entities.ID;
import GameEngine.Framework.ObjectHandler;
import GameEngine.Util.Vector2D;

public class Projectile extends GameObject {

	private static final long serialVersionUID = 1L;
	protected Graphics g;
	protected int width = 16, height = 16;
	protected double angle;
	protected int speed;
	protected int pXX, pYY;
	protected Vector2D posVec;
	protected Vector2D shootVec;
	public Rectangle collisiosRect;
	protected boolean updateBounds = false;
	
	public Projectile(Vector2D vec, double dir) 
	{
		posVec = vec;
		angle = dir;
		id = ID.PROJECTTILE;
		int pX = (int)vec.getX();
		int pY = (int)vec.getY();
		collisiosRect = new Rectangle(pX, pY, width, height);
		collisiosRect.setBounds(collisiosRect);
		updateBounds = true;
	}
	
	public void move()
	{
		
	}
	
	public Rectangle updateBounds()
	{
		pXX = (int)posVec.getX();
		pYY = (int)posVec.getY();
		collisiosRect = new Rectangle(pXX, pYY, width, height);
		collisiosRect.setBounds(collisiosRect);
		return collisiosRect;
	}
	
	public void removeIfCollision()
	{
		LinkedList<Rectangle> blocks = Block.getLinkedList();
		LinkedList<Projectile> bullets = ObjectHandler.getBulletList();
		
		for (Rectangle block : blocks)
		{
			for (int i = 0; i < bullets.size(); i++){
				
				if (bullets.get(i).collisiosRect.getBounds().intersects(block.getBounds()))
				{
					ObjectHandler.removeBullet(bullets.get(i));
				}
			}
		}
	}
	
	public void update() 
	{
		move();
		if (updateBounds)
		{
			updateBounds();
			removeIfCollision();
		}
	}

	public void render(Graphics g) 
	{
		
	}

}
