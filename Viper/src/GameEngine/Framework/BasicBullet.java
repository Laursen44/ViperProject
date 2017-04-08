package GameEngine.Framework;

import java.awt.Color;
import java.awt.Graphics;

public class BasicBullet extends Projectile
{
	private static final long serialVersionUID = 1L;

	public BasicBullet(Vector2D vec, double dir) 
	{
		super(vec, dir);
		speed = 6;
		
		shootVec = new Vector2D(Math.cos(angle), Math.sin(angle));
		shootVec = shootVec.mul(speed);
	}
	
	public void update()
	{
		move();
	}
	
	public void move()
	{
		posVec = posVec.add(shootVec);
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillRect((int)posVec.getX(), (int)posVec.getY(), 16, 16);
	}
	
}
