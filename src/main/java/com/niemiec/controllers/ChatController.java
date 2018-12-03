package com.niemiec.controllers;

import java.util.ArrayList;

import com.niemiec.client.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ChatController {
	@FXML
	private HBox mainHBox;

	@FXML
	private TextArea generalChat;

	@FXML
	private TextField textAreaGeneralChat;

	@FXML
	private Button sendToGeneralChatButton;

	@FXML
	private ListView<String> listOfUsersChat;

	@FXML
	private TextArea privateChat;

	@FXML
	private TextField textAreaPrivateChat;

	@FXML
	private Button sendToPrivateChatButton;

	private String nick;
	private Client clientConnection;
	ObservableList<String> list;

	@FXML
	void initialize() {
		list = FXCollections.observableArrayList();
		this.clientConnection = new Client(this, nick);
	}

	@FXML
	void getUserNick(MouseEvent event) {
		ObservableList<String> userNickFromListView;
		userNickFromListView = listOfUsersChat.getSelectionModel().getSelectedItems();
		clientConnection.setUserNickToPrivateMessage(userNickFromListView.get(0));
	}

	@FXML
	void sendToGeneralChat(ActionEvent event) {
		clientConnection.sendToGeneralChat(textAreaGeneralChat.getText());
	}

	@FXML
	void sendToPrivateChat(ActionEvent event) {
		clientConnection.sendToPrivateChat(textAreaPrivateChat.getText());
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public ArrayList<String> getUserList(ObservableList<String> obs) {
		ArrayList<String> a = new ArrayList<>();
		for (int i = 0; i < obs.size(); i++) {
			a.add(obs.get(i));
		}

		return a;
	}

	public void setUserList(ArrayList<String> list) {
		listOfUsersChat.getItems().clear();
		this.list.addAll(list);
		listOfUsersChat.setItems(this.list);
	}
	
	public void setTextToGeneralChat(String text) {
		generalChat.setText(text);
		
	}

}
