package GameEngine.Framework;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import GameEngine.GameDesign.GUITextBox;
import GameEngine.GameDesign.Level;
import GameEngine.GameDesign.OnGUI;
import GameEngine.Util.KeyboardManager;
import GameEngine.Util.MouseManager;
import GameEngine.Util.Sprites;
import Serialization.VPDatabase;
import Serialization.VPObject;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 5997611677775171115L;
	public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
	private boolean running = false;
	private Graphics g;
	private Thread game;
	private ObjectHandler handler;
	private Level level;
	private OnGUI gui;
	public static Client client;
	
	public static void main(String[] args)
	{
		new Game();
	}

	public Game() 
	{
		new Window(WIDTH, HEIGHT, "Viper Project", this);
		handler = new ObjectHandler();
		level = new Level();
		gui = new OnGUI();
		KeyboardManager keyboard = new KeyboardManager();
		addKeyListener(keyboard);
		MouseManager mouse = new MouseManager();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		client = new Client("localhost", 5030);
		client.connect();
		client.start();
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
		long last = System.nanoTime();
		double targetlooptime = 60;
		double optimalTime = 1000000000.0 / targetlooptime;  
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		Sprites.initialize();
		// keep looping round until the game ends
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - last) / optimalTime;
			last = now;
			
			// the difference between last and now is a benchmark to how fast the cpu can process code in nanoseconds
			// that time is divided by the optimalTime which is a second in nanoseconds divided by 60.
			// this gives us what 1 60th of a second (real time) in cpu processing nanoseconds time is.
			// we can then decrement delta until it hits 1 and breaks the while loop.
			// each iteration delta will be redone as last is set to now and now the new value of the nanoTime.
			// executes roughly 60 times per second.
			while(delta >= 1)
			{
				update();
				delta--;
			}
			
			// execute as as many times as possible
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
		
        VPDatabase database = new VPDatabase("Dead");
		VPObject object = new VPObject(GUITextBox.username);
		database.addObject(object);
		client.send(database);
		
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
		gui.render(g);
		
		g.dispose();
		buffStrat.show();
	}

	public void update()
	{
		handler.update();
		KeyboardManager.update();
		gui.update();
		level.update();
	}
}