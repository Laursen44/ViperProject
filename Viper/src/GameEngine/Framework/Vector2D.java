package GameEngine.Framework;

public class Vector2D {

	protected float x, y; 
	
	public Vector2D(float x, float y)
	{
		this.x = x; 
		this.y = y;
	}
	
	public float length()
	{
		return(int)Math.sqrt(x * x + y * y);
	}
	
	public float dot(Vector2D v)
	{
		return x * v.getX() + y * v.getY();
	}
	
	public Vector2D normalize()
	{
		float length = length();
		x /= length;
		y /= length;
		return this;
	}
	
	public Vector2D rotate(Vector2D vec, float angle)
	{
		float rad = (float)Math.toRadians(angle);
		float cos = (float)Math.cos(rad);
		float sin = (float)Math.sin(rad);
		
		return new Vector2D((float)(x * cos - y * sin),(float)(x * sin + y * cos)).mul(vec);
	}
	
	public Vector2D add(Vector2D v)
	{
		return new Vector2D(x + v.getX(), y + v.getY());
	}
	
	public Vector2D add(float v)
	{
		return new Vector2D(x + v, y + v);
	}
	
	public Vector2D sub(Vector2D v)
	{
		return new Vector2D(x - v.getX(), y - v.getY());
	}
	
	public Vector2D sub(float v)
	{
		return new Vector2D(x - v, y - v);
	}
	
	public Vector2D mul(Vector2D v)
	{
		return new Vector2D(x * v.getX(), y * v.getY());
	}
	
	public Vector2D mul(float v)
	{
		return new Vector2D(x * v, y * v);
	}
	
	public Vector2D div(Vector2D v)
	{
		return new Vector2D(x / v.getX(), y / v.getY());
	}
	
	public Vector2D div(float v)
	{
		return new Vector2D(x / v, y / v);
	}
	
	public String toString()
	{
		return "(" + x + " " + y +")";
	}
	

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}


