package com.niemiec.logic;

import java.util.ArrayList;

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
		this.actualInterlocutor = "";
		interlocutorsManager = new InterlocutorsManager();
		generalChat = new GeneralChat();
	}

	public void messageIsString(Object object) {
		// TODO Auto-generated method stub
		// rozróżnienie /pm od /gm
		// jeżeli wiadomość przychodzi do aktualnie wyświetlanego użytkownika
		// to aktualizujemy ListView
		message = (String) object;
		separateMessage();

		if (typeOfMessage.equals(PRIVATE_MESSAGE)) {
			receivedPrivateMessage();
		} else if (typeOfMessage.equals(GROUP_MESSAGE)) {
			receivedAndViewGroupMessage();
		}
		message = null;
	}

	private void receivedPrivateMessage() {
		if (!interlocutorsManager.isExist(incomingNick)) {
			interlocutorsManager.addInterlocutor(incomingNick);
		}
		String m = ((incomingNick.equals(nick)) ? "TY" : incomingNick) + "> " + rightMessage;
		interlocutorsManager.addMessage(incomingNick, m);
		if (incomingNick.equals(actualInterlocutor)) {
			interlocutorsManager.setMessageIsRead(incomingNick, true);
			addMessageToPrivateChat();
		} else {
			// jeżeli nie to podświetlenie pola
			interlocutorsManager.setMessageIsRead(incomingNick, false);
		}
	}

	private void receivedAndViewGroupMessage() {
		String m = ((incomingNick.equals(nick)) ? "TY" : incomingNick) + "> " + rightMessage;
		generalChat.addMessage(m);
		chatController.addMessageToGeneralChat(m);
	}

	private void separateMessage() {
		getTypeOfMessageFromMessage();
		if (typeOfMessage.equals(PRIVATE_MESSAGE)) {
			getNickAndInterlocutorNickAndRightMessageFromMessage();
		} else if (typeOfMessage.equals(GROUP_MESSAGE)) {
			getNickAndRightMessageFromMessage();
		}
	}

	private void getNickAndInterlocutorNickAndRightMessageFromMessage() {
		String[] s = message.split("/", 5);
		typeOfMessage = s[1];
		incomingNick = s[2];
		rightMessage = s[4];
	}

	private void getNickAndRightMessageFromMessage() {
		String[] s = message.split("/", 4);
		typeOfMessage = s[1];
		incomingNick = s[2];
		rightMessage = s[3];
	}

	private void getTypeOfMessageFromMessage() {
		typeOfMessage = message.substring(1, 3);
	}

	@SuppressWarnings("unchecked")
	public void messageIsArrayList(Object object) {
		chatController.updateUsersList((ArrayList<String>) object);
	}

	public void setActualInterlocutor(String selectedNick) {
		if (selectedNick.equals(this.nick)) {
			// jeżeli wybrałem siebie to zablokować możliwość wpisywania - nieaktywny
			// przycisk wyślij
			// i wyczyścić privateListView
		} else if (!selectedNick.equals(actualInterlocutor)) {
			System.err.println(selectedNick);
			if (!interlocutorsManager.isExist(selectedNick)) {
				interlocutorsManager.addInterlocutor(selectedNick);
			}
			actualInterlocutor = selectedNick;
//
			if (interlocutorsManager.haveMessages(selectedNick)) {
//				System.out.println("typeOfMessage: " + typeOfMessage + ", incomingNick: " + incomingNick
//						+ ", actualInterlocutor: " + actualInterlocutor);
//				interlocutorsManager.setMessageIsRead(actualInterlocutor, true);
//				System.out.println("actual messages: " + interlocutorsManager.getMessages(selectedNick));
				updatePrivateChatListView();
				
				//WIADOMOŚĆ ODCZYTANA - ZNIKA PODŚWIETLENIE UŻYTKOWNIKA
			}
			// aktualizacja wyświetlania prywatnej wiadomości - ustawienie, że wiadomość
			// odczytana
			// i wyświetlenie jej
		}

	}

	private void updatePrivateChatListView() {
		chatController.updatePrivateChat(interlocutorsManager.getMessages(actualInterlocutor));
	}

	private void addMessageToPrivateChat() {
		String m = ((incomingNick.equals(nick)) ? "TY" : incomingNick) + "> " + rightMessage;
		chatController.addMessageToPrivateChat(m);
	}

	public Object sendToGeneralChat(String message) {
		return new String("/" + GROUP_MESSAGE + "/" + nick + "/" + message);
	}

	public void highlightTheFieldInListView(String nick) {

	}

	public Object sendToPrivateChat(String message) {
		chatController.addMessageToPrivateChat("TY> " + message);
		return new String("/" + PRIVATE_MESSAGE + "/" + nick + "/" + actualInterlocutor + "/" + message);
	}

}
