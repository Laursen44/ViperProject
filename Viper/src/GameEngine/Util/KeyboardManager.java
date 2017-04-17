package GameEngine.Util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener 
{

	public static boolean[] keys = new boolean[160];
	public static boolean up, down, left, right, key1, key2, key3;

	public static void update()
	{
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		key1 = keys[KeyEvent.VK_1];
		key2 = keys[KeyEvent.VK_2];
		key3 = keys[KeyEvent.VK_3];
	}

	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent arg0) 
	{

	}
}
