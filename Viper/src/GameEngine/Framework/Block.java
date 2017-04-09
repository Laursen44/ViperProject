package GameEngine.Framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

public class Block extends GameObject 
{
	private static final long serialVersionUID = 1L;
	
	public Rectangle rect;
	public static LinkedList<Rectangle> blockBounds = new LinkedList<Rectangle>();


	public Block(Rectangle rect) 
	{
		id = ID.STATIC;
		this.rect = rect;
		addStaticCollision(rect);
	}
	
	public void update() 
	{

	}

	public void render(Graphics g) 
	{
		g.setColor(Color.BLUE);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
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
