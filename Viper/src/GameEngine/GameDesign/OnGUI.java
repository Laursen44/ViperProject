package GameEngine.GameDesign;

import java.awt.Graphics;

import GameEngine.Framework.Game;
import GameEngine.Util.KeyboardManager;
import GameEngine.Util.MouseManager;
import GameEngine.Util.Sprites;
import Serialization.VPDatabase;
import Serialization.VPObject;


public class OnGUI
{
	public static boolean healthBar = false; 
	public static boolean abillityBar = false;
	public static boolean loginScreen = false;
	public static boolean usernameActive = false;
	public static boolean connectionNotSent = true;
	public int healthX = 50, healthY = 50, healthBarWidth = 96;
	public int abillityX = Game.WIDTH/2 - ((50*2+16*2)/2), abillityY = Game.HEIGHT - 100, abillityOffset = 50;
	public static int usernameWidth = 300, usernameHeight = 30;
	public static int loginX = Game.WIDTH/2 - 48, loginY = 200, createX = loginX, createY = loginY + 75, 
			playX = loginX, playY = createY + 75, usernameX = Game.WIDTH/2 - usernameWidth/2, usernameY = playY +50;
	public static int abillityBarActive = 1;
	public KeyboardManager keyboard;
	private boolean createHigh = false, loginHigh = false, playHigh = false;
	GUITextBox usernameBox;
	public static String errorMessage = "";
	
	public OnGUI()
	{
		
		keyboard = new KeyboardManager();
		usernameBox = new GUITextBox(usernameX, usernameY, usernameWidth, usernameHeight);
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
		
		usernameBox.render(g);
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
				if (GUITextBox.username.length() > 0)
				{
					if(connectionNotSent)
					{
					VPDatabase database = new VPDatabase("Connection");
					VPObject object = new VPObject(GUITextBox.username);
					database.addObject(object);
					Game.client.send(database);
					connectionNotSent = false;
					}
					
					usernameActive = false;
					Level.setLevel(1);
					Level.changeLevel = true;
				}
				else errorMessage = "Please enter a username";
				
			}
		}
		else playHigh = false;
		if(MouseManager.getX() <= usernameX + usernameWidth && MouseManager.getX() >= usernameX && MouseManager.getY() <= usernameY + 
				usernameHeight && MouseManager.getY() >= usernameY && MouseManager.mouseB == 1)
		{
			usernameActive = true;
		}
	}

}


