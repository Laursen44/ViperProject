package Entities;

import java.awt.Graphics;

import GameEngine.SuperEntities.Sprites;
import GameEngine.SuperEntities.Texture;
import GameEngine.Util.Vector2D;

public class GrassTexture extends Texture 
{

	private static final long serialVersionUID = 1L;

	public GrassTexture(Vector2D vec) 
	{
		super(vec);
	}
	
	public void render(Graphics g) 
	{
		g.drawImage(Sprites.grass1, (int)vec.getX(), (int)vec.getY(), null);
	}
}
