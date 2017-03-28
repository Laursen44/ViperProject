package GameEngine;

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
		Vector2D dirVec;
		int key = e.getKeyCode();
		
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject temp = handler.object.get(i);
			
			if(temp.getId() == ID.PLAYER)
			{
				dirVec = new Vector2D(temp.vec.getX(), temp.vec.getY());
						
				if(key == KeyEvent.VK_W)
				{
					dirVec.rotate(temp.vec, 10f);
				}
				if(key == KeyEvent.VK_S)
				{
					temp.vec.mul(dirVec);
					temp.vec.add(10f);
				}
				
				if(key == KeyEvent.VK_A) temp.setVec(temp.getVec().normalize());
				//if(key == KeyEvent.VK_D) temp.setVec(temp.getVec().rotate(5));
			}
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.PLAYER)
			{

			}
		}
	}
}
