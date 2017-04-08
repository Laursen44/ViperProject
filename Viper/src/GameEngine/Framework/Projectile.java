package GameEngine.Framework;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {

	private static final long serialVersionUID = 1L;
	protected Graphics g;
	protected int width = 16, height = 16;
	protected double angle;
	protected int speed;
	protected Vector2D posVec;
	protected Vector2D shootVec;
	
	public Projectile(Vector2D vec, double dir) 
	{
		posVec = vec;
		angle = dir;
		id = ID.PROJECTTILE;
	}
	
	public void move()
	{

	}

	public void update() 
	{
		move();
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.YELLOW);
		g.fillRect((int)posVec.getX(), (int)posVec.getY(), 16, 16);
	}

}
