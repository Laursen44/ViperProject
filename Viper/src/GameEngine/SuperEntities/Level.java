package GameEngine.SuperEntities;


import java.awt.Rectangle;

import Entities.GrassTexture;
import Entities.Player;
import Entities.WallBlockHori;
import Entities.WallBlockVerti;
import GameEngine.Framework.Game;
import GameEngine.Framework.ObjectHandler;
import GameEngine.Util.Vector2D;

public class Level 
{
	private static final long serialVersionUID = 1L;
	private int level = 1;
	
	public Level() 
	{
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void loginScreen(int level)
	{
		if (level != 0) return;
		addBackgroundTextures();
		addWalls();
	}
	
	public void level1(int level) 
	{
		if (level != 1) return;
		addBackgroundTextures();
		addWalls();
		ObjectHandler.addObject(new Player("Thomas", new Vector2D(200, 200)));	
	}
	
	private void addBackgroundTextures()
	{
		for (int i = 0; i < Game.WIDTH; i += 64)
		{
			for (int j = 0; j < Game.WIDTH; j += 64)
			{
				ObjectHandler.addObject(new GrassTexture(new Vector2D(i, j)));
			}
		}
	}
	
	private void addWalls() 
	{
		for (int i = 0; i < Game.WIDTH; i += 128)
		{
			ObjectHandler.addObject(new WallBlockVerti(new Rectangle(i, 0, 128, 32)));
			ObjectHandler.addObject(new WallBlockVerti(new Rectangle(i, Game.HEIGHT - 60, 128, 32)));
		}
		
		for (int i = 0; i < Game.HEIGHT; i += 128)
		{
			ObjectHandler.addObject(new WallBlockHori(new Rectangle(0, i, 32, 128)));
			ObjectHandler.addObject(new WallBlockHori(new Rectangle(Game.WIDTH - 38, i, 32, 128)));
		}
	}
}
