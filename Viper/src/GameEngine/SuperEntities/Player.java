package GameEngine.SuperEntities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import GameEngine.Framework.Game;
import GameEngine.Framework.ObjectHandler;
import GameEngine.GameDesign.GUITextBox;
import GameEngine.GameDesign.OnGUI;
import GameEngine.Util.KeyboardManager;
import GameEngine.Util.MouseManager;
import GameEngine.Util.Vector2D;
import Serialization.VPDatabase;
import Serialization.VPField;
import Serialization.VPObject;

public class Player extends GameObject
{
	private static final long serialVersionUID = 1L;
	Graphics g;
	public static Vector2D vec;
	private int health = 100;
	private String name = GUITextBox.username;
	int cooldownTimer = 30, cooldown;
	public static Rectangle collisionRectTop, collisionRectBot, collisionRectLeft, collisionRectRight;
	public int pW = 32, pH = 32, x, y;
	private boolean updateBounds = false, Shoot = false;
	int pXX, pYY, hDir, vDir;
	ObjectHandler handler;
	
	public Player(Vector2D vec)
	{
		Player.vec = vec;
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
		Shoot = true;
	}
	
	public void shoot(Vector2D initVec, float aimX, float aimY) 
	{
		float opposite = aimY - initVec.getY();
		float adjacent = aimX - initVec.getX();
		float angle = (float)Math.atan2(opposite, adjacent);
		
		if (OnGUI.abillityBarActive == 1)
		{
			cooldown = 10;
			Projectile p = new Projectile(initVec.add(8), angle, 20, 8, Sprites.bullet1Red);
			ObjectHandler.addBullet(p);
		}
		
		if (OnGUI.abillityBarActive == 2)
		{
			cooldown = 30;
			Projectile p = new Projectile(initVec.add(8), angle, 20, 16, Sprites.bullet2White);
			ObjectHandler.addBullet(p);
		}
		
		if (OnGUI.abillityBarActive == 3)
		{
			cooldown = 60;
			Projectile p = new Projectile(initVec.add(8), angle, 20, 12, Sprites.bullet3Orange);
			ObjectHandler.addBullet(p);
		}
		
	}
	
	public void checkIfShot()
	{
		if (MouseManager.getB() == 1 && cooldownTimer > cooldown)
		{
			shoot(vec, MouseManager.getX(), MouseManager.getY());
			cooldownTimer = 0;
		}
	}
	
	public void move()
	{
		VPDatabase database = new VPDatabase("Update");
		VPObject object = new VPObject(GUITextBox.username);
		VPField xcord = VPField.Integer("x", 0);
		VPField ycord = VPField.Integer("y", 0);
		
		if (KeyboardManager.up && !checkCollisionTop())
		{
			y = -5;
			ycord = VPField.Integer("y", y);
		} 	 
			
		if (KeyboardManager.down && !checkCollisionBot())
		{
			y = +5;
			ycord = VPField.Integer("y", y);
		}	 
		
		if (KeyboardManager.left && !checkCollisionLeft())
		{
			x = -5;
			xcord = VPField.Integer("x", x);
		}
			
		if (KeyboardManager.right && !checkCollisionRight())
		{
			x = +5;
			xcord = VPField.Integer("x", x);
		}
		
		
		if(xcord != null && ycord != null)
		{
			object.addField(xcord);
			object.addField(ycord);
			
			database.addObject(object);
			
			Game.client.send(database);
			x = 0;
			y = 0;
		}
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
		
		 if (Shoot)
		 {
			 checkIfShot();
		 }
		 cooldownTimer++;
	}

	public void render(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.drawString(this.name, (int)vec.getX() - 8, (int)vec.getY() - 5);
		g.drawImage(Sprites.ship1, (int)vec.getX(), (int)vec.getY(), null);
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
