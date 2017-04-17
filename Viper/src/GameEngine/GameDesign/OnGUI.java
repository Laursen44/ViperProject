package GameEngine.GameDesign;

import java.awt.Graphics;

import GameEngine.Framework.Game;
import GameEngine.SuperEntities.Sprites;
import GameEngine.Util.KeyboardManager;
import GameEngine.Util.MouseManager;


public class OnGUI
{
	
	public static boolean healthBar = false; 
	public static boolean abillityBar = false;
	public static boolean loginScreen = false;
	public int healthX = 50, healthY = 50, healthBarWidth = 96;
	public int abillityX = Game.WIDTH/2 - ((50*2+16*2)/2), abillityY = Game.HEIGHT - 100, abillityOffset = 50;
	public int loginX = Game.WIDTH/2 - 48, loginY = 200, createX = loginX, createY = loginY + 75, playX = loginX, playY = createY + 75;
	public static int abillityBarActive = 1;
	
	private boolean createHigh = false, loginHigh = false, playHigh = false;
	
	public OnGUI()
	{
	}
	
	public void update() 
	{
		abillityBarLogic(abillityBar);
		healthBarLogic(healthBar);
		loginScreenLogic(loginScreen);
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
		g.drawImage(Sprites.healthbar, healthX, healthY, healthBarWidth, 32, null);
	}
	
	public void healthBarLogic(boolean active)
	{
		if (!active) return;
		
		//if player is hit by another players projectile
		// healthBarWidth--;
		
		//clamping health
		if (healthBarWidth <= 0) healthBarWidth = 0;
		if (healthBarWidth >= 96) healthBarWidth = 96;
		
		
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
		if(loginHigh)
		{
			g.drawImage(Sprites.Loginhigh, loginX, loginY, null);
		}
		
		g.drawImage(Sprites.create, createX, createY, null);
		if (createHigh)
		{
			g.drawImage(Sprites.createhigh, createX, createY, null);
		}
		
		g.drawImage(Sprites.play, playX, playY, null);
		if (playHigh)
		{
			g.drawImage(Sprites.playhigh, playX, playY, null);
		}
	}
	
	public void loginScreenLogic(boolean active)
	{
		if (!active) return;
		
		if (MouseManager.getX() <= loginX + 80 && MouseManager.getX() >= loginX && MouseManager.getY() <= loginY + 32 && MouseManager.getY() >= loginY)
		{
			loginHigh = true;
			if(MouseManager.mouseB == 1)
			{
				//add code here for the player to login
			}
		}
		else loginHigh = false;
				
		if (MouseManager.getX() <= createX + 80 && MouseManager.getX() >= createX && MouseManager.getY() <= createY + 32 && 
				MouseManager.getY() >= createY)
		{
			createHigh = true;
			if(MouseManager.mouseB == 1)
			{
				// add code here for the player to create an account
			}
		}
		else createHigh = false;
		
		if (MouseManager.getX() <= playX + 80 && MouseManager.getX() >= playX && MouseManager.getY() <= playY + 32 && 
				MouseManager.getY() >= playY)
		{
			playHigh = true;
			if(MouseManager.mouseB == 1)
			{
				Level.setLevel(1);
				Level.changeLevel = true;
			}
		}
		else playHigh = false;

	}
}
