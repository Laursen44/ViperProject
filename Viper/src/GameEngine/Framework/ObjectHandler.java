package GameEngine.Framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import Entities.DirtTexture;
import Entities.GrassTexture;
import Entities.Player;
import Entities.WallBlockHori;
import Entities.WallBlockVerti;
import GameEngine.SuperEntities.GameObject;
import GameEngine.SuperEntities.Projectile;
import GameEngine.Util.Vector2D;

public class ObjectHandler 
{
	public static LinkedList<GameObject> object = new LinkedList<GameObject>();
	public static LinkedList<Projectile> bullet = new LinkedList<Projectile>();
	
	public ObjectHandler()
	{
		addBackgroundTextures();
		addWalls();
		addObject(new Player("Thomas", new Vector2D(200, 200)));	
	}
	
	private void addBackgroundTextures()
	{
		for (int i = 0; i < Game.WIDTH; i += 32)
		{
			for (int j = 0; j < Game.WIDTH; j += 32)
			{
				addObject(new GrassTexture(new Vector2D(i, j)));
			}
		}
	}
	
	private void addWalls() 
	{
		for (int i = 0; i < Game.WIDTH; i += 32)
		{
			addObject(new WallBlockVerti(new Rectangle(i, 0, 32, 32)));
			addObject(new WallBlockVerti(new Rectangle(i, Game.HEIGHT - 60, 32, 32)));
		}
		
		for (int i = 0; i < Game.HEIGHT; i += 32)
		{
			addObject(new WallBlockHori(new Rectangle(0, i, 32, 32)));
			addObject(new WallBlockHori(new Rectangle(Game.WIDTH - 38, i, 32, 32)));
		}
	}
	
	public void update()
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject o = object.get(i);
			o.update();
		}
		
		for(int i = 0; i < bullet.size(); i++)
		{
			Projectile p = bullet.get(i);
			p.update();
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject o = object.get(i);
			o.render(g);
		}
		
		for(int i = 0; i < bullet.size(); i++)
		{
			Projectile p = bullet.get(i);
			p.render(g);
		}
	}
	
	public static void addObject(GameObject o)
	{
		object.add(o);
	}
	
	public static void removeObject(GameObject o)
	{
		object.remove(o);
	}
	
	public static void addBullet(Projectile p)
	{
		bullet.add(p);
	}
	
	public static void removeBullet(Projectile p)
	{
		bullet.remove(p);
	}
	
	public static LinkedList<GameObject> getObjectList() {
		return object;
	}
	
	public static LinkedList<Projectile> getBulletList() {
		return bullet;
	}
}
