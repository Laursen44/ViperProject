package Entities;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import GameEngine.Framework.ObjectHandler;
import GameEngine.SuperEntities.Block;
import GameEngine.SuperEntities.Projectile;
import GameEngine.SuperEntities.Sprites;
import GameEngine.Util.Vector2D;

public class BasicBullet extends Projectile
{
	private static final long serialVersionUID = 1L;

	public BasicBullet(Vector2D vec, double dir) 
	{
		super(vec, dir);
		speed = 12;
		
		shootVec = new Vector2D(Math.cos(angle), Math.sin(angle));
		shootVec = shootVec.mul(speed);
		int pX = (int)vec.getX();
		int pY = (int)vec.getY();
		collisiosRect = new Rectangle(pX, pY, width, height);
		collisiosRect.setBounds(collisiosRect);
		updateBounds = true;
	}
	
	public Rectangle updateBounds()
	{
		pXX = (int)posVec.getX();
		pYY = (int)posVec.getY();
		collisiosRect = new Rectangle(pXX, pYY, width, height);
		collisiosRect.setBounds(collisiosRect);
		return collisiosRect;
	}
	
	public void removeIfCollideBlock()
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
	
	public void move()
	{
		posVec = posVec.add(shootVec);
	}
	
	public void update()
	{
		move();
		
		if (updateBounds)
		{
			updateBounds();
			removeIfCollideBlock();
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Sprites.bulletWhite, (int)posVec.getX(), (int)posVec.getY(), null);
	}
	
}
