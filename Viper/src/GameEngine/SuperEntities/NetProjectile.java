package GameEngine.SuperEntities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import GameEngine.Util.Sprites;
import GameEngine.Util.Vector2D;

public class NetProjectile extends GameObject {

	private static final long serialVersionUID = 1L;
	public String username;
	public Vector2D vec;
	public BufferedImage sprite;
	public int damage;

	public NetProjectile(Vector2D vec, String username, int type)
	{
		this.vec = vec;
		this.username = username;
		
		if(type == 1)
		{
			this.sprite = Sprites.bullet1Red;
			damage = 10;
		} 
		
		if(type == 2)
		{
			this.sprite = Sprites.bullet2White;
			damage = 20;
		} 
		
		if(type == 3)
		{
			this.sprite = Sprites.bullet3Orange;
			damage = 50;
		}
	}
	public void update(float x, float y)
	{
		this.vec = this.vec.add(new Vector2D(x, y));
	}

	public void render(Graphics g) 
	{
		g.drawImage(sprite, (int)vec.getX(), (int)vec.getY(), null);
	}
	
	public void update() 
	{

	}
}
