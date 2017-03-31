package GameEngine.Framework;

import java.awt.Graphics;

public class Projectile extends GameObject {

	private static final long serialVersionUID = 1L;
	Graphics g;

	public Projectile(Vector2D vec, Graphics g, ID id) {
		this.vec = vec;
		this.g = g;
		id = ID.PROJECTTILE;
	}

	public void update() {
		
	}

	public void render(Graphics g) {
		
	}

}
