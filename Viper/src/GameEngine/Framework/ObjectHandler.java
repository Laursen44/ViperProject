package GameEngine.Framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class ObjectHandler 
{
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public ObjectHandler()
	{
		addObject(new Player("Thomas", new Vector2D(200, 200), ID.PLAYER, null));
		addObject(new Block(new Rectangle(400,500,200,200)));
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
