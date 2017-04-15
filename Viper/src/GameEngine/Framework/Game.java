package GameEngine.Framework;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 5997611677775171115L;
	private static final int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;
	private boolean running = false;
	private Graphics g;
	private Thread game;
	private ObjectHandler handler;
	private Sprites loadSpriteSheets;
	
	public static void main(String[] args)
	{
		new Game();
	}

	public Game() 
	{
		new Window(WIDTH, HEIGHT, "Viper Project", this);
		handler = new ObjectHandler();
		KeyboardManager keyboard = new KeyboardManager();
		addKeyListener(keyboard);
		MouseManager mouse = new MouseManager();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
	}
		

	public synchronized void start()
	{
		game = new Thread(() -> run(), "Gameloop thread");
		game.start();
		running = true;
	}

	public synchronized void stop()
	{
		try {
			game.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		double lastLoopTime = System.nanoTime();
		int targetFps = 60;
		double optimalTime = 1000000000 / targetFps;  
		double delta = 0;
		double timer = System.currentTimeMillis();
		int frames = 0;
		Sprites.initialize();
		// keep looping round until the game ends
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastLoopTime) / optimalTime;
			lastLoopTime = now;
			while(delta >= 1)
			{
				update();
				delta--;
			}
			if(running)
			{
				render();
				frames++;
				
				if(System.currentTimeMillis() - timer > 1000)
				{
					timer += 1000;
					System.out.println("FPS :" + frames);
					frames = 0;
				}
			}
		}
		stop();
	}

	public void render()
	{
		BufferStrategy buffStrat = getBufferStrategy();
		if(buffStrat == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		g = buffStrat.getDrawGraphics();
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		
		g.dispose();
		buffStrat.show();
	}

	public void update()
	{
		handler.update();
		KeyboardManager.update();
	}
	

}






