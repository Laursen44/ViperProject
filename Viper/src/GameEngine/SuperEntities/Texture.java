package GameEngine.SuperEntities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import GameEngine.Util.Vector2D;

public class Texture extends GameObject 
{
	private static final long serialVersionUID = -695564632126953006L;
	protected BufferedImage sprite;
	protected Vector2D vec;
	
	public Texture(Vector2D vec, BufferedImage sprite)
	{
		this.vec = vec;
		this.sprite = sprite;
	}

	public void update() 
	{
	}

	public void render(Graphics g) 
	{
		g.drawImage(sprite, (int)vec.getX(), (int)vec.getY(), null);
	}

}
