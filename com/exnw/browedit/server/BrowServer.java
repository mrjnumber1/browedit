package com.exnw.browedit.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.exnw.browedit.data.Map;

public class BrowServer implements Runnable
{
	Thread listenThread;
	ServerSocket listener;
	
	ArrayList<ServerClient> clients = new ArrayList<ServerClient>();
	
	public Map map;
	
	
	public BrowServer()
	{
		try
		{
			listener = new ServerSocket(8203);
			
			listenThread = new Thread(this);
			listenThread.start();		
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void run()
	{
		while(true)
		{
			Socket clientSocket;
			try
			{
				clientSocket = listener.accept();
				new ServerClient(clientSocket, this);
			} catch (IOException e)
			{
				e.printStackTrace();
			}			
		}
		
	}

	public synchronized void addClient(ServerClient serverClient)
	{
		clients.add(serverClient);
	}

	public synchronized void removeClient(ServerClient serverClient)
	{
		clients.remove(serverClient);		
	}
}
