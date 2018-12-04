package com.niemiec.connection;

import java.io.IOException;
import java.net.Socket;

import com.niemiec.objects.Client;

public class Connection extends Thread {
	private Client client;
	private Socket socket;
	private boolean isConnected;
	private InputOutputStream inputOutputStream;
	private boolean closeTheConnection;

	public Connection(Client client, String host, int port) {
		this.client = client;
		this.isConnected = false;
		makeTheConnection(host, port);
		this.closeTheConnection = false;
		this.inputOutputStream = new InputOutputStream(socket);
	}

	@Override
	public void run() {
		Object object = null;
		while (!closeTheConnection) {
			object = inputOutputStream.receiveTheObject();
			client.receiveTheObject(object);
		}
		inputOutputStream.closeInputOutputStream();
		interrupt();
	}

	public void makeTheConnection(String host, int port) {
		socket = null;
		try {
			socket = new Socket(host, port);
			isConnected = true;
		} catch (Exception e) {
			System.out.println("Błąd tworzenia połączenia: " + e);
		}
	}
	
	public void interrupt() {
		super.interrupt();
		try {
			socket.close();
			isConnected = false;
		} catch (IOException e) {
		}
	}

	public boolean isConnected() {
		return isConnected;
	}
	
	public void sendTheObject(Object object) {
		inputOutputStream.sendTheObject(object);
	}
	
	public void closeTheConnection() {
		closeTheConnection = true;
	}
}
