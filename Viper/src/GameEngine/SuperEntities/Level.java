package GameEngine.SuperEntities;


import java.awt.Rectangle;
import GameEngine.Framework.Game;
import GameEngine.Framework.ObjectHandler;
import GameEngine.Util.Vector2D;

public class Level 
{
	public boolean changeLevel = true;
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
		ObjectHandler.addObject(new Player("Thomas", new Vector2D(200, 200)));	
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
