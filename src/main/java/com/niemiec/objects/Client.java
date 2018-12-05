package com.niemiec.objects;

import java.util.ArrayList;

import com.niemiec.connection.Connection;
import com.niemiec.controllers.ChatController;
import com.niemiec.logic.MessagesManagement;


public class Client {
	private Connection connection;
	private MessagesManagement messagesManagement;

	public Client(ChatController chatController, String nick) {
		createConnection();
		this.messagesManagement = new MessagesManagement(chatController, nick);
	}

	public void setUserNickToPrivateMessage(String actualInterlocutor) {
		messagesManagement.setActualInterlocutor(actualInterlocutor);
		
	}

	public void sendToGeneralChat(String message) {
		connection.sendTheObject(messagesManagement.sendToGeneralChat(message));
	}

	public void sendToPrivateChat(String message) {
		connection.sendTheObject(messagesManagement.sendToPrivateChat(message));
	}

	public void receiveTheObject(Object object) {
		if (object instanceof String) {
			messagesManagement.messageIsString(object);
		} else if (object instanceof ArrayList) {
			messagesManagement.messageIsArrayList(object);
		}
	}

	private void createConnection() {
		connection = new Connection(this, "localhost", 6666);
		connection.start();
	}
}
