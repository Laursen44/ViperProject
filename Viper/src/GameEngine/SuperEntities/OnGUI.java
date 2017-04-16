package GameEngine.SuperEntities;

import java.awt.Graphics;

import GameEngine.Framework.Game;


public class OnGUI
{

	public static boolean healthBar = false; 
	public static boolean abillityBar = false;
	public static boolean loginScreen = false;
	public int healthX = 50, healthY = 100;
	public int abillityX = Game.WIDTH/2, abillityY = Game.HEIGHT - 100;
	public int loginX = Game.WIDTH/2 - 48, loginY = 200, createX = loginX, createY = loginY + 75;
	
	public OnGUI()
	{
	}

	public void update() 
	{
		
	}

	public void render(Graphics g) 
	{
		healthBarRender(healthBar, g);
		abillityBarRender(abillityBar, g);
		loginScreenRender(loginScreen, g);
	}
	
	public void healthBarRender(boolean active, Graphics g)
	{
		if (!active) return;
		g.drawImage(Sprites.healthbarFrame, healthX, healthY, null);
		g.drawImage(Sprites.healthbar, healthX, healthY, null);
	}
	
	public void healthBarLogic(boolean active)
	{
		if (!active) return;
		
	}
	
	public void abillityBarRender(boolean active, Graphics g)
	{
		if (!active) return;
		
	}
	
	public void abillityBarLogic(boolean active)
	{
		if (!active) return;
		
	}
	
	public void loginScreenRender(boolean active, Graphics g)
	{
		if (!active) return;
		g.drawImage(Sprites.Login, loginX, loginY, null);
		g.drawImage(Sprites.Loginhigh, loginX, loginY, null);
		
		g.drawImage(Sprites.create, createX, createY, null);
		g.drawImage(Sprites.createhigh, createX, createY, null);
		
	}
	
	public void loginScreenLogic(boolean active)
	{
		if (!active) return;
		
	}
}
