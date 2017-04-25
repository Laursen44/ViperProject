package GameEngine.Framework;

import java.awt.Graphics;
import java.util.ArrayList;
import GameEngine.SuperEntities.GameObject;
import GameEngine.SuperEntities.NetPlayer;
import GameEngine.SuperEntities.NetProjectile;
import GameEngine.SuperEntities.Projectile;
 
public class ObjectHandler 
{
	public static ArrayList<GameObject> object = new ArrayList<GameObject>();
	public static ArrayList<Projectile> bullet = new ArrayList<Projectile>();
	public static ArrayList<NetPlayer> netPlayers = new ArrayList<NetPlayer>();
	public static ArrayList<NetProjectile> netBullets = new ArrayList<NetProjectile>();
	
	public ObjectHandler()
	{
		 
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
		
		for(int i = 0; i < netPlayers.size(); i++)
		{
			NetPlayer p = netPlayers.get(i);
			p.render(g);
		}
		
		for(int i = 0; i < netBullets.size(); i++)
		{
			NetProjectile p = netBullets.get(i);
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
	
	public static void addNetPlayer(NetPlayer p)
	{
		netPlayers.add(p);
	}
	
	public static void removeNetPlayer(NetPlayer p)
	{
		netPlayers.remove(p);
	}
	
	public static void addNetBullet(NetProjectile p)
	{
		netBullets.add(p);
	}
	
	public static void removeNetBullet(NetProjectile p)
	{
		netBullets.remove(p);
	}
	
	public static ArrayList<GameObject> getObjectList() {
		return object;
	}
	
	public static ArrayList<Projectile> getBulletList() {
		return bullet;
	}
}
