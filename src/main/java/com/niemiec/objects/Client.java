package com.niemiec.objects;

import com.niemiec.connection.Connection;
import com.niemiec.controllers.ChatController;
import com.niemiec.logic.MessagesManagement;


public class Client {
	private Connection connection;
	private MessagesManagement messagesManagement;

	public Client(ChatController chatController) {
		this.messagesManagement = new MessagesManagement(chatController);
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
		messagesManagement.receiveTheObject(object);
	}

	public void exit() {
		connection.sendTheObject(messagesManagement.exit());
		connection.interrupt();
	}
	
	public void setNick(String nick) {
		messagesManagement.setNick(nick);
	}

	public void setConnection(Connection connection2) {
		this.connection = connection2;
		this.connection.setClient(this);
	}

	public void sendReadyToWork() {
		connection.sendTheObject(messagesManagement.sendReadyToWork());
	}
}
