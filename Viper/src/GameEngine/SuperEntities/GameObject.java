package GameEngine.SuperEntities;

import java.awt.Graphics;
import java.awt.Rectangle;

import GameEngine.Util.Vector2D;

public abstract class GameObject extends Rectangle {

	private static final long serialVersionUID = 1L;
	protected Vector2D vec;
	protected ID id;
	
	public GameObject()
	{	
	}
	
	public GameObject(Vector2D vec)
	{	
		this.vec = vec;
	}
	
	public GameObject(Vector2D vec, ID id)
	{	
		this.vec = vec;
		this.id = id;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	public Vector2D getVec() {
		return vec;
	}

	public void setVec(Vector2D vec) {
		this.vec = vec;
	}

	public ID getId() {
		return id;
	}
}
