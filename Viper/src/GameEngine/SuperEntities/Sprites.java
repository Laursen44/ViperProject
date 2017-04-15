package GameEngine.SuperEntities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import GameEngine.Util.BufferedImageLoader;

public class Sprites 
{
	
	public static BufferedImage loadedSpriteSheet1;
	
	// Sprites start
	public static BufferedImage grass1;
	public static BufferedImage building1;
	public static BufferedImage ship1;
	public static BufferedImage wallVertical;
	public static BufferedImage wallHorizontal;
	public static BufferedImage wallRed;
	public static BufferedImage bulletRed;
	public static BufferedImage bulletWhite;
	public static BufferedImage bulletOrange;
	public static BufferedImage dirt1;
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
		bulletRed = new Sprites().getSpriteSubImage(3, 1, 16, 16, loadedSpriteSheet1);
		bulletWhite = new Sprites().getSpriteSubImage(3, 3, 16, 16, loadedSpriteSheet1);
		bulletOrange = new Sprites().getSpriteSubImage(3, 5, 16, 16, loadedSpriteSheet1);
		dirt1 = new Sprites().getSpriteSubImage(1, 3, 32, 32, loadedSpriteSheet1);
	}
}
