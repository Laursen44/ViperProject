package GameEngine.SuperEntities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import Entities.ID;

public class Block extends GameObject 
{
	private static final long serialVersionUID = 1L;
	
	public Rectangle rect;
	public static LinkedList<Rectangle> blockBounds = new LinkedList<Rectangle>();

	public Block(Rectangle rect) 
	{
		this.rect = rect;
		addStaticCollision(rect);
	}
	
	public void update() 
	{

	}

	public void render(Graphics g) 
	{
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
