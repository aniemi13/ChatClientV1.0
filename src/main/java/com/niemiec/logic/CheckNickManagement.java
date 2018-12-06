package com.niemiec.logic;

import com.niemiec.controllers.GetNickController;

public class CheckNickManagement {
	private final String CHECK_NICK = "cn";
	
	private GetNickController getNickController;
	
	public CheckNickManagement(GetNickController getNickController) {
		this.getNickController = getNickController;
	}
	
	public Object sendNick(String nick) {
		return new String();
	}
}
