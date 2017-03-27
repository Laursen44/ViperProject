package GameEngine;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = 5997611677775171115L;

	private static final int WIDTH = 800, HEIGHT = WIDTH / 16 * 9;
	private boolean running = false;
	private Graphics g;
	private Thread game;
	private ObjectHandler handler;
	
	public static void main(String[] args)
	{
		new Game();
	}

	public Game() 
	{
		new Window(WIDTH, HEIGHT, "Game Engine", this);
		handler = new ObjectHandler();
		this.addKeyListener(new InputManager(handler));
	}

	public synchronized void start()
	{
		game = new Thread(this);
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
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		
		g.dispose();
		bs.show();
	}

	public void update()
	{
		handler.update();
	}
}
