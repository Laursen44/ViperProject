package GameEngine.SuperEntities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import GameEngine.Framework.ObjectHandler;
import GameEngine.Util.Sprites;
import GameEngine.Util.Vector2D;

public class NetProjectile extends GameObject {

	private static final long serialVersionUID = 1L;
	protected Graphics g;
	protected int width = 16, height = 16;
	protected float angle;
	protected int speed, damage;
	protected int pXX, pYY;
	protected Vector2D posVec;
	protected Vector2D shootVec;
	public int x, y;
	protected Rectangle collisionRect;
	protected BufferedImage sprite;
	protected boolean updateBounds = false;
	
	public NetProjectile(Vector2D vec, float dir, int type) 
	{
		
		if (type == 1)
		{
			this.damage = 10;
			this.speed = 8;
			this.sprite = Sprites.bullet1Red;
		}
		
		if (type == 2)
		{
			this.damage = 20;
			this.speed = 14;
			this.sprite = Sprites.bullet2White;
		}
		
		if (type == 3)
		{
			this.damage = 50;
			this.speed = 12;
			this.sprite = Sprites.bullet3Orange;
		}
		
		posVec = vec;
		angle = dir;
		shootVec = new Vector2D(Math.cos(angle), Math.sin(angle));
		shootVec = shootVec.mul(speed);
		int pX = (int)vec.getX();
		int pY = (int)vec.getY();
		collisionRect = new Rectangle(pX, pY, width, height);
		collisionRect.setBounds(collisionRect);
		updateBounds = true;
	}

	public void move()
	{
		posVec = posVec.add(shootVec);
	}
	
	public Rectangle getCollisiosRect() {
		return collisionRect;
	}

	public Rectangle updateBounds()
	{
		pXX = (int)posVec.getX();
		pYY = (int)posVec.getY();
		collisionRect = new Rectangle(pXX, pYY, width, height);
		collisionRect.setBounds(collisionRect);
		return collisionRect;
	}
	
	public void removeIfCollideBlock()
	{
		ArrayList<Rectangle> blocks = Block.getArrayList();
		ArrayList<NetProjectile> bullets = ObjectHandler.getNetBulletList();
		
		for (Rectangle block : blocks)
		{
			for (int i = bullets.size() -1; i >= 0 ; i--){
				
				if (ObjectHandler.netBullets.get(i).collisionRect.getBounds().intersects(block.getBounds()))
				{
					
					ObjectHandler.removeNetBullet(ObjectHandler.netBullets.get(i));
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
			removeIfCollideBlock();
		}
	}

	public void render(Graphics g) 
	{
		g.drawImage(sprite, (int)posVec.getX(), (int)posVec.getY(), null);
	}

}
