package br.ufma.cm.mobilesuscliente.controller;

public class Controller {
	private static ControllerChat chat=new ControllerChat();
	private Controller() {
	}
	
	public static ControllerChat getChat(){
		return chat;
	}
}
