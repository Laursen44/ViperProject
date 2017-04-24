package GameEngine.Framework;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import GameEngine.GameDesign.GUITextBox;
import GameEngine.GameDesign.Level;
import GameEngine.SuperEntities.NetPlayer;
import GameEngine.SuperEntities.Player;
import GameEngine.Util.Vector2D;
import Serialization.Type;
import Serialization.VPDatabase;
import Serialization.VPField;
import Serialization.VPObject;
import Serialization.VPString;

public class Client {
	
	public enum Error
	{
		NONE, INVALID_HOST,SOCKET_EXCEPTION
	}
	
	private int port;
	private String ipAddress;
	private InetAddress serverAddress;
	private Error errorCode = Error.NONE;
	private DatagramSocket socket;
	private final int MAX_PACKET_SIZE = 1024;
	private byte[] receivedDataBuffer = new byte [MAX_PACKET_SIZE *10]; 
	private boolean playerActive = false;
	private Thread listenThread;
	private boolean listening = false;
	public int netplayerX, netplayerY;
	
	public Client(String host) // host parameter eg. 192.168.1.1:5000
	{
		String[] parts = host.split(":");
		if(parts.length != 2)
		{
			errorCode = Error.INVALID_HOST;
			return;
		}
		
		ipAddress = parts[0];
		try{
			port = Integer.parseInt(parts[1]);
		} catch (NumberFormatException e)
		{
			errorCode = Error.INVALID_HOST;
			return;
		}
	}
	
	public Client (String host, int port)
	{
	this.ipAddress = host;
	this.port = port;
	}
	
	public boolean connect()
	{
		try {
			serverAddress = InetAddress.getByName(ipAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			errorCode = Error.INVALID_HOST;
			return false;
		}
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
			errorCode = Error.SOCKET_EXCEPTION;
			return false;
		}	
		return true;
	}
	
	public void start()
	{
		listening = true;
		listenThread= new Thread (() -> listen(), "ViperProjectClient-ListenThread" );
		listenThread.start();
		System.out.println("client is Listening");
	}
	
	public void listen()
	{			
		long last = System.nanoTime();
		double targetlooptime = 60;
		double optimalTime = 1000000000.0 / targetlooptime;  
		double delta = 0;
		while (listening)
		{
			long now = System.nanoTime();
			delta += (now - last) / optimalTime;
			last = now;
			while(delta >= 1)
			{
				while (listening)
				{
					DatagramPacket  pack = new DatagramPacket(receivedDataBuffer, MAX_PACKET_SIZE);
					try
					{
						socket.receive(pack);
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}	
					process(pack);
				}
			delta--;
		}
			
	}
		
	}
	
	public void send(byte[] data)
	{
		if(socket.isConnected())
		{
		}
		DatagramPacket pack = new DatagramPacket(data, data.length, serverAddress, port);

		try {
			socket.send(pack);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void send(VPDatabase database)
	{
		byte[] data = new byte[database.getSize()];
		database.getBytes(data,0);
		send(data);
		
	}
	
	public void process(DatagramPacket pack)
	{
		byte[] data = pack.getData();
		if (new String(data,0,4).equals("VPDB"))
		{
			System.out.println("recieved VPDB from server!");
			VPDatabase database = VPDatabase.Deserialize(data);
			//dump(database);
			update(database);
		}
	}
	
	public void update(VPDatabase database)
	{
		if (Level.level == 0) return;
			
		if (database.getName().equals("Server Clients"))
		{
			
			for (VPObject object : database.objects)
			{
				if (object.getName().equals(GUITextBox.username))
				{
					//make or update player!
					if(!playerActive)
					{
						int x = 0, y = 0;
						for (VPField field : object.fields)
						{
							if(field.getName().equals("x")) x = field.getInt();
							if(field.getName().equals("y")) y = field.getInt();
						}
						ObjectHandler.addObject(new Player(new Vector2D(x, y)));
						playerActive = true;
					}else 
					{
						int x = 0, y = 0;
						for (VPField field : object.fields)
						{
							if(field.getName().equals("x")) x = field.getInt();
							if(field.getName().equals("y")) y = field.getInt();
						}
						System.out.println("updated player");
						Player.vec = new Vector2D(x,y);
						//Player.vec = Player.vec.add(new Vector2D(0,y));
					}
					
				}
				
				if (!object.getName().equals(GUITextBox.username))
				{
					// make or update net-player!
					boolean newPlayer = true;
					for (int i = 0; i < ObjectHandler.netPlayers.size(); i++)
					{
						NetPlayer p = ObjectHandler.netPlayers.get(i);
		
						if (object.getName().equals(p.username))
						{
							int x = 0, y = 0;
							for (VPField field : object.fields)
							{
								if(field.getName().equals("x")) x = field.getInt();
								if(field.getName().equals("y")) y = field.getInt();
							}
							newPlayer = false;
							System.out.println("updated Net Player");
							p.update(x, y);
						}
					}
					if(newPlayer)
					{
						int x = 0, y = 0;
						for (VPField field : object.fields)
						{
							if(field.getName().equals("x")) x = field.getInt();
							if(field.getName().equals("y")) y = field.getInt();
						}
						System.out.println("added Net Player");
						ObjectHandler.addNetPlayer(new NetPlayer(object.getName(), new Vector2D(x,y)));
					}
				}
			}
		}
	}
	
	public Error getErrorCode()
	{
		return errorCode;
	}

	private void dump(VPDatabase database)
	{
		System.out.println("----------------------------------");
		System.out.println("VPDatabase");
		System.out.println("----------------------------------");
		System.out.println("Name: " + database.getName());
		System.out.println("Size: " + database.getSize());
		System.out.println("Object count: " + database.objects.size());
		System.out.println();
		for(VPObject object : database.objects)
		{
			System.out.println("\tObject:");
			System.out.println("\tName: " + object.getName());
			System.out.println("\tSize: " +object.getSize());
			System.out.println("\tField Count: " + object.fields.size());
			for (VPField field : object.fields)
			{
				System.out.println("\t\tField: ");
				System.out.println("\t\tName: " + field.getName());
				System.out.println("\t\tSize: " + field.getSize());
				System.out.println("\t\tSize: " + field.getInt());
		System.out.println("---------------------------------");
		
			}
		}
	}
}