package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import GameEngine.SuperEntities.Block;
import GameEngine.SuperEntities.Sprites;

public class WallBlockVerti extends Block 
{
	private static final long serialVersionUID = 1L;

	public WallBlockVerti(Rectangle rect) {
		super(rect);
		addStaticCollision(rect);
	}
	
	public void update() 
	{

	}

	public void render(Graphics g) 
	{
		g.drawImage(Sprites.wallLargeVertical, rect.x, rect.y, null);
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
