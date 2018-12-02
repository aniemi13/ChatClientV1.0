package com.niemiec.connections;

import java.util.ArrayList;

import com.niemiec.controllers.ChatController;

import javafx.scene.control.TextField;

public class ClientConnection extends Thread {
	private ChatController chatController;
	private String nick;
	private String actualPrivateUser;

	public ClientConnection(ChatController chatController, String nick) {
		this.chatController = chatController;
		this.nick = nick;
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
	}

	public void sendToPrivateChat(String text) {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<>();
		a.add("aa");
		a.add("ff");
		chatController.setUserList(a);
	}


}
