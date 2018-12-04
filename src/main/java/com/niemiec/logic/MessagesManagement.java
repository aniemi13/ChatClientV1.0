package com.niemiec.logic;

import com.niemiec.controllers.ChatController;
import com.niemiec.objects.GeneralChat;
import com.niemiec.objects.managers.InterlocutorsManager;

public class MessagesManagement {
	private final String PRIVATE_MESSAGE = "pm";
	private final String GROUP_MESSAGE = "gm";

	private ChatController chatController;
	private InterlocutorsManager interlocutorsManager;
	private GeneralChat generalChat;
	private String message;
	private String typeOfMessage;
	private String nick;
	private String incomingNick;
	private String rightMessage;
	private String actualInterlocutor;

	public MessagesManagement(ChatController chatController, String nick) {
		this.chatController = chatController;
		this.nick = nick;
		interlocutorsManager = new InterlocutorsManager();
		generalChat = new GeneralChat();
	}

	public void messageIsString(Object object) {
		// TODO Auto-generated method stub
		// rozróżnienie /pm od /gm
		// jeżeli wiadomość przychodzi do aktualnie wyświetlanego użytkownika
		// to aktualizujemy ListView
		message = (String) object;
		System.out.println(message);
		separateMessage();

		if (typeOfMessage.equals(PRIVATE_MESSAGE)) {
			receivedPrivateMessage();
		} else if (typeOfMessage.equals(GROUP_MESSAGE)) {
			receivedGroupMessage();
		}
		message = null;
	}

	private void receivedPrivateMessage() {
		// TODO Auto-generated method stub

	}

	private void receivedGroupMessage() {
		String m = ((incomingNick == nick) ? "TY" : incomingNick) + "> " + rightMessage;
		generalChat.addMessage(m);
		chatController.addMessageToGeneralChat(m);
	}

	private void separateMessage() {
		getTypeOfMessageFromMessage();
		getNickAndRightMessageFromMessage();
	}

	private void getNickAndRightMessageFromMessage() {
		for (int i = 4; i < message.length(); i++) {
			if (separateTheNickNameAndMessage(i))
				break;
		}
	}

	private boolean separateTheNickNameAndMessage(int i) {
		if (message.charAt(i) == '/') {
			rightMessage = message.substring(i + 1);
			incomingNick = message.substring(4, i);
			return true;
		}
		return false;
	}

	private void getTypeOfMessageFromMessage() {
		typeOfMessage = message.substring(1, 3);
	}

	public void messageIsArrayList(Object object) {
		// TODO Auto-generated method stub
		// ZAKTUALIZOWANIE LISTY UŻYTKOWNIKÓW
	}

	public void setActualInterlocutor(String nick) {
		this.actualInterlocutor = nick;
		// aktualizacja wyświetlania prywatnej wiadomości - ustawienie, że wiadomość
		// odczytana
		// i wyświetlenie jej
	}

	public Object sendToGeneralChat(String message) {
		return new String("/" + GROUP_MESSAGE + "/" + nick + "/" + message);
	}

}
