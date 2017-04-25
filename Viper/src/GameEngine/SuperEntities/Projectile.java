package GameEngine.SuperEntities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import GameEngine.Framework.Game;
import GameEngine.Framework.ObjectHandler;
import GameEngine.GameDesign.GUITextBox;
import GameEngine.Util.Sprites;
import GameEngine.Util.Vector2D;
import Serialization.VPDatabase;
import Serialization.VPField;
import Serialization.VPObject;

public class Projectile extends GameObject {

	private static final long serialVersionUID = 1L;
	protected Graphics g;
	protected int width = 16, height = 16;
	protected double angle;
	protected int speed, damage;
	protected int pXX, pYY;
	protected Vector2D posVec;
	protected Vector2D shootVec;
	public int x, y;
	public String username;
	public int index;
	protected Rectangle collisionRect;
	protected BufferedImage sprite;
	protected boolean updateBounds = false;
	
	public Projectile(Vector2D vec, double dir, int damage, int speed, BufferedImage sprite, int type, int index) 
	{
		this.damage = damage;
		this.speed = speed;
		this.sprite = sprite;
		this.index = index;
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
		
		VPDatabase database = new VPDatabase("Projectiles");	
		for (int i = 0; i < ObjectHandler.bullet.size(); i++)
		{
			VPObject object = new VPObject(GUITextBox.username + "," + index);
			VPField xdir = VPField.Float("xd", shootVec.getX());
			VPField ydir = VPField.Float("yd", shootVec.getY());
			VPField xCord = VPField.Integer("x", (int)posVec.getX());
			VPField yCord = VPField.Integer("y", (int)posVec.getY());
			object.addField(xCord);
			object.addField(yCord);
			object.addField(xdir);
			object.addField(ydir);
			database.addObject(object);
		}
		Game.client.send(database);
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
		LinkedList<Rectangle> blocks = Block.getLinkedList();
		ArrayList<Projectile> bullets = ObjectHandler.getBulletList();
		
		for (Rectangle block : blocks)
		{
			for (int i = 0; i < bullets.size(); i++){
				
				if (bullets.get(i).collisionRect.getBounds().intersects(block.getBounds()))
				{
					Projectile p = bullets.get(i);
					ObjectHandler.removeBullet(p);
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
		g.drawImage(sprite, (int)posVec.getX(), (int)posVec.getX(), null);
	}

}
