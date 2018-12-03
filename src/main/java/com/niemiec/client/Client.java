package com.niemiec.client;

import java.util.ArrayList;

import com.niemiec.connection.Connection;
import com.niemiec.controllers.ChatController;

import javafx.scene.control.TextField;

public class Client {
	private ChatController chatController;
	private String nick;
	private String actualPrivateUser;
	private Connection connection;

	public Client(ChatController chatController, String nick) {
		this.chatController = chatController;
		this.nick = nick;
		connection = new Connection(this, "localhost", 6666);
		connection.start();
	}

	public void setUserNickToPrivateMessage(String privateUser) {
		this.actualPrivateUser = privateUser;
	}

	public void sendToGeneralChat(String text) {
		ArrayList<String> a = new ArrayList<>();
		a.add("adsdsda");
		a.add("ffdsds");
		a.add("ffdsdsdsdsd");
		chatController.setUserList(a);
		connection.sendTheObject(a);
//		receiveTheObject(text);
	}

	public void sendToPrivateChat(String text) {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<>();
		a.add("aa");
		a.add("ff");
		chatController.setUserList(a);
	}

	public void receiveTheObject(Object object) {
		if (object instanceof ArrayList) {
			ArrayList<String> a = (ArrayList<String>) object;
			for (int i = 0; i < a.size(); i++) {
				System.out.println(a.get(i));
			}
		}
		if (object != null && object instanceof String)
			chatController.setTextToGeneralChat((String) object);
	}

}
