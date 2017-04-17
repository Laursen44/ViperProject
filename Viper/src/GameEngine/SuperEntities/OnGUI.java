package GameEngine.SuperEntities;

import java.awt.Graphics;

import GameEngine.Framework.Game;
import GameEngine.Util.KeyboardManager;


public class OnGUI
{
	
	public static boolean healthBar = false; 
	public static boolean abillityBar = false;
	public static boolean loginScreen = false;
	public int healthX = 50, healthY = 50;
	public int abillityX = Game.WIDTH/2 - ((50*2+32*2)/2), abillityY = Game.HEIGHT - 100, abillityOffset = 50;
	public int loginX = Game.WIDTH/2 - 48, loginY = 200, createX = loginX, createY = loginY + 75;
	public static int abillityBarActive = 1;
	
	public OnGUI()
	{
	}
	
	public void update() 
	{
		abillityBarLogic(abillityBar);
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
		
		g.drawImage(Sprites.abillityBar1, abillityX, abillityY, null);
		
		if(abillityBarActive == 1)
		{
		g.drawImage(Sprites.abillityBar1High, abillityX, abillityY, null);
		}
		
		g.drawImage(Sprites.abillityBar2, abillityX + abillityOffset*1, abillityY, null);
		
		if(abillityBarActive == 2)
		{
		g.drawImage(Sprites.abillityBar2High, abillityX + abillityOffset*1, abillityY, null);
		}
		
		g.drawImage(Sprites.abillityBar3, abillityX + abillityOffset*2, abillityY, null);
		
		if(abillityBarActive == 3)
		{
		g.drawImage(Sprites.abillityBar3High, abillityX + abillityOffset*2, abillityY, null);
		}
	}
	
	public void abillityBarLogic(boolean active)
	{
		if (!active) return;
		
		if(KeyboardManager.key1) abillityBarActive = 1;
		if(KeyboardManager.key2) abillityBarActive = 2;
		if(KeyboardManager.key3) abillityBarActive = 3;
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
