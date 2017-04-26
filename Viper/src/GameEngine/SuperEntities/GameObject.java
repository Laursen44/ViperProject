package GameEngine.SuperEntities;

import java.awt.Graphics;
import java.awt.Rectangle;

import GameEngine.Util.Vector2D;

public abstract class GameObject extends Rectangle {

	private static final long serialVersionUID = 1L;
	protected Vector2D vec;
	
	public GameObject()
	{	
	}
	
	public GameObject(Vector2D vec)
	{	
		this.vec = vec;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	public Vector2D getVec() {
		return vec;
	}

	public void setVec(Vector2D vec) {
		this.vec = vec;
	}
}
