package GameEngine.GameDesign;

import java.awt.Rectangle;
import GameEngine.Framework.Game;
import GameEngine.Framework.ObjectHandler;
import GameEngine.SuperEntities.Block;
import GameEngine.SuperEntities.Player;
import GameEngine.SuperEntities.Sprites;
import GameEngine.SuperEntities.Texture;
import GameEngine.Util.Vector2D;

public class Level 
{
	public static boolean changeLevel = true;
	public static int level = 0;
	
	public Level() 
	{
		
	}
	
	public static int getLevel() {
		return level;
	}

	public static void setLevel(int newLevel) {
		level = newLevel;
	}
	
	public void update()
	{
		if(changeLevel != true) return;
		loginScreen(level);
		level1(level);
		changeLevel = false;
	}
	
	public void loginScreen(int level)
	{
		if (level != 0) return;
		addBackgroundTextures();
		addWalls();
		OnGUI.healthBar = false;
		OnGUI.abillityBar = false;
		OnGUI.loginScreen = true;
		
	}
	
	public void level1(int level) 
	{
		if (level != 1) return;
		addBackgroundTextures();
		addWalls();
		OnGUI.loginScreen = false;
		OnGUI.healthBar = true;
		OnGUI.abillityBar = true;
	}
	
	private void addBackgroundTextures()
	{
		for (int i = 0; i < Game.WIDTH; i += 64)
		{
			for (int j = 0; j < Game.WIDTH; j += 64)
			{
				ObjectHandler.addObject(new Texture(new Vector2D(i, j), Sprites.grass2));
			}
		}
	}
	
	private void addWalls() 
	{
		for (int i = 0; i < Game.WIDTH; i += 128)
		{
			ObjectHandler.addObject(new Block(new Rectangle(i, 0, 128, 32), Sprites.wallLargeVertical));
			ObjectHandler.addObject(new Block(new Rectangle(i, Game.HEIGHT - 60, 128, 32), Sprites.wallLargeVertical));
		}
		
		for (int i = 0; i < Game.HEIGHT; i += 128)
		{
			ObjectHandler.addObject(new Block(new Rectangle(0, i, 32, 128), Sprites.wallLargeHorizontal));
			ObjectHandler.addObject(new Block(new Rectangle(Game.WIDTH - 38, i, 32, 128), Sprites.wallLargeHorizontal));
		}
	}
}
