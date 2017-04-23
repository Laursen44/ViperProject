package GameEngine.GameDesign;

import java.awt.Graphics;

public class GUITextBox 
{
	public static String username = "";
	public int x, y, width, height;
	
	public GUITextBox(int x, int y, int width, int height)
	{
		this.x = x; 
		this.y = y; 
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics g)
	{
		g.drawRect(x, y, width, height);
		g.drawString("username: " + username, x, y+20);
		g.drawString(OnGUI.errorMessage, x, y+50);
	}
}
