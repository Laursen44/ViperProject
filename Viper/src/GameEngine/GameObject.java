package GameEngine;

import java.awt.Graphics;

public abstract class GameObject {

	protected Vector2D vec;
	protected int velX, velY;
	protected static ID id;
	
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

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public ID getId() {
		return id;
	}
}
