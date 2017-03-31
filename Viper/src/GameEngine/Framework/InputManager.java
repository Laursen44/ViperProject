package GameEngine.Framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputManager extends KeyAdapter  
{

private ObjectHandler handler;
	
	public InputManager(ObjectHandler handler)
	{
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject temp = handler.object.get(i);
			
			if(temp instanceof Player)
			{	
				if(key == KeyEvent.VK_W) temp.setVelY(-1);

				if(key == KeyEvent.VK_S) temp.setVelY(1);	

				if(key == KeyEvent.VK_A) temp.setVelX(-1);
					
				if(key == KeyEvent.VK_D) temp.setVelX(1);

			}
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject temp = handler.object.get(i);
			
			if(temp instanceof Player)
			{
				if(key == KeyEvent.VK_W) temp.setVelY(0);

				if(key == KeyEvent.VK_S) temp.setVelY(0);	

				if(key == KeyEvent.VK_A) temp.setVelX(0);
					
				if(key == KeyEvent.VK_D) temp.setVelX(0);
			}
		}
	}
}
