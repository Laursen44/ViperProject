package GameEngine.Framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Block extends GameObject 
{
	private static final long serialVersionUID = 1L;
	
	public Rectangle rect;
	public static ArrayList<Rectangle> blockBounds = new ArrayList<Rectangle>();
	
	public Block(){}

	public Block(Rectangle rect) 
	{
		id = ID.STATIC;
		this.rect = rect;
		setBounds(rect);
		blockBounds.add(rect);
	}
	
	public void update() 
	{

	}

	public void render(Graphics g) 
	{
		g.setColor(Color.BLUE);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
	}

	public static ArrayList<Rectangle> getArrayList() {
		return blockBounds;
	}

}
