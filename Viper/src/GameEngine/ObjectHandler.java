package GameEngine;

import java.awt.Graphics;
import java.util.LinkedList;

public class ObjectHandler 
{
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public ObjectHandler()
	{
		addObject(new Player("Thomas", new Vector2D(100,100), ID.PLAYER, null));
	}
	
	public void update()
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.update();
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
}
