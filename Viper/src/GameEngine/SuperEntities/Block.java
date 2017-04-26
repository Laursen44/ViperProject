package GameEngine.SuperEntities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

public class Block extends GameObject 
{
	private static final long serialVersionUID = 1L;
	public Rectangle rect;
	protected BufferedImage sprite;
	public static ArrayList<Rectangle> blockBounds = new ArrayList<Rectangle>();

	public Block(Rectangle rect, BufferedImage sprite) 
	{
		this.rect = rect;
		this.sprite = sprite;
		addStaticCollision(rect);
	}
	
	public void update() 
	{

	}

	public void render(Graphics g) 
	{
		g.drawImage(sprite, (int)rect.getX(), (int)rect.getY(), null);
	}

	public static ArrayList<Rectangle> getArrayList() {
		return blockBounds;
	}
	
	public void addStaticCollision(Rectangle rect)
	{
		setBounds(rect);
		blockBounds.add(rect);
	}

}
