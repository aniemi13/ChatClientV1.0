package com.niemiec.connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InputOutputStream {
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	
//	private BufferedReader bufferedReader;
//	private DataOutputStream dataOutputStream;

	public InputOutputStream(Socket socket) {
		this.socket = socket;
		createOutputStream();
		createInputStream();
	}
	
	public void sendTheObject(Object object) {
		try {
//			dataOutputStream.writeBytes(object + "\n");
			outputStream.writeObject(object);
		} catch (IOException e) {
			System.out.println("Błąd wysyłania danych: " + e);
		}
	}
	
	public Object receiveTheObject() {
		try {
//			return bufferedReader.readLine();
			return inputStream.readObject();
		} catch (Exception e) {
			System.out.println("Błąd odbierania danych: " + e);
		}
		return null;
	}

	private void createOutputStream() {
		try {
//			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			outputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Błąd tworzenia strumienia wyjściowego: " + e);
		}
	}

	private void createInputStream() {
		try {
//			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("Błąd tworzenia strumienia wejściowego: " + e);
		}
	}
	
	public void closeInputOutputStream() {
		try {
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
		}
	}
}
