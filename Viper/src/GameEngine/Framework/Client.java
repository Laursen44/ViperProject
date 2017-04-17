package GameEngine.Framework;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
		sendConnectionPacket();
		//Wait for server reply
		return true;
	}
			private void sendConnectionPacket()
			{
			byte[] data = "ConnectionPacket".getBytes();
			send(data);
		}
	
	
	public void send(byte[] data)
	{
		assert(socket.isConnected());
		DatagramPacket pack = new DatagramPacket(data, data.length, serverAddress, port);

		try {
			socket.send(pack);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public Error getErrorCode()
	{
		return errorCode;
	}
}
