package com.niemiec.controllers;

import java.util.ArrayList;

import com.niemiec.objects.Client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ChatController {
	@FXML
	private HBox mainHBox;

	@FXML
	private ListView<String> generalChat;

	@FXML
	private TextField textAreaGeneralChat;

	@FXML
	private Button sendToGeneralChatButton;

	@FXML
	private ListView<String> listOfUsersChat;

	@FXML
	private ListView<String> privateChat;

	@FXML
	private TextField textAreaPrivateChat;

	@FXML
	private Button sendToPrivateChatButton;

	private Client client;
	ObservableList<String> list;

	@FXML
	void initialize() {
		list = FXCollections.observableArrayList();
		this.client = new Client(this, GetNickController.nick);
	}

	@FXML
	void getUserNick(MouseEvent event) {
		ObservableList<String> userNickFromListView;
		userNickFromListView = listOfUsersChat.getSelectionModel().getSelectedItems();
		client.setUserNickToPrivateMessage(userNickFromListView.get(0));
	}

	@FXML
	void sendToGeneralChat() {
		client.sendToGeneralChat(textAreaGeneralChat.getText());
		textAreaGeneralChat.clear();
	}
	
	@FXML
    void sendToGeneralChatAfterKeyPress(KeyEvent event) {
		if ("ENTER".equals(event.getCode().toString())) {
			sendToGeneralChat();
		}
    }

	@FXML
	void sendToPrivateChat(ActionEvent event) {
		client.sendToPrivateChat(textAreaPrivateChat.getText());
	}

	public ArrayList<String> getUserList(ObservableList<String> obs) {
		ArrayList<String> a = new ArrayList<>();
		for (int i = 0; i < obs.size(); i++) {
			a.add(obs.get(i));
		}

		return a;
	}

	public void updateUsersList(ArrayList<String> list) {
		listOfUsersChat.getItems().clear();
		this.list.addAll(list);
		listOfUsersChat.setItems(this.list);
	}

	public void addMessageToGeneralChat(String message) {
		Platform.runLater(() -> { 
			generalChat.getItems().add(message);
		});
	}
}
