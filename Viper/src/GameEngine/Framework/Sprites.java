package GameEngine.Framework;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprites 
{
	
	public static BufferedImage loadedSpriteSheet1;
	
	// Sprites start
	
	public static BufferedImage grass1; 
	
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
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void initialize()
	{
		loadedSpriteSheet1 = loadSpriteSheet(loadedSpriteSheet1);
		grass1 = new Sprites().getSpriteSubImage(1, 1, 16, 16, loadedSpriteSheet1);
	}
}
