package GameEngine.SuperEntities;

import java.awt.Color;
import java.awt.Graphics;

import GameEngine.Util.Vector2D;

public class NetPlayer extends GameObject
{
	public String username = "";
	public Vector2D vec;
	
	public NetPlayer(String username, Vector2D vec)
	{
		this.username = username;
		this.vec = vec;
	}

	public void update() 
	{
		
	}


	public void render(Graphics g) 
	{
		g.setColor(Color.WHITE);
		g.drawString(username, (int)vec.getX() - 8, (int)vec.getY() - 5);
		g.drawImage(Sprites.ship1, (int)vec.getX(), (int)vec.getY(), null);
	}

}
