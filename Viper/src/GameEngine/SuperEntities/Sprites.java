package GameEngine.SuperEntities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import GameEngine.Util.BufferedImageLoader;

public class Sprites 
{
	
	public static BufferedImage loadedSpriteSheet1;
	
	// Sprites start
	public static BufferedImage grass1;
	public static BufferedImage grass2;
	public static BufferedImage building1;
	public static BufferedImage ship1;
	public static BufferedImage wallVertical;
	public static BufferedImage wallHorizontal;
	public static BufferedImage wallLargeVertical;
	public static BufferedImage wallLargeHorizontal;
	public static BufferedImage wallRed;
	public static BufferedImage dirt1;
	public static BufferedImage healthbarFrame;
	public static BufferedImage healthbar;
	
	public static BufferedImage bullet1Red;
	public static BufferedImage bullet2White;
	public static BufferedImage bullet3Orange;
	
	public static BufferedImage Login;
	public static BufferedImage Loginhigh;
	
	public static BufferedImage create;
	public static BufferedImage createhigh;
	
	public static BufferedImage play;
	public static BufferedImage playhigh;
	
	public static BufferedImage abillityBar1;
	public static BufferedImage abillityBar1High;
	
	public static BufferedImage abillityBar2;
	public static BufferedImage abillityBar2High;
	
	public static BufferedImage abillityBar3;
	public static BufferedImage abillityBar3High;
	// Sprites end
	
	public Sprites()
	{
		
	}
	
	public BufferedImage getSpriteSubImage(int col, int row, int width, int height, BufferedImage image)
	{
		BufferedImage img = image.getSubimage((col * 16) - 16, (row * 16) - 16, width, height);
		return img;
	}
	
	public static BufferedImage loadSpriteSheet(BufferedImage image)
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			image =  loader.loadImage("/spritesheet.png");
			return image;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static void initialize()
	{
		loadedSpriteSheet1 = loadSpriteSheet(loadedSpriteSheet1);
		grass1 = new Sprites().getSpriteSubImage(1, 1, 32, 32, loadedSpriteSheet1);
		building1 = new Sprites().getSpriteSubImage(4, 1, 64, 64, loadedSpriteSheet1);
		ship1 = new Sprites().getSpriteSubImage(1, 5, 32, 32, loadedSpriteSheet1);
		wallRed = new Sprites().getSpriteSubImage(1, 7, 32, 32, loadedSpriteSheet1);
		wallVertical = new Sprites().getSpriteSubImage(1, 9, 32, 32, loadedSpriteSheet1);
		wallHorizontal = new Sprites().getSpriteSubImage(1, 11, 32, 32, loadedSpriteSheet1);
		dirt1 = new Sprites().getSpriteSubImage(1, 3, 32, 32, loadedSpriteSheet1);
		wallLargeVertical = new Sprites().getSpriteSubImage(8, 5, 128, 32, loadedSpriteSheet1);
		wallLargeHorizontal = new Sprites().getSpriteSubImage(8, 7, 32, 128, loadedSpriteSheet1);
		grass2 = new Sprites().getSpriteSubImage(10, 7, 64, 64, loadedSpriteSheet1);
		
		bullet1Red = new Sprites().getSpriteSubImage(3, 1, 16, 16, loadedSpriteSheet1);
		bullet2White = new Sprites().getSpriteSubImage(3, 2, 16, 16, loadedSpriteSheet1);
		bullet3Orange = new Sprites().getSpriteSubImage(3, 3, 16, 16, loadedSpriteSheet1);
		
		healthbar = new Sprites().getSpriteSubImage(8, 3, 96, 32, loadedSpriteSheet1);
		healthbarFrame = new Sprites().getSpriteSubImage(8, 1, 96, 32, loadedSpriteSheet1);
		Login = new Sprites().getSpriteSubImage(3, 5, 80, 32, loadedSpriteSheet1);
		Loginhigh = new Sprites().getSpriteSubImage(3, 7, 80, 32, loadedSpriteSheet1);
		create = new Sprites().getSpriteSubImage(3, 9, 80, 32, loadedSpriteSheet1);
		createhigh = new Sprites().getSpriteSubImage(3, 11, 80, 32, loadedSpriteSheet1);
		play = new Sprites().getSpriteSubImage(3, 15, 80, 32, loadedSpriteSheet1);
		playhigh = new Sprites().getSpriteSubImage(3, 13, 80, 32, loadedSpriteSheet1);
		
		abillityBar1 = new Sprites().getSpriteSubImage(1, 13, 32, 32, loadedSpriteSheet1);
		abillityBar2 = new Sprites().getSpriteSubImage(1, 15, 32, 32, loadedSpriteSheet1);
		abillityBar3 = new Sprites().getSpriteSubImage(8, 15, 32, 32, loadedSpriteSheet1);
		
		abillityBar1High = new Sprites().getSpriteSubImage(10, 11, 32, 32, loadedSpriteSheet1);
		abillityBar2High = new Sprites().getSpriteSubImage(10, 13, 32, 32, loadedSpriteSheet1);
		abillityBar3High = new Sprites().getSpriteSubImage(10, 15, 32, 32, loadedSpriteSheet1);
	}
}
