package GameEngine.SuperEntities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Block extends GameObject 
{
	private static final long serialVersionUID = 1L;
	public Rectangle rect;
	protected BufferedImage sprite;
	public static LinkedList<Rectangle> blockBounds = new LinkedList<Rectangle>();

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

	public static LinkedList<Rectangle> getLinkedList() {
		return blockBounds;
	}
	
	public void addStaticCollision(Rectangle rect)
	{
		setBounds(rect);
		blockBounds.add(rect);
	}

}
